package com.example.todolistbyjetpackcompose.commonViewModel

import androidx.lifecycle.*
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import com.example.todolistbyjetpackcompose.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

    @ExperimentalCoroutinesApi
    val todo: LiveData<List<TodoItem>> = mutableListState.flatMapLatest { todoState ->
        listRepository.loadList(todoState)
    }.asLiveData()

    init {
        initSampleData()
        stateChange(TodoState.NotDone)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
    }

    fun loadList() {
     //   _listRepository.loadList()
    }

    fun add(title: String, description: String, todoState: TodoState) {
        viewModelScope.launch {
            listRepository.insert(title = title, description = description, state = todoState)
        }
    }

    fun stateChange(state: TodoState) {
        mutableListState.value = state
    }


    private fun initSampleData() {
        viewModelScope.launch {
            listRepository.deleteAll()
            listRepository.insert("aaaaaa", "bbbbbbb", TodoState.NotDone)
            listRepository.insert("bbbbbb", "ccccccc", TodoState.Done)
        }
    }
}