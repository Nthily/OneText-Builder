package com.compose.onetextbuilder.home.hitokoto

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.onetextbuilder.*
import com.compose.onetextbuilder.R
import com.compose.onetextbuilder.home.favorite.Favorite
import com.compose.onetextbuilder.home.setting.Setting
import com.compose.onetextbuilder.utils.*
import kotlinx.coroutines.delay


@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Home(viewModel:UiState) {
    val navController = rememberNavController()
    val context = LocalContext.current

    if(getNetWorkState(context, viewModel)){
        GetOneText(viewModel)
    }

    NavHost(navController, startDestination = "hitokoto") {
        composable("hitokoto") {
            HomePage(viewModel, navController, context)
        }
        /*
        composable("favorite") {
            Favorite(viewModel, navController)
        }
        composable("setting"){
            Setting(viewModel, navController)
        }

         */
    }
}

@Composable
fun BottomNavigation(viewModel: UiState, navController: NavHostController) {

    val items = listOf("Hitokoto", "????????????", "??????")

    BottomNavigation(
        elevation = 10.dp,
        backgroundColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = {
                    when(true) {
                        index == 0 -> Icon(Icons.Filled.Home, contentDescription = null)
                        index == 1 -> Icon(Icons.Filled.Favorite, contentDescription = null)
                        else -> Icon(Icons.Filled.Settings, contentDescription = null)
                    }
                },
                label = { Text(item) },
                selected = viewModel.selectedItem == index,
                onClick = {
                    viewModel.selectedItem = index
                    // TODO:????????????
                    viewModel.currentPage = item
                    if(viewModel.currentPage != "Hitokoto") viewModel.flag = false
                },
                unselectedContentColor = Color(0xFFDBDDEB),
                selectedContentColor = Color(0xFF80C6F6)
            )
        }
    }
}


@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomePage(viewModel: UiState,
             navController: NavHostController,
             context: Context
) {
    Scaffold(
        content = {
            when(true) {
                viewModel.selectedItem == 0 -> RefreshLayout(viewModel, context)
                viewModel.selectedItem == 1 -> Favorite(viewModel, navController)
                else -> Setting(viewModel)
            }
        },
        bottomBar = {
            BottomNavigation(viewModel, navController)
        },
        backgroundColor = Color(0xFFF4F6FC)
    )

}

@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun RefreshLayout(viewModel:UiState,context: Context) {

    val composableScope = rememberCoroutineScope()

    SwipeToRefreshLayout(
        refreshingState = viewModel.flag,
        onRefresh = {
            viewModel.flag = true

            if(getNetWorkState(context, viewModel)) {
                composableScope.launch {
                    viewModel.result = v1Function(viewModel)
                }
            }

            else {
                viewModel.result = "??????????????????????????????~"
                GlobalScope.launch(Dispatchers.IO){
                    delay(500)
                    viewModel.flag = false
                }
            }
        },
        refreshIndicator = {
            Surface(elevation = 10.dp, shape = CircleShape) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp)
                )
            }
        }) {
        HitokotoCard(viewModel)
    }
}



@ExperimentalAnimationApi
@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HitokotoCard(viewModel: UiState)   {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .width(300.dp)
                .height(450.dp),
            color = Color.White,
            elevation = 14.dp
        ) {
            Column(
                modifier = Modifier.clickable{
                }
            ){
                Title()
                CardContent(viewModel)
            }
            Row(modifier = Modifier.fillMaxSize(),verticalAlignment = Alignment.Bottom){
                CardButtons(viewModel)
            }
        }
    }
}

@Composable
fun Title() {
    Column(
        modifier = Modifier
            .width(300.dp)
            .height(140.dp)
            .background(Color(0xFF80C6F6)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "??????",
            modifier = Modifier
                .padding(8.dp),
            fontWeight = FontWeight.W900,
            style = MaterialTheme.typography.h4
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun CardContent(viewModel:UiState) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = viewModel.result!="") {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("???", style = MaterialTheme.typography.h4, modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp))
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        SelectionContainer{
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.W300,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(
                                Font(R.font.zoolkuaile)
                            ))
                    ) {
                        append(viewModel.result)
                    }
                },
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp),
                style = MaterialTheme.typography.subtitle2,
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))
        AnimatedVisibility(visible = viewModel.result!="") {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("???", style = MaterialTheme.typography.h4, modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 20.dp))
            }
        }
    }
}


@Composable
fun CardButtons(viewModel: UiState) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ){

        var change by remember{ mutableStateOf(false)}

        val buttonSize by animateDpAsState(
            targetValue = if(change) 32.dp else 24.dp,
            animationSpec = tween(120)
        )

        if(buttonSize == 32.dp) {
            change = false
        }

        val context = LocalContext.current
        MyIconButton(
            onClick = {
                viewModel.enableRed = !viewModel.enableRed
                change = true
            }
        ) {
            CompositionLocalProvider(
                LocalContentColor provides when(true) {
                    viewModel.enableRed -> Color.Red
                    else -> Color(0xFF757575)
                }) {
                Icon(Icons.Rounded.Favorite,
                    null,
                    modifier = Modifier.size(buttonSize))
            }
        }

        IconButton(
            onClick = {
                startShare(context, viewModel.result)
            },
        ) {
            CompositionLocalProvider(LocalContentColor provides Color(0xFF757575)) {
                Icon(Icons.Rounded.Share, null)
            }
        }
    }
}

@Composable
fun MyIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    val IconButtonSizeModifier = Modifier.size(48.dp)
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = null
            )
            .then(IconButtonSizeModifier),
        contentAlignment = Alignment.Center
    ) { content() }
}