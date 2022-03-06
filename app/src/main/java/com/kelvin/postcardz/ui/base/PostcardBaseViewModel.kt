package com.kelvin.postcardz.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.lang.Exception

abstract class PostcardBaseViewModel: ViewModel() {
    protected val _networkError = MutableSharedFlow<Exception?>()
    val networkError = _networkError.asSharedFlow()
}