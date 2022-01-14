package com.example.todolistbyjetpackcompose.ui.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily.Companion.SansSerif
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistbyjetpackcompose.model.TodoItem
import com.example.todolistbyjetpackcompose.model.TodoState
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun TodoListItem(todoItem: TodoItem, listOnClick: (todoItem: TodoItem) -> Unit ) {
    val swipeableState = rememberSwipeableState(initialValue = 0)

    BoxWithConstraints {
        val screenWidth = maxWidth
        val swipeSize = (screenWidth / 3)
        val iconBtnSize = (swipeSize/2)
        val swipeSizePx = with(LocalDensity.current){ swipeSize.toPx() }
        val anchors = mapOf(0f to 0, swipeSizePx * -1 to 1)

        Box(
            modifier = Modifier
                .height(80.dp)
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.3f) },
                    orientation = Orientation.Horizontal
                )
        ) {
            ButtonArea(buttonWidth = iconBtnSize, todoItem.state)

            ListItemArea(title = todoItem.title, description = todoItem.description, offsetX = swipeableState.offset.value.roundToInt()) {
                listOnClick(todoItem)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ListItemArea(title: String, description: String, offsetX: Int, listOnClick: () -> Unit) {
    Column(
        modifier = Modifier
            .offset {
                IntOffset(offsetX, 0)
            }
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(red = 220, green = 220, blue = 220))
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable {
                listOnClick()
            }

    ) {
        Text(text = title,
            modifier = Modifier.width(400.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontFamily = SansSerif,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = description,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            fontFamily = SansSerif,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Composable
fun ButtonArea(buttonWidth: Dp, todoState: TodoState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically

    ){
        if(todoState == TodoState.NotDone) {
            IconButton( onClick = { /*TODO*/ },
                modifier = Modifier
                  .width(buttonWidth)
                  .fillMaxHeight()
                  .background(Color(red = 0, green = 200, blue = 0))
            ) {
                Icon(Icons.Default.Check, contentDescription = "完了にする")
            }
        } else {
            IconButton( onClick = { /*TODO*/ },
                modifier = Modifier
                  .width(buttonWidth)
                  .fillMaxHeight()
                  .background(Color(red = 0, green = 200, blue = 0))
            ) {
                //TODO: Iconを変える
                Icon(Icons.Default.Delete, contentDescription = "未完了にする")
            }
        }


        IconButton( onClick = { /*TODO*/ },
            modifier = Modifier
              .width(buttonWidth)
              .fillMaxHeight()
              .background(Color(red = 200, green = 0, blue = 0))
        ) {
            Icon(Icons.Default.Delete, contentDescription = "削除")
        }
    }
}