package com.compose.onetextbuilder.components

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Tags() {
    TagTemplate(tagName = "动漫", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "漫画", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "游戏", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "文学", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "原创", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "来自网络", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "其他", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "影视", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "诗词", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "网易云", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "哲学", color = Color(0xFF80C6F6))
    TagTemplate(tagName = "抖机灵", color = Color(0xFF80C6F6))
}

@Composable
fun TagTemplate(
    tagName:String,
    color: Color
) {
    Surface(
        color = color,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = tagName,
            modifier = Modifier
                .padding(15.dp),
            fontWeight = FontWeight.W900
        )
    }
    Spacer(Modifier.padding(horizontal = 8.dp))
}