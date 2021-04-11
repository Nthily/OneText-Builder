package com.compose.onetextbuilder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UiState: ViewModel() {

    var result by mutableStateOf("")
    var flag by mutableStateOf(false)

}