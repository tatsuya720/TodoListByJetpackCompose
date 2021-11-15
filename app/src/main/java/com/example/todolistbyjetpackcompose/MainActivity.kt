package com.example.todolistbyjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistbyjetpackcompose.ui.doneList.DoneList
import com.example.todolistbyjetpackcompose.ui.notDoneList.NotDoneList
import com.example.todolistbyjetpackcompose.ui.theme.TodoListByJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListByJetpackComposeTheme {
                MainView()
            }
        }
    }
}


@Composable
fun MainView() {
    var selectedIndex by remember { mutableStateOf(0) }

    Column {
        MenuTab(selectedIndex = selectedIndex) {
            selectedIndex = it
        }
        ListView(selectedIndex)
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
fun ListView(selectedIndex: Int) {
    if(selectedIndex == 0) {
        NotDoneList()
    } else {
        DoneList()
    }
}

@Preview
@Composable
fun MenuTabPreview() {
    MainView()
}