package com.josemaxp.birdpedy.ui.components

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContactSupport
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.josemaxp.birdpedy.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizeTopMenu(
    modifier: Modifier,
    orderByScientific: MutableState<Boolean>,
    text: String,
    onClick0: () -> Unit = {},
    onClick1: () -> Unit = {},
    searchText: MutableState<String>,
    navController: NavHostController,
    onlyWatched: MutableState<Boolean>,
    onlyNotWatched: MutableState<Boolean>,
    listState: LazyListState
){
    val selected = remember { mutableIntStateOf(0) }
    val animate0 = remember { mutableStateOf(false) }
    val animate1 = remember { mutableStateOf(false) }
    val searchState = remember { mutableStateOf(false) }
    val showMenu = remember { mutableStateOf(false) }
    val showFilter = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val searchAnimatedState = remember { MutableTransitionState(false) }
    val offsetXSearchBar = animateDpAsState(
        targetValue = if (searchState.value) 0.dp else LocalConfiguration.current.screenWidthDp.dp,
        label = ""
    )
    val offsetXTopBar = animateDpAsState(
        targetValue = if (!searchState.value) 0.dp else LocalConfiguration.current.screenWidthDp.dp,
        label = ""
    )

    val shape0 = customMenuRoundedCorner(
        selectedItem = selected.intValue,
        shapeNumber = 0,
        totalShapes = 3,
        position = "top"
    )
    val shape1 = customMenuRoundedCorner(
        selectedItem = selected.intValue,
        shapeNumber = 1,
        totalShapes = 3,
        position = "top"
    )

    Column (
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
    ) {
        when (searchState.value) {
            false -> {
                TopAppBar(
                    title = { Text(text = text) },
                    modifier = Modifier
                        .offset(x = offsetXTopBar.value),
                    actions = {
                        IconButton(onClick = {
                            searchState.value = true
                            searchAnimatedState.targetState = true
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search icon"
                            )
                        }
                        IconButton(onClick = {
                            showFilter.value = !showFilter.value
                        }) {
                            Icon(
                                imageVector = Icons.Filled.FilterAlt,
                                contentDescription = "Search icon"
                            )
                        }
                        DropdownMenu(
                            expanded = showFilter.value,
                            onDismissRequest = { showFilter.value = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(text = "Mostrar todos") },
                                onClick = {
                                    showFilter.value = false
                                    onlyWatched.value = false
                                    onlyNotWatched.value = false
                                    coroutineScope.launch {
                                        listState.scrollToItem(0)
                                    }
                                },
                                trailingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.bird),
                                        contentDescription = "Mostrar todos",
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                                        modifier = Modifier
                                            .size(25.dp)
                                    )
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Avistados") },
                                onClick = {
                                    showFilter.value = false
                                    onlyWatched.value = true
                                    onlyNotWatched.value = false
                                    coroutineScope.launch {
                                        listState.scrollToItem(0)
                                    }
                                },
                                trailingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.binoculars),
                                        contentDescription = "Avistados",
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                                        modifier = Modifier
                                            .size(25.dp)
                                    )
                                }
                            )
                            DropdownMenuItem(
                                text = { Text(text = "No avistados") },
                                onClick = {
                                    showFilter.value = false
                                    onlyWatched.value = false
                                    onlyNotWatched.value = true
                                    coroutineScope.launch {
                                        listState.scrollToItem(0)
                                    }
                                },
                                trailingIcon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.binocularsempty),
                                        contentDescription = "No avistados",
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                                        modifier = Modifier
                                            .size(25.dp)
                                    )
                                }
                            )
                        }

                        IconButton(onClick = { showMenu.value = !showMenu.value }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "More options"
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu.value,
                            onDismissRequest = { showMenu.value = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text(text = "Contacta con nosotros") },
                                onClick = { navController.navigate("settingScreen") },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.ContactSupport,
                                        contentDescription = "More options"
                                    )}
                            )
                            DropdownMenuItem(
                                text = { Text(text = "Reportar error") },
                                onClick = { /*TODO*/ },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.BugReport,
                                        contentDescription = "More options"
                                    )}
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.background,
                        actionIconContentColor = MaterialTheme.colorScheme.background,
                    )
                )
            }

            true -> {
                SearchAppBar(
                    text = searchText,
                    modifier = Modifier.offset(x = offsetXSearchBar.value),
                    onCloseClicked = {
                        searchState.value = false
                    },
                    onSearchClicked = {}
                )
            }
        }

        Row(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.Top
        ) {
            CustomizeTextIconButton(
                onClick = {
                    onClick0()
                    orderByScientific.value = false
                    selected.intValue = 0
                    animate0.value = true
                    animate1.value = false
                },
                modifier = Modifier.weight(1f),
                selectedItem = selected,
                text = "N. común",
                number = 0,
                shape = shape0
            )
            CustomizeTextIconButton(
                onClick = {
                    onClick1()
                    orderByScientific.value = true
                    selected.intValue = 1
                    animate0.value = false
                    animate1.value = true
                },
                modifier = Modifier.weight(1f),
                selectedItem = selected,
                text = "N. científico",
                number = 1,
                shape = shape1
            )
        }
    }
}

@Composable
fun SearchAppBar(
    text: MutableState<String>,
    modifier: Modifier = Modifier,
    onCloseClicked: () -> Unit,
    onSearchClicked: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp)
            .height(60.dp),
        color = MaterialTheme.colorScheme.primary,

        ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text.value,
            onValueChange = { text.value = it },
            placeholder = {
                Text(
                    modifier =  Modifier
                        .alpha(0.5f),
                    text = "Buscar...",
                    color = MaterialTheme.colorScheme.background
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(0.5f),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = "Search icon")
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if(text.value.isNotEmpty()){
                            text.value = ""
                        }else{
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close, contentDescription = "Close icon")
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked()
                }
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedContainerColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.background,
                focusedTrailingIconColor = MaterialTheme.colorScheme.background,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.background,
                focusedLeadingIconColor = MaterialTheme.colorScheme.background,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.background
            )
        )
    }
}