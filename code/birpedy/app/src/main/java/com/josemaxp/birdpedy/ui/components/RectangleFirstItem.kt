package com.josemaxp.birdpedy.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.josemaxp.birdpedy.functions.generalBoldFontFamily


@Composable
fun RectangleFirstItem(text: String, size: Size) {
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult: TextLayoutResult =
        textMeasurer.measure(text = AnnotatedString(text))
    val textStyle = TextStyle(
        color = MaterialTheme.colorScheme.primary,
        fontFamily = generalBoldFontFamily(),
        fontSize = MaterialTheme.typography.titleSmall.fontSize,
        fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
        fontFeatureSettings = MaterialTheme.typography.titleLarge.fontFeatureSettings,
        fontSynthesis = MaterialTheme.typography.titleLarge.fontSynthesis
    )
    val textSize = textLayoutResult.size
    val width = size.width
    val height = size.height
    val rectangleColor = MaterialTheme.colorScheme.tertiary
    val lineColor = MaterialTheme.colorScheme.primary

    val pointA = Offset(0f, 0f)
    val pointB = Offset(0f,  height)
    val pointC = Offset(width, height)
    val pointD = Offset(width, 0f)
    val pointE = Offset(- width / 10f, height / 2)

    Box(
        modifier = Modifier
            .size(width.dp, height.dp)
            .padding(top = 16.dp),
        //elevation = 4.dp,
        //shadowColor = Color.Gray
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val path = Path().apply {
                moveTo(pointD.x, pointD.y)
                lineTo(pointC.x, pointC.y)
                lineTo(pointB.x, pointB.y)
                lineTo(pointE.x, pointE.y)
                lineTo(pointA.x, pointA.y)
                close()
            }

            drawPath(path = path, color = rectangleColor)
            drawLine(
                start = pointD,
                end = pointC,
                color = lineColor,
                strokeWidth = 7f
            )
            drawLine(
                start = pointC,
                end = pointB,
                color = lineColor,
                strokeWidth = 7f
            )
            drawLine(
                start = pointB,
                end = pointE,
                color = lineColor,
                strokeWidth = 7f
            )
            drawLine(
                start = pointE,
                end = pointA,
                color = lineColor,
                strokeWidth = 7f
            )
            drawLine(
                start = pointA,
                end = pointD,
                color = lineColor,
                strokeWidth = 7f
            )
            drawText(
                textMeasurer = textMeasurer,
                text = text,
                style = textStyle,
                overflow = TextOverflow.Ellipsis,
                topLeft = Offset(
                    x = 0f,
                    y = (height - textSize.height) / 2f
                ),
            )
        }
    }
}