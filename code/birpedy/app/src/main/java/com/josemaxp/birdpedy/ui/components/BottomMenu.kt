package com.josemaxp.birdpedy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Map
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.josemaxp.birdpedy.R

@Composable
fun CustomizeBottomMenu(
    modifier: Modifier,
    onClick0: () -> Unit = {},
    onClick1: () -> Unit = {},
    selected: MutableIntState
){
    val animate0 = remember { mutableStateOf(false) }
    val animate1 = remember { mutableStateOf(false) }
    val scale0 = customizeAnimation(animate0)
    val scale1 = customizeAnimation(animate1)

    val shape0 = customMenuRoundedCorner(
        selectedItem = selected.intValue,
        shapeNumber = 0,
        totalShapes = 2,
        position = "bottom"
    )
    val shape1 = customMenuRoundedCorner(
        selectedItem = selected.intValue,
        shapeNumber = 1,
        totalShapes = 2,
        position = "bottom"
    )

    Row (
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent),
        verticalAlignment = Alignment.Bottom
    ){
        CustomizeImageButton(
            onClick = {
                onClick0()
                selected.intValue = 0
                animate0.value = true
                animate1.value = false
            },
            modifier = Modifier.weight(1f),
            selectedItem = selected,
            icon = R.drawable.bird,
            iconDescription =  "",
            scale = scale0,
            number = 0,
            shape = shape0
        )
        CustomizeIconButton(
            onClick = {
                onClick1()
                selected.intValue = 1
                animate0.value = false
                animate1.value = true
                      },
            modifier = Modifier.weight(1f),
            selectedItem = selected,
            icon = Icons.Rounded.Map,
            iconDescription = "",
            scale = scale1,
            number = 1,
            shape = shape1
        )
    }
}