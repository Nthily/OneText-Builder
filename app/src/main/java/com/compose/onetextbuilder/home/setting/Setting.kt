package com.compose.onetextbuilder.home.setting

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Icon
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.compose.onetextbuilder.R
import com.compose.onetextbuilder.UiState

@Composable
fun Setting(viewModel:UiState){
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        UserInfo()
        SimpleSettingList(viewModel)
    }
}


@Composable
fun UserInfo(){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(bottomStart = 45.dp, bottomEnd = 45.dp),
        elevation = 14.dp
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFF80C6F6))
                .padding(25.dp)
                .height(100.dp),
            verticalAlignment = CenterVertically
        ){
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(65.dp)
            ) {
                Image(painterResource(id = R.drawable.shiro), null)
            }
            Spacer(Modifier.padding(horizontal = 8.dp))
            Column() {
                Text(
                    text = "香辣鸡腿堡",
                    fontWeight = FontWeight.W900,
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                Text(
                    text = "已看了 5220 句",
                    fontWeight = FontWeight.W900,
                    style = MaterialTheme.typography.body2,
                    color = Color.White
                )
            }
        }
    }
    CardItemSpacer()
}


@Composable
fun SimpleSettingList(
    viewModel: UiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ){
        Text(
            text = "基本设置",
            fontWeight = FontWeight.W900,
            modifier = Modifier
                .padding(8.dp)
        )
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
            elevation = 14.dp

        ) {
            Column() {
                SentenceType(viewModel)
                FontType(viewModel)
            }
        }
        CardItemSpacer()
        Text(
            text = "其他",
            fontWeight = FontWeight.W900,
            modifier = Modifier
                .padding(8.dp)
        )
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
            elevation = 14.dp

        ) {
            About(viewModel)
        }
    }
}

@Composable
fun SentenceType(viewModel: UiState){
    ItemTemplate("句子类型", R.drawable.sell_black_24dp,
    viewModel = viewModel)
}

@Composable
fun FontType(viewModel: UiState){
    ItemTemplate("字体选择", R.drawable.text_format_black_24dp,
    modifier = Modifier.scale(1.4f),viewModel)
}

@Composable
fun About(viewModel: UiState) {
    ItemTemplate("关于项目（欢迎 Star）", R.drawable.github,
    viewModel = viewModel)
}

@Composable
fun TextSpacer() {
    Spacer(modifier = Modifier.padding(horizontal = 8.dp))
}

@Composable
fun CardItemSpacer() {
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
}


@Composable
fun CardBackgroundStyle(content: @Composable()() -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = Color.White
    ) {
        content()
    }
}

@Composable
fun ItemTemplate(
    name:String,
    icon:Int,
    modifier: Modifier = Modifier,
    viewModel:UiState
){
    CardBackgroundStyle {
        Row(
            modifier = Modifier
                .clickable {
                    when(true) {
                        name == "字体选择" -> {
                            viewModel.requestSelectFont = true
                        }
                    }
                }
                .padding(15.dp),
            verticalAlignment = CenterVertically
        ){
            Surface(
                shape = CircleShape,
                color = Color(0xFFEDEDED)
            ) {
                Icon(painterResource(icon), null,
                    modifier = modifier
                        .padding(10.dp))
            }
            TextSpacer()
            Text(
                text = name,
                fontWeight = FontWeight.W700
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Icon(Icons.Filled.KeyboardArrowRight, null)
            }
        }
    }
}