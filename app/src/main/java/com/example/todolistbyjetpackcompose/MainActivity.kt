package com.example.todolistbyjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolistbyjetpackcompose.ui.theme.TodoListByJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListByJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TodoListByJetpackComposeTheme {
        Greeting("Android")
    }
}

@Composable
fun MainView() {
    var selectedIndex by remember { mutableStateOf(0) }

    MenuTab(selectedIndex = selectedIndex) {
        selectedIndex = it
    }
}

@Composable
fun MenuTab(selectedIndex: Int, onClick: (Int) -> Unit) {
    TabRow(selectedTabIndex = selectedIndex,
        modifier = Modifier.height(30.dp), 
        backgroundColor = Color.White
    ) {
        Tab(selected = (selectedIndex == 0), onClick = { onClick(0) }) {
            Text( text = "未完了")
        }
        Tab(selected = (selectedIndex == 1), onClick = { onClick(1) }) {
            Text(text = "完了")
        }
    }
}

@Preview
@Composable
fun MenuTabPreview() {
    MainView()
}