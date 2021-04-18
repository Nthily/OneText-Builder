package com.compose.onetextbuilder.home.setting

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

@Composable
fun Setting(){
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        UserInfo()
        SimpleSettingList()
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
fun SimpleSettingList() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ){
        SentenceType()
        Divider(thickness = 1.dp)
        FontType()
        CardItemSpacer()
        About()
    }
}

@Composable
fun SentenceType(){
    ItemTemplate("句子类型", R.drawable.sell_black_24dp)
}

@Composable
fun FontType(){
    ItemTemplate("字体选择", R.drawable.text_format_black_24dp,
    modifier = Modifier.scale(1.4f))
}

@Composable
fun About() {
    ItemTemplate("关于项目（欢迎 Star）", R.drawable.github)
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
    modifier: Modifier = Modifier
){
    CardBackgroundStyle {
        Row(
            modifier = Modifier
                .clickable {

                }
                .padding(15.dp),
            verticalAlignment = CenterVertically
        ){
            Icon(painterResource(icon), null,
                modifier = modifier)
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