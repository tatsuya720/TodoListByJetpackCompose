package com.example.todolistbyjetpackcompose.ui.notDoneList

import android.graphics.drawable.GradientDrawable
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolistbyjetpackcompose.R
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun NotDoneListItem(title: String, description: String) {

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
            ButtonArea(buttonWidth = iconBtnSize)

            ListItemArea(title = title, description = description, offsetX = swipeableState.offset.value.roundToInt())
        }
    }
}

@Composable
fun ButtonArea(buttonWidth: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically

    ){
      IconButton( onClick = { /*TODO*/ },
          modifier = Modifier
              .width(buttonWidth)
              .fillMaxHeight()
              .background(Color(red = 0, green = 200, blue = 0))
      ) {
          Icon(Icons.Default.Check, contentDescription = "完了にする")
      }
      IconButton( onClick = { /*TODO*/ },
          modifier = Modifier
              .width(buttonWidth)
              .fillMaxHeight()
              .background(Color(red = 200, green = 0, blue = 0))
      ) {
          Icon(painter = painterResource(id = R.drawable.delete_icon), contentDescription = "削除")
      }
    }
}

@ExperimentalMaterialApi
@Composable
fun ListItemArea(title: String, description: String, offsetX: Int) {
    Column(
        modifier = Modifier
            .offset {
                IntOffset(offsetX,0)
            }
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(red = 220, green = 220, blue = 220))
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable { }

    ) {
        Text(text = title,
            modifier = Modifier.width(400.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = description,
            modifier = Modifier.fillMaxWidth(),
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            fontFamily = FontFamily.SansSerif,
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun PreviewListItem() {
    NotDoneListItem("ああああああああああああああああああああ", "さんぷるさんぷるさんぷるさんぷるさんぷるさんぷるさんぷるサるサンプルさんp流サンプルさんぷるさんっぷるサンプルサンプルサンプルサンプルさんぷるさんぷるさんぷる")
}

@ExperimentalMaterialApi
@Composable
@Preview
fun PreviewButtonArea() {
    BoxWithConstraints {
        val screenWidth = with(LocalDensity.current) { maxWidth.toPx() }
        val swipeSize = (screenWidth / 3)
        val iconBtnSize = (swipeSize / 2)
        //ButtonArea(iconBtnSize)
    }
}