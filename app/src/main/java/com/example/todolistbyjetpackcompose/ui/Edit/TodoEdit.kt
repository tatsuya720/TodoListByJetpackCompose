package com.example.todolistbyjetpackcompose.ui.Edit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.todolistbyjetpackcompose.model.EditState
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import timber.log.Timber

@Composable
fun TodoEdit(id: Long = 0, editState: EditState, viewModel: EditViewModel, onClickBack: () -> Unit) {

    val todoItem = viewModel.todoItem.observeAsState(initial = TodoItem(id=-1, title="タイトル", description = "", state=TodoState.NotDone))
    if(todoItem.value.id == -1L) {
        return
    }

    val maxTitleLen = 10
    val maxDescriptionLen = 200

    val titleText = rememberSaveable {
     mutableStateOf(todoItem.value.title)
    }

    Column {
        TopBar(titleText = titleText.value, editState = editState, onClickBack = onClickBack,
            onClickSave = {
                if(editState == EditState.Edit) {
                    viewModel.update(id = id, title = titleText.value, description = todoItem.value.description, state = todoItem.value.state)
                } else {
                    viewModel.add(title = titleText.value, description = todoItem.value.description)
                }
                onClickBack()
            },
            onChangeText = {
                //TODO: 意図した動作になっていないので修正予定
                if(it.length <= maxTitleLen) {
                    titleText.value = it
                }
            }
        )
        DescriptionEdit(descriptionText = todoItem.value.description, editState = editState) {
            //TODO: 意図した動作になっていないので修正予定
            if(it.length <= maxDescriptionLen) {
                todoItem.value.description = it
            }
        }
    }
}


@Composable
fun TopBar(titleText: String, editState: EditState, onClickBack: () -> Unit, onClickSave: () -> Unit, onChangeText: (String) -> Unit) {
    Row (modifier = Modifier
        .background(color = Color.LightGray)
        .fillMaxWidth()
    ){
        IconButton(onClick = { onClickBack() }) {
            Icon(Icons.Default.ArrowBack, "戻る")
        }
        TextField(
            value = titleText,
            onValueChange = {
                onChangeText(it)
            },
            singleLine = true,
            textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold)
        )
        Button(onClick = { onClickSave() },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text("保存")
        }
    }
}

@Composable
fun DescriptionEdit(descriptionText: String, editState: EditState, onChangeText: (String) -> Unit) {
    TextField(value = descriptionText,
        onValueChange = {
            onChangeText(it)
        },
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    )
}

@Preview
@Composable
fun PrevTodoEdit() {
//    TodoEdit("", "", EditState.Add) {
//
//    }
}