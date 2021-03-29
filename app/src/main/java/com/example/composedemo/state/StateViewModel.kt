package com.example.composedemo.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateViewModel: ViewModel() {

    private val _count: MutableLiveData<Int> = MutableLiveData()
    val count = _count as LiveData<Int>

    init {
        _count.value = 0
    }

    fun increase() {
        _count.value = _count.value!! + 1
    }

}