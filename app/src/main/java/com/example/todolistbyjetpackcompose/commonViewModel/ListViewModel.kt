package com.example.todolistbyjetpackcompose.commonViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistbyjetpackcompose.model.ListItem
import com.example.todolistbyjetpackcompose.repository.ListRepository

class ListViewModel: ViewModel() {
    private val _list = MutableLiveData<List<ListItem>>()
    val list: LiveData<List<ListItem>> = _list

    private val _listRepository = ListRepository()

    fun loadList() {
        _listRepository.loadList()
    }

    fun add() {

    }

    fun stateChange() {

    }

}