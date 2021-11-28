package com.example.todolistbyjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LifecycleOwner
import com.example.todolistbyjetpackcompose.commonViewModel.ListViewModel
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import com.example.todolistbyjetpackcompose.ui.doneList.DoneList
import com.example.todolistbyjetpackcompose.ui.notDoneList.NotDoneList
import com.example.todolistbyjetpackcompose.ui.theme.TodoListByJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LifecycleOwner {

    private val listViewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(listViewModel)
        setContent {
            TodoListByJetpackComposeTheme {
                MainView(listViewModel = listViewModel)
            }
        }
    }
}


@Composable
fun MainView(listViewModel: ListViewModel) {
    val selectedIndex by listViewModel.listState.observeAsState(TodoState.NotDone)
    val itemList by listViewModel.todo.observeAsState(listOf())
    Column {
        MenuTab(selectedIndex = selectedIndex.value) {
            listViewModel.stateChange(TodoState.from(it))
        }
        ListView(selectedIndex.value, itemList)
    }


}

@Composable
fun MenuTab(selectedIndex: Int, onClick: (Int) -> Unit) {
    TabRow(selectedTabIndex = selectedIndex,
        modifier = Modifier.height(30.dp),
        backgroundColor = Color.White
    ) {
        Tab(selected = (selectedIndex == 0), onClick = { onClick(0) }) {
            Text(text = "Todo")
        }
        Tab(selected = (selectedIndex == 1), onClick = { onClick(1) }) {
            Text(text = "Done")
        }
    }

}

@Composable
fun ListView(selectedIndex: Int, itemList: List<TodoItem>) {
    if(selectedIndex == 0) {
        NotDoneList(itemList)
    } else {
        DoneList(itemList)
    }
}

@Preview
@Composable
fun MenuTabPreview() {
    //MainView()
}