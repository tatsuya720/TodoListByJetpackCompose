package com.example.todolistbyjetpackcompose.ui.Edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import com.example.todolistbyjetpackcompose.repository.EditRepository
import com.example.todolistbyjetpackcompose.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(
    private val editRepository: EditRepository,
    private val listRepository: ListRepository
): ViewModel() {

    private val mutableTodoId = MutableStateFlow<Long>(-1)
    //検索にひっかあからなかったら通知しない
    val todoItem: LiveData<TodoItem> = mutableTodoId.flatMapLatest { id ->
        listRepository.findById(id)
    }.filterNotNull().asLiveData()

    fun add(title: String, description: String) {
        val todoItem = TodoItem(id = 0, title= title, description = description, state = TodoState.NotDone)
        add(todoItem)
    }

    fun update(id: Long, title: String, description: String, state: TodoState) {
        val todoItem = TodoItem(id = id, title = title, description = description, state = state)
        viewModelScope.launch {
            editRepository.update(todoItem = todoItem)
            listRepository.loadList(todoState = TodoState.NotDone)
        }
    }

    fun add(todoItem: TodoItem) {
        viewModelScope.launch {
            editRepository.add(todoItem = todoItem)
            listRepository.loadList(todoState = TodoState.NotDone)
        }
    }

    fun setSearchId(id: Long) {
        mutableTodoId.value = id
    }

}