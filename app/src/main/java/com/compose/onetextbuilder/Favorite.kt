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
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nthily.simpletextfield.SearchTextField


@ExperimentalAnimationApi
@Composable
fun Favorite(viewModel:UiState, navController: NavHostController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ){

        item{
            TopInfo()
        }
        item{
            SentenceCardTemplate(viewModel)
            SentenceCardTemplate(viewModel)
            SentenceCardTemplate(viewModel)
            SentenceCardTemplate(viewModel)
            SentenceCardTemplate(viewModel)
            SentenceCardTemplate(viewModel)
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun TopInfo() {
    var text by remember{ mutableStateOf("")}
    Row(
        verticalAlignment = CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 15.dp)
    ){
        AnimatedVisibility(visible = !text.isNotBlank()) {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(45.dp)
            ) {
                Image(painterResource(id = R.drawable.shiro), null)
            }        }

        SearchTextField(value = text, onValueChange = {
            text = it
        },closeOnclick = {
            text = ""
        }, modifier = Modifier
            .scale(1.26f)
            .fillMaxWidth()
            .animateContentSize(),
        placeholder = {
            Text("搜索")
        })
    }
}

@ExperimentalAnimationApi
@Composable
fun SentenceCardTemplate(viewModel: UiState) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp),
        color = Color.White,
        elevation = 10.dp
    ){
        Column(
            modifier = Modifier.clickable{

            }
        ){
            CollectedSentence()
            Row(modifier = Modifier.fillMaxHeight(),verticalAlignment = Alignment.Bottom){
                //Shiro()
                CardButtons(viewModel)
            }
        }
    }
}


@Composable
fun CollectedSentence() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp, top = 15.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("“", style = MaterialTheme.typography.h4, modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp))
        }
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
                        append("这是一个测试" +
                                "这是一个测试" +
                                "这是一个测试" +
                                "这是一个测试" +
                                "这是一个测试" +
                                "这是一个测试" +
                                "这是一个测试这是一个测试" +
                                "这是一个测试" +
                                "这是一个测试")
                    }
                },
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp),
                style = MaterialTheme.typography.subtitle2,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("”", style = MaterialTheme.typography.h4, modifier = Modifier
                .align(Alignment.End)
                .padding(end = 20.dp))
        }
    }
}