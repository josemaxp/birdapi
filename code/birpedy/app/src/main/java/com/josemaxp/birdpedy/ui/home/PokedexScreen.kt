package com.josemaxp.birdpedy.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.josemaxp.birdpedy.R
import com.josemaxp.birdpedy.dao.birdWatching.BirdWatch
import com.josemaxp.birdpedy.dao.birdWatching.BirdWatchViewModel
import com.josemaxp.birdpedy.dao.birds.Bird
import com.josemaxp.birdpedy.dao.birds.BirdViewModel
import com.josemaxp.birdpedy.functions.generalScientificNameFontFamily
import com.josemaxp.birdpedy.functions.generalScientificNameFontStyle
import com.josemaxp.birdpedy.functions.generalTitleFontSize
import com.josemaxp.birdpedy.ui.components.CustomizeBottomMenu
import com.josemaxp.birdpedy.ui.components.CustomizeTopMenu
import com.josemaxp.birdpedy.ui.components.GeneralText
import com.josemaxp.birdpedy.ui.components.RectangleFirstItem
import com.josemaxp.birdpedy.ui.components.URLImage
import com.josemaxp.birdpedy.ui.navigation.AppScreens
import kotlinx.coroutines.launch


@Composable
fun PokedexScreen(
    navController: NavHostController,
    birdViewModel: BirdViewModel,
    birdWatchViewModel: BirdWatchViewModel,
    allBirdsWatched: List<BirdWatch>,
    allBirds: State<List<Bird>>,
    selected: MutableIntState,
){
    val baseUrl = "https://josemaxp.github.io/birdapi/images/"
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val orderByScientific = remember { mutableStateOf( false) }
    val searchText = remember { mutableStateOf("") }
    val firstVisibleIndex = remember { derivedStateOf { listState.firstVisibleItemIndex } }.value
    val selectedBird = remember { mutableStateOf(birdViewModel.basicData()) }
    val birdWatch = remember { mutableStateOf(listOf<BirdWatch>()) }
    val onlyWatched = remember { mutableStateOf(false) }
    val onlyNotWatched = remember { mutableStateOf(false) }
    val birdsWatchedId = allBirdsWatched.map { it.birdId }
    val allBirdsFiltered = allBirds.value.sortedBy { if(orderByScientific.value) it.nombre_cientifico else it.nombre_comun }
        .filter { if(onlyWatched.value) it.id in birdsWatchedId else true }
        .filter { if(onlyNotWatched.value) it.id !in birdsWatchedId else true  }
        .filter {
            if(searchText.value.isNotEmpty() && orderByScientific.value) {
                 it.nombre_cientifico!!.lowercase()
                    .contains(searchText.value.lowercase())
            }
            else if(searchText.value.isNotEmpty() && !orderByScientific.value){
                it.nombre_comun!!.lowercase()
                    .contains(searchText.value.lowercase())
            }
            else {
                true
            }
        }

    if(allBirdsFiltered.isNotEmpty()) {
        selectedBird.value = allBirdsFiltered[firstVisibleIndex]
        birdWatch.value = allBirdsWatched.filter { it.birdId == selectedBird.value.id }
    }else{
        selectedBird.value = birdViewModel.basicData()
    }

    Column (
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ){
        CustomizeTopMenu(
            modifier = Modifier
                .weight(0.2f),
            orderByScientific = orderByScientific,
            text = if(onlyWatched.value ) "Aves avistadas" else if(onlyNotWatched.value) "Aves no avistadas" else "Aves de España",
            onClick0 = {
                coroutineScope.launch {
                    listState.scrollToItem(0)
                    orderByScientific.value = false
                }
            },
            onClick1 = {
                coroutineScope.launch {
                    listState.scrollToItem(0)
                    orderByScientific.value = true
                }
            },
            searchText = searchText,
            navController = navController,
            onlyWatched = onlyWatched,
            onlyNotWatched = onlyNotWatched,
            listState = listState
        )
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .weight(0.7f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.2f)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    GeneralText(
                        text = selectedBird.value.nombre_comun.toString(),
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = generalTitleFontSize(),
                        textAlign = TextAlign.Center,
                        maxLines = 2
                    )
                    GeneralText(
                        text = selectedBird.value.nombre_cientifico.toString(),
                        modifier = Modifier.padding(top = 2.dp),
                        style = generalScientificNameFontStyle(),
                        fontFamily = generalScientificNameFontFamily(),
                        textAlign = TextAlign.Center
                    )
                }

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = if (birdWatch.value.isEmpty()) 10.dp else 0.dp,
                            spotColor = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(40.dp)
                        )
                        .weight(0.55f)
                        .clip(shape = RoundedCornerShape(40.dp))
                        .background(if (birdWatch.value.isEmpty()) MaterialTheme.colorScheme.primary else Color.Transparent),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    if(allBirdsFiltered.isNotEmpty()) {
                        URLImage(
                            url = if (birdWatch.value.isEmpty()) baseUrl + selectedBird.value.png else baseUrl + selectedBird.value.jpg,
                            colorFilter = if (birdWatch.value.isEmpty()) ColorFilter.tint(MaterialTheme.colorScheme.background) else null,
                            birdWatch = birdWatch
                        )
                    }else{
                        Image(
                            painter = painterResource(id = R.drawable.bird),
                            contentDescription = "No bird image",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.background),
                            contentScale = ContentScale.Fit,
                        )
                    }
                }
                Row (
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .weight(0.25f)
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    FilledIconButton(
                        onClick = {
                            if(birdWatch.value.isEmpty()){
                                birdWatchViewModel.insert(BirdWatch(0, selectedBird.value.id))
                            }else{
                                birdWatchViewModel.delete(birdWatch.value.first())
                                coroutineScope.launch {
                                    if(onlyWatched.value) {
                                        listState.scrollToItem(0)
                                    }
                                }
                            }
                        },
                        modifier = Modifier.weight(1f),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        enabled = allBirdsFiltered.isNotEmpty()
                    ) {
                        Image(
                            painter = if(selectedBird.value.id in birdsWatchedId) painterResource(id = R.drawable.binoculars) else painterResource(id = R.drawable.binocularsempty),
                            contentDescription = "Icono avistar ${selectedBird.value.nombre_comun}",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                    FilledIconButton(
                        onClick = {
                            birdViewModel.setSelectedBird(selectedBird.value)
                            navController.navigate(route = AppScreens.BirdScreen.route)
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary
                        ),
                        enabled = allBirdsFiltered.isNotEmpty()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Icono info ${selectedBird.value.nombre_comun}",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(20.dp)
                        )
                    }
                }
            }
            if(allBirdsFiltered.isNotEmpty()) {
                CurvedScrollList(
                    modifier = Modifier
                        .weight(0.4f),
                    listState = listState,
                    list = allBirdsFiltered,
                    firstVisibleIndex = firstVisibleIndex,
                    orderByScientific = orderByScientific
                )
            }else if(searchText.value.isNotEmpty()) {
                GeneralText(
                    text = "No existen coincidencias.",
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(0.4f)
                )
            }else if(onlyWatched.value) {
                GeneralText(
                    text = "La lista de aves avistadas se encuentra vacía.",
                    modifier = Modifier
                        .padding(2.dp)
                        .weight(0.4f)
                )
            }
        }
        CustomizeBottomMenu(
            modifier = Modifier
                .height(75.dp)
                .weight(0.1f),
            onClick0 = {
                onlyWatched.value = false
                onlyNotWatched.value = false
                coroutineScope.launch {
                    listState.scrollToItem(0)
                }
            },
            onClick1 = {
                navController.navigate(route = AppScreens.MapScreen.route)
            },
            selected = selected
        )
    }
}

@Composable
fun CurvedScrollList(
    modifier: Modifier,
    listState: LazyListState,
    list: List<Bird>,
    firstVisibleIndex: Int,
    orderByScientific: MutableState<Boolean>
) {
    val heightDp = LocalConfiguration.current.screenHeightDp.dp / 1.6f
    val height = remember { mutableStateOf(65.dp) }
    val width = remember { mutableStateOf(0.dp) }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = listState
        ) {
            itemsIndexed(list.sortedBy { if(orderByScientific.value) it.nombre_cientifico else it.nombre_comun }) { index, bird ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height.value)
                        .padding(end = 8.dp)
                        .onGloballyPositioned {
                            width.value = it.size.width.dp
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    ) {
                    if (index == firstVisibleIndex) {
                        RectangleFirstItem(
                            text = if(orderByScientific.value) bird.nombre_cientifico.toString() else bird.nombre_comun.toString(),
                            size = Size
                                (width = width.value.value,
                                height = height.value.value * 2)
                        )
                    }else {
                        Row (
                            modifier = Modifier
                                .fillMaxSize()
                                .height(200.dp)
                                .padding(top = 24.dp)
                                .border(
                                    border = ButtonDefaults.outlinedButtonBorder,
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .clip(shape = RoundedCornerShape(4.dp))
                                .background(MaterialTheme.colorScheme.secondary)
                                .clickable {
                                    coroutineScope.launch {
                                        listState.animateScrollToItem(index)
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ){
                            GeneralText(
                                text = if(orderByScientific.value) bird.nombre_cientifico.toString() else bird.nombre_comun.toString(),
                                modifier = Modifier.padding(2.dp)
                            )
                        }
                    }
                }

                if (index == list.size - 1) {
                    Spacer(modifier = Modifier.height(heightDp))
                }
            }
        }
    }
}