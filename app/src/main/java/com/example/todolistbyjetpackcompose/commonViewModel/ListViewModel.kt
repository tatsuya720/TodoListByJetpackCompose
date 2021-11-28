package com.example.todolistbyjetpackcompose.commonViewModel

import androidx.lifecycle.*
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listRepository: ListRepository
): ViewModel(), DefaultLifecycleObserver {
    val todo: LiveData<List<TodoItem>> = listRepository.loadList().asLiveData()
    
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    fun loadList() {
     //   _listRepository.loadList()
    }

    fun add() {

    }

    fun stateChange() {

    }

}