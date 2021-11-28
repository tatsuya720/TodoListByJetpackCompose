package com.example.todolistbyjetpackcompose.commonViewModel

import androidx.lifecycle.*
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import com.example.todolistbyjetpackcompose.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listRepository: ListRepository
): ViewModel(), DefaultLifecycleObserver {
    private val mutableListState = MutableStateFlow(TodoState.NotDone)
    val listState = mutableListState.asLiveData()

    val todo: LiveData<List<TodoItem>> = mutableListState.flatMapLatest { todoState ->
        listRepository.loadList(todoState)
    }.asLiveData()


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    fun loadList() {
     //   _listRepository.loadList()
    }

    fun add() {

    }

    fun stateChange(state: TodoState) {
        mutableListState.value = state
    }

}