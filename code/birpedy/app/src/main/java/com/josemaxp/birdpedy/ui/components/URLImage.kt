package com.josemaxp.birdpedy.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.josemaxp.birdpedy.dao.birdWatching.BirdWatch

@Composable
fun URLImage(
    modifier: Modifier = Modifier,
    url: String,
    colorFilter: ColorFilter? = null,
    birdWatch: MutableState<List<BirdWatch>> = mutableStateOf(emptyList()),
) {
    val painter =
        rememberAsyncImagePainter(url)
    if (painter.state is AsyncImagePainter.State.Loading) {
        CircularProgressIndicator()
    }

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (birdWatch.value.isEmpty()) 0.dp else 10.dp,
                spotColor = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(40.dp)
            ),
        contentScale = if (birdWatch.value.isEmpty()) ContentScale.Fit else ContentScale.FillWidth,
        colorFilter = colorFilter
    )
}