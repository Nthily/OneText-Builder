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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController


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
                .wrapContentHeight(),
            color = Color.White,
            elevation = 14.dp
        ){
            LazyColumn() {
                item{
                    Column(
                        modifier = Modifier.padding(15.dp)
                    ) {
                        UserInfo()
                        CardSpacer()
                        TextCategory()
                    }
                }
            }
        }
    }
}

@Composable
fun CardSpacer() {
  //  Divider(thickness = 2.dp, modifier = Modifier.padding(top = 18.dp, bottom = 18.dp))
    Spacer(modifier = Modifier.padding(vertical = 18.dp))
}

@Composable
fun UserInfo(){
    Column() {
        Text(text = "设置中心",
            fontWeight = FontWeight.W900,
            style = MaterialTheme.typography.h5)
        Spacer(Modifier.padding(vertical = 8.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFF80C6F6),
            elevation = 14.dp
        ) {
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
                    modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }
}


@Composable
fun TextListSpacer() {
    Divider(thickness = 2.dp, modifier = Modifier.padding(top = 12.dp, bottom = 12.dp))
}

@Composable
fun TextCategory() {
    Column() {
        Text(text = "句子类型",
            fontWeight = FontWeight.W900,
            style = MaterialTheme.typography.body1)
        Spacer(Modifier.padding(vertical = 8.dp))
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            TextType()
        }
    }
}

@Composable
fun TextType(){
    val radioOptions = listOf(
        "全部",
        "动漫",
        "漫画",
        "网络",
        "文学",
        "哲学",
        "其他")

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0] ) }
    radioOptions.forEach{
        Row(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .selectable(
                    selected = (it == selectedOption),
                    onClick = {

                    }
                )
        ) {
            Text(text = it,
                style = MaterialTheme.typography.body1)
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                RadioButton(
                    selected = (it == selectedOption),
                    onClick = { onOptionSelected(it) },
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
        TextListSpacer()
    }
}