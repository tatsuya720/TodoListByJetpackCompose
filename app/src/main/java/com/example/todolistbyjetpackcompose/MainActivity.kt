package com.example.todolistbyjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistbyjetpackcompose.commonViewModel.ListViewModel
import com.example.todolistbyjetpackcompose.model.EditState
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import com.example.todolistbyjetpackcompose.ui.Edit.EditViewModel
import com.example.todolistbyjetpackcompose.ui.Edit.TodoEdit
import com.example.todolistbyjetpackcompose.ui.doneList.DoneList
import com.example.todolistbyjetpackcompose.ui.notDoneList.NotDoneList
import com.example.todolistbyjetpackcompose.ui.theme.TodoListByJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity(), LifecycleOwner {

    private val listViewModel: ListViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(listViewModel)
        setContent {
            TodoListByJetpackComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main") {
                    composable(route = "main") {
                        MainView(listViewModel = listViewModel, navController = navController)
                    }
                    composable(route = "edit?id={id}&editState={editState}",
                        arguments = listOf(
                            navArgument("id") {
                                defaultValue = 0
                                type = NavType.LongType
                            },
                            navArgument("editState") {
                                defaultValue = EditState.Add.value
                                type = NavType.IntType
                            }
                        )
                    ) {
                        val id = it.arguments?.getLong("id") ?: 0
                        val editState = it.arguments?.getInt("editState") ?: 0


                        val viewModel: EditViewModel by viewModels()
                        viewModel.setSearchId(id)
                        TodoEdit(id = id, editState = EditState.from(editState), viewModel = viewModel) {
                            navController.popBackStack()
                        }
                    }
                }
            }
        }
    }
}


@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun MainView(listViewModel: ListViewModel, navController: NavController) {
    val selectedIndex by listViewModel.listState.observeAsState(TodoState.NotDone)
    val itemList by listViewModel.todo.observeAsState(listOf())
    Column {
        MenuTab(selectedIndex = selectedIndex.value) {
            listViewModel.stateChange(TodoState.from(it))
        }
        ListView(selectedIndex.value, itemList, navController = navController, listViewModel = listViewModel)
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

@ExperimentalMaterialApi
@Composable
fun ListView(selectedIndex: Int, itemList: List<TodoItem>, navController: NavController, listViewModel: ListViewModel) {
    if(selectedIndex == 0) {
        NotDoneList(itemList,
            onClickAddBtn = {
                navController.navigate("edit?editState=${EditState.Add.value}") {
                    popUpTo("main")
                }
            },
            onClickListItem = {
                navController.navigate("edit?id=${it.id}&editState=${EditState.Edit.value}") {
                    popUpTo("main")
                }
            }
        )
    } else {
        DoneList(itemList) {
            navController.navigate("edit?id=${it.id}&title=${it.title}&description=${it.description}&todoState=${it.state.value}&editState=${EditState.Edit.value}") {
                popUpTo("main")
            }
        }
    }
}

@Preview
@Composable
fun MenuTabPreview() {
    //MainView()
}