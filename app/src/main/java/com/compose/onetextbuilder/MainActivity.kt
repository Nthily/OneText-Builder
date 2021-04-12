package com.compose.onetextbuilder

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.onetextbuilder.ui.theme.OneTextBuilderTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel:UiState by viewModels()

    @ExperimentalAnimationApi
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OneTextBuilderTheme {
                Test(viewModel)
            }
        }
    }
}

@ExperimentalAnimationApi
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Test(viewModel:UiState) {

    SwipeToRefreshLayout(
        refreshingState = viewModel.flag,
        onRefresh = {
            GlobalScope.launch(Dispatchers.Main){
                viewModel.result = v1Function()
                viewModel.enableRed = false
            }
            viewModel.flag = true
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
        Demo(viewModel)
    }
}


@ExperimentalAnimationApi
@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Demo(viewModel: UiState) {

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
            Column{
                Title()
                CardContent(viewModel)
            }
            Buttons(viewModel)
        }
    }
}


@Composable
fun Title() {
    Column(
        modifier = Modifier
            .width(300.dp)
            .height(140.dp)
            .background(Color(0xFFE97A3D)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "一言",
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
            .fillMaxSize()
            .clickable {

            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(visible = viewModel.result!="") {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("“", style = MaterialTheme.typography.h4, modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 20.dp))
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.W900, color = Color.Black)
                ) {
                    append(viewModel.result)
                }
            },
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp),
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        AnimatedVisibility(visible = viewModel.result!="") {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("”", style = MaterialTheme.typography.h4, modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 20.dp))
            }
        }
    }
}


@Composable
fun Buttons(viewModel: UiState) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom
    ){


        var change by remember{ mutableStateOf(false)}

        val buttonSize by animateDpAsState(
            targetValue = if(change) 32.dp else 24.dp,
            animationSpec = tween(150)
        )

        if(buttonSize == 32.dp) {
            change = false
        }

        val context = LocalContext.current
        IconButton(
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