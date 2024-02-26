package com.josemaxp.birdpedy.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.josemaxp.birdpedy.functions.generalFontFamily
import com.josemaxp.birdpedy.functions.generalFontStyle


/*************** FUNCIONES PARA MENÚS********************/
fun customMenuRoundedCorner(selectedItem: Int, shapeNumber: Int, totalShapes: Int, position: String, roundedShapeDp: Dp = 25.dp): RoundedCornerShape {
    val otherRounded = RoundedCornerShape(0.dp)
    var selectedRounded = RoundedCornerShape(topEnd = roundedShapeDp, topStart = roundedShapeDp)
    var leftRounded = RoundedCornerShape(bottomEnd = roundedShapeDp)
    var rightRounded = RoundedCornerShape(bottomStart = roundedShapeDp)
    if(position == "bottom"){
        selectedRounded = RoundedCornerShape(bottomStart = roundedShapeDp, bottomEnd = roundedShapeDp)
        leftRounded = RoundedCornerShape(topEnd = roundedShapeDp)
        rightRounded = RoundedCornerShape(topStart = roundedShapeDp)
    }

    when (selectedItem) {
        0 -> {
            return when (shapeNumber) {
                0 -> {
                    selectedRounded
                }

                1 -> {
                    rightRounded
                }

                else -> {
                    otherRounded
                }
            }
        }
        in 1..<totalShapes-1 -> {
            return when (shapeNumber) {
                selectedItem-1 -> {
                    leftRounded
                }

                selectedItem -> {
                    selectedRounded
                }

                selectedItem+1 -> {
                    rightRounded
                }

                else -> {
                    otherRounded
                }
            }
        }
        else -> {
            return when (shapeNumber) {
                selectedItem-1 -> {
                    leftRounded
                }

                selectedItem -> {
                    selectedRounded
                }

                else -> {
                    otherRounded
                }
            }
        }
    }
}

@Composable
fun customizeAnimation(animate: MutableState<Boolean>): State<Float> {
    return animateFloatAsState(
        targetValue = if (animate.value) 1.2f else 1f,
        animationSpec = keyframes {
            durationMillis = 500
            1.2f at 150
        }, label = ""
    )
}

@Composable
fun CustomizeIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    selectedItem: MutableIntState,
    icon: ImageVector,
    iconDescription: String,
    scale: State<Float>,
    number: Int,
    shape: Shape = RoundedCornerShape(0.dp)
){
    val iconYOffset = remember { mutableStateOf(0.dp) }

    if(selectedItem.intValue == number){
        iconYOffset.value = (-5).dp
    }else{
        iconYOffset.value = 0.dp
    }

    Row (modifier = modifier.background(setBgColor(number, selectedItem.intValue))){
        IconButton(
            onClick = onClick,
            modifier = modifier
                .offset(y = iconYOffset.value)
                .background(
                    color = setIconColor(number, selectedItem.intValue),
                    shape = shape
                )
        )
        {
            Icon(
                icon,
                contentDescription = iconDescription,
                tint = setBgColor(number, selectedItem.intValue),
                modifier = Modifier
                    .scale(scale.value)
            )
        }
    }
}

@Composable
fun CustomizeImageButton(
    onClick: () -> Unit,
    modifier: Modifier,
    selectedItem: MutableIntState,
    icon: Int,
    iconDescription: String,
    scale: State<Float>,
    number: Int,
    shape: Shape = RoundedCornerShape(0.dp)
){
    val iconYOffset = remember { mutableStateOf(0.dp) }

    if(selectedItem.intValue == number){
        iconYOffset.value = (-5).dp
    }else{
        iconYOffset.value = 0.dp
    }

    Row (
        modifier = modifier
            .background(setBgColor(number, selectedItem.intValue))
    ){
        IconButton(
            onClick = onClick,
            modifier = modifier
                .offset(y = iconYOffset.value)
                .background(
                    color = setIconColor(number, selectedItem.intValue),
                    shape = shape
                )
        )
        {
            Image(
                painter = painterResource(id = icon),
                contentDescription = iconDescription,
                colorFilter = ColorFilter.tint(setBgColor(number, selectedItem.intValue)),
                modifier = Modifier
                    .size(25.dp)
                    .scale(scale.value)
            )
        }
    }
}

@Composable
fun CustomizeTextIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    selectedItem: MutableIntState,
    text: String,
    number: Int,
    shape: Shape = RoundedCornerShape(0.dp)
){

    Row (
        modifier = modifier
            .background(setBgColor(number, selectedItem.intValue))
    ){
        IconButton(
            onClick = onClick,
            modifier = modifier
                .background(
                    color = setIconColor(number, selectedItem.intValue),
                    shape = shape
                )
        )
        {
            Text(
                text = text,
                color = setBgColor(number, selectedItem.intValue),
                modifier = Modifier
            )
        }
    }
}

@Composable
fun setBgColor(number: Int, selected: Int): Color {
    val colorNoSelected = MaterialTheme.colorScheme.background
    val colorSelected = MaterialTheme.colorScheme.primary

    return if(number == selected){
        colorSelected
    }else{
        colorNoSelected
    }
}

@Composable
fun setIconColor(number: Int, selected: Int): Color {
    val colorNoSelected = MaterialTheme.colorScheme.primary
    val colorSelected = MaterialTheme.colorScheme.background

    return if(number == selected){
        colorSelected
    }else{
        colorNoSelected
    }
}

/***********************************/

@Composable
fun GeneralText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = generalFontStyle(),
    color: Color = MaterialTheme.colorScheme.primary,
    fontFamily: FontFamily = generalFontFamily(),
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 10,
){
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontFamily = fontFamily,
        overflow = TextOverflow.Ellipsis,
        style = style,
        textAlign = textAlign,
        maxLines = maxLines
    )
}

@Composable
fun GeneralButtonIcon(
    onClick: () -> Unit,
    text: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Default.RemoveRedEye,
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(20.dp)
        )
        Text(text = text, modifier = Modifier.padding(4.dp))
    }
}

@Composable
fun GeneralButtonImage(
    onClick: () -> Unit,
    text: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    icon: Int,
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(20.dp)
        )
        Text(text = text, modifier = Modifier.padding(4.dp))
    }
}