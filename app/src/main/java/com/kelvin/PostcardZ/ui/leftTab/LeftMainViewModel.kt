package com.kelvin.PostcardZ.ui.leftTab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LeftMainViewModel: ViewModel() {
    private val _label = MutableLiveData<String>()
    val label: LiveData<String> = _label

    /**
     * Do not let other classes change the mutable liva data directly, make helper methods
     */
    fun setLabelId(id: String) {
        if (id.isNotBlank()) {
            _label.value = id
        }
    }
}