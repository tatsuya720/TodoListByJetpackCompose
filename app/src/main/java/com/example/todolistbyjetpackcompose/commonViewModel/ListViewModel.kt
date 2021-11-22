package com.example.todolistbyjetpackcompose.commonViewModel

import androidx.lifecycle.*
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.repository.ListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val listRepository: ListRepository
): ViewModel(), DefaultLifecycleObserver {
//    private val _list = MutableLiveData<List<TodoItem>>()
//    val todo: LiveData<List<TodoItem>> = listRepository.loadList()

    //private val _listRepository = ListRepository()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        viewModelScope.launch {
            listRepository.loadList()
        }

    }

    fun loadList() {
     //   _listRepository.loadList()
    }

    fun add() {

    }

    fun stateChange() {

    }

}