package com.josemaxp.birdpedy.ui.birdscreen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavHostController
import com.josemaxp.birdpedy.dao.birds.Bird
import com.josemaxp.birdpedy.dao.families.Family
import com.josemaxp.birdpedy.dao.families.FamilyViewModel
import com.josemaxp.birdpedy.dao.order.Order
import com.josemaxp.birdpedy.dao.order.OrderViewModel
import com.josemaxp.birdpedy.ui.components.BirdpedyDivider
import com.josemaxp.birdpedy.ui.components.InfoImage
import kotlin.math.max
import kotlin.math.min

private val BottomBarHeight = 56.dp
private val TitleHeight = 100.dp
private val GradientScroll = 180.dp
private val ImageOverlap = 115.dp
private val MinTitleOffset = 65.dp
private val MinImageOffset = 12.dp
private val MaxTitleOffset = ImageOverlap + MinTitleOffset + GradientScroll
private val ExpandedImageSize = 300.dp
private val CollapsedImageSize = 150.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun BirdScreen(
    navController: NavHostController,
    selectedBird: Bird,
    familyViewModel: FamilyViewModel,
    orderViewModel: OrderViewModel,
) {
    val selectedFamily = familyViewModel.allValues.collectAsState(initial = emptyList()).value.firstOrNull { it.id == selectedBird.familia }
    val selectedOrder = orderViewModel.allValues.collectAsState(initial = emptyList()).value.firstOrNull {
        it.id == (selectedFamily?.id_orden ?: 0)
    }

    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        Body(selectedBird, scroll, selectedFamily, selectedOrder)
        Title(selectedBird) { scroll.value }
        Image(selectedBird.jpg.toString()) { scroll.value }
        Up()
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    )
}

@Composable
private fun Up() {
    IconButton(
        onClick = {/*HACER BACK*/},
        modifier = Modifier
            .statusBarsPadding()
            .padding(8.dp)
            .background(
                color = MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBackIosNew,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = "Volver"
        )
    }
}

@Composable
private fun Body(
    bird: Bird,
    scroll: ScrollState,
    selectedFamily: Family?,
    selectedOrder: Order?
) {
    Column (modifier = Modifier.padding(top = 16.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .height(MinTitleOffset)
        )
        Column(
            modifier = Modifier.verticalScroll(scroll)
        ) {
            Spacer(Modifier.height(GradientScroll))
            Column (modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                Spacer(Modifier.height(ImageOverlap))
                Spacer(Modifier.height(TitleHeight))

                Spacer(Modifier.height(16.dp))
                Row {
                    Column (modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Familia",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = HzPadding
                        )
                        Text(
                            text = selectedFamily?.nombre ?: "Información incompleta",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = HzPadding
                        )
                    }
                    Column (modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Orden",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            modifier = HzPadding
                        )
                        Text(
                            text = selectedOrder?.nombre ?: "Información incompleta",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = HzPadding
                        )
                    }
                }

                Spacer(Modifier.height(40.dp))

                Text(
                    text = "Información general",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = HzPadding
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = bird.info.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = HzPadding
                )

                Spacer(Modifier.height(40.dp))

                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Identificación",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = HzPadding
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = bird.identificacion.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    overflow = TextOverflow.Ellipsis,
                    modifier = HzPadding
                )

                Spacer(Modifier.height(40.dp))

                Text(
                    text = "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = HzPadding
                )

                Spacer(Modifier.height(16.dp))
                BirdpedyDivider()
                Spacer(
                    modifier = Modifier
                        .padding(bottom = BottomBarHeight)
                        .navigationBarsPadding()
                        .height(8.dp)
                )
            }
        }
    }
}

@Composable
private fun Title(bird: Bird, scrollProvider: () -> Int) {
    val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
    val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = TitleHeight)
            .statusBarsPadding()
            .offset {
                val scroll = scrollProvider()
                val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
                IntOffset(x = 0, y = offset.toInt())
            }
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            text = bird.nombre_comun.toString(),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = HzPadding.fillMaxWidth(0.5f)
        )
        Text(
            text = bird.nombre_cientifico.toString(),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            fontStyle = FontStyle.Italic,
            modifier = HzPadding.fillMaxWidth(0.5f)
        )
        Spacer(Modifier.height(16.dp))
        BirdpedyDivider()
    }
}

@Composable
private fun Image(
    imageUrl: String,
    scrollProvider: () -> Int
) {
    val url = "https://josemaxp.github.io/birdapi/images/$imageUrl"
    val collapseRange = with(LocalDensity.current) { (MaxTitleOffset - MinTitleOffset).toPx() }
    val collapseFractionProvider = {
        (scrollProvider() / collapseRange).coerceIn(0f, 1f)
    }

    CollapsingImageLayout(
        collapseFractionProvider = collapseFractionProvider,
        modifier = HzPadding.statusBarsPadding()
    ) {
        InfoImage(
            imageUrl = url,
            contentDescription = "",
        )
    }
}

@Composable
private fun CollapsingImageLayout(
    collapseFractionProvider: () -> Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        check(measurables.size == 1)

        val collapseFraction = collapseFractionProvider()

        val imageMaxSize = min(ExpandedImageSize.roundToPx(), constraints.maxWidth)
        val imageMinSize = max(CollapsedImageSize.roundToPx(), constraints.minWidth)
        val imageWidth = lerp(imageMaxSize, imageMinSize, collapseFraction)
        val imagePlaceable = measurables[0].measure(Constraints.fixed(imageWidth, imageWidth))

        val imageY = lerp(MinTitleOffset, MinImageOffset, collapseFraction).roundToPx()
        val imageX = lerp(
            (constraints.maxWidth - imageWidth) / 2, // centered when expanded
            constraints.maxWidth - imageWidth, // right aligned when collapsed
            collapseFraction
        )
        layout(
            width = constraints.maxWidth,
            height = imageY + imageWidth
        ) {
            imagePlaceable.placeRelative(imageX, imageY)
        }
    }
}
