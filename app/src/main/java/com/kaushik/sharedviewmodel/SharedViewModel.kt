package com.kaushik.sharedviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private var _name = MutableLiveData("Kaushik") // giving it a default value
    val name: MutableLiveData<String> = _name

    fun saveName(name: String) {
        _name.value = name
    }

}