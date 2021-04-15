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
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController


@ExperimentalAnimationApi
@Composable
fun Setting(viewModel:UiState, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .width(350.dp)
                .height(640.dp),
            color = Color.White,
            elevation = 10.dp,
            border = BorderStroke(3.dp, Color(0xFF80C6F6))
        ){
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    UserInfo()
                    CardItemSpacer()
                    SentenceCategory(viewModel)
                }
            }
        }
    }
}


@Composable
fun UserInfo(){
    Column {
        Text(text = "设置中心",
            fontWeight = FontWeight.W900,
            style = MaterialTheme.typography.h5)
        CardItemSpacer()
        CardBackgroundStyle{
            Row(
                modifier = Modifier.padding(10.dp)
            ){
                Surface(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(45.dp)
                ) {
                    Image(painter = painterResource(id = R.drawable.shiro), contentDescription = null)
                }
                Spacer(Modifier.padding(horizontal = 5.dp))
                Text(text = "香辣鸡腿堡",
                    fontWeight = FontWeight.W900,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(CenterVertically))
            }
        }
    }
}



@ExperimentalAnimationApi
@Composable
fun SentenceCategory(viewModel: UiState) {

    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = CenterVertically
        ){
            SubSettingTitle("句子类型")
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    viewModel.textListSwitch = !viewModel.textListSwitch
                },
                    modifier = Modifier.align(Alignment.End)) {
                    Icon(painterResource(id = R.drawable.expand_more_black_24dp), null)
                }
            }
        }
        Spacer(Modifier.padding(vertical = 2.dp))
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            CategoryList(viewModel)
        }
    }
}

@Composable
fun CategoryList(viewModel: UiState){
    val normalModifier = 0.dp
    val expandModifier = 550.dp

    val animatedHeight by animateDpAsState(
        targetValue = (if(viewModel.textListSwitch) expandModifier else normalModifier),
        animationSpec = tween(800)
    )
    val textType = listOf(
        "动漫",
        "漫画",
        "游戏",
        "文学",
        "原创",
        "网络",
        "其他",
        "影视",
        "诗词",
        "网易云",
        "哲学",
        "抖机灵"
    )

    Column(
        modifier = Modifier
            .padding(8.dp)
            .height(animatedHeight)
            .verticalScroll(rememberScrollState())
    ) {

        textType.forEach{
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = CenterVertically
            ){
                val checkedState = remember { mutableStateOf(true) }
                Text(it)
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Switch(
                        checked = checkedState.value,
                        onCheckedChange = { checkedState.value = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF80C6F6)),
                        modifier = Modifier.align(Alignment.End),
                        interactionSource = MutableInteractionSource()
                    )
                }
            }
            TextListSpacer()
        }
    }
}


@Composable
fun CardItemSpacer() {
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
}

@Composable
fun TextListSpacer() {
    Divider(thickness = 2.dp, modifier = Modifier.padding(top = 12.dp, bottom = 12.dp))
}

@Composable
fun SubSettingTitle(titleName:String) {
    Text(text = titleName,
        fontWeight = FontWeight.W900,
        style = MaterialTheme.typography.h6)
}

@Composable
fun CardBackgroundStyle(content: @Composable()() -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        color = Color(128, 198, 246),
        elevation = 14.dp
    ) {
        content()
    }
}