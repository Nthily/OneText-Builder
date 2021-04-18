package com.compose.onetextbuilder.home.setting

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.compose.onetextbuilder.*
import com.compose.onetextbuilder.R
import com.compose.onetextbuilder.home.favorite.Favorite
import com.compose.onetextbuilder.home.setting.Setting
import com.compose.onetextbuilder.utils.SwipeToRefreshLayout
import com.compose.onetextbuilder.utils.percentOffsetX
import kotlinx.coroutines.delay
import kotlin.math.roundToInt


@Composable
fun FontSelection(viewModel: UiState) {
    val percentOffsetX = animateFloatAsState(if (viewModel.requestSelectFont) 0f else 1f)
    Box(
        modifier = Modifier.percentOffsetX(percentOffsetX.value)
    ){
        Scaffold(
            content = {
                FontList(viewModel)
            },
            backgroundColor = Color(0xFFF4F6FC),
            topBar = { FontListTopBar(viewModel) }
        )
    }
}

@Composable
fun FontListTopBar(
    viewModel: UiState
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = {
                viewModel.requestSelectFont = false
            }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        },
        backgroundColor = Color(0xFFF4F6FC)
    )
}


@Composable
fun FontList(viewModel: UiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ){
        Text(
            text = "字体选择",
            fontWeight = FontWeight.W900,
            modifier = Modifier
                .padding(8.dp)
        )
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
            elevation = 14.dp
        ) {
            Column{
                FontTemplate("默认字体", null, viewModel)
                FontTemplate("Zool Kuaile", FontFamily(
                    Font(R.font.zoolkuaile)
                ),viewModel)
            }
        }
    }
}

@Composable
fun FontTemplate(
    fontName:String,
    fontFamily: FontFamily? = null,
    viewModel:UiState
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .clickable {
                viewModel.currentFont = fontName
            }
            .background(Color.White)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = fontName,
            fontWeight = FontWeight.W900,
            fontFamily = fontFamily
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            if(fontName == viewModel.currentFont)  Icon(Icons.Filled.Done, null)
        }
    }
}