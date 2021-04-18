package com.compose.onetextbuilder

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import com.compose.onetextbuilder.ui.theme.OneTextBuilderTheme
import com.compose.onetextbuilder.home.Home


class MainActivity : ComponentActivity() {

    private val viewModel:UiState by viewModels()

    @SuppressLint("CoroutineCreationDuringComposition")
    @ExperimentalAnimationApi
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OneTextBuilderTheme {
                Home(viewModel)
            }
        }
    }
}
