package com.compose.onetextbuilder.components

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.onetextbuilder.R
import com.compose.onetextbuilder.UiState
import com.compose.onetextbuilder.home.CardButtons
import com.nthily.simpletextfield.SearchTextField

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