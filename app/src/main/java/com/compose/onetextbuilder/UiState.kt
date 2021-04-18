package com.compose.onetextbuilder

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class UiState: ViewModel() {

    var result by mutableStateOf("")
    var flag by mutableStateOf(false)

    var enableRed by mutableStateOf(false)
    var selectedItem by mutableStateOf(0)

    var currentPage by mutableStateOf("hitokoto")

    var currentFont by mutableStateOf("默认字体")

    var requestSelectFont by mutableStateOf(false)

}