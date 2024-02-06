package com.example.myapplication

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.components.TextInputV2
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.DarkOrange
import com.example.myapplication.ui.theme.DividerGrey
import com.example.myapplication.ui.theme.MainPurple
import com.example.myapplication.ui.theme.TextGrey

@Composable
fun SearchScreen(navController: NavHostController) {
    var searchValue by remember {
        mutableStateOf("")
    }

    var animate by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        animate = true
    }

    var action by remember { mutableIntStateOf(-1) }

    var searchItems by remember {
        mutableStateOf(Mock.searchItems)
    }

    Search(
        animate = animate,
        action = action,
        searchItems = searchItems,
        searchValue = searchValue,
        onSearchValueChange = {
            if (it.isBlank()) {
                searchValue = it
                action = 0
                searchItems = Mock.searchItems
            } else {
                searchValue = it
            }
        },
        onBackPressed = {
            navController.navigateUp()
        },
        onSearchClicked = {
            action = 1
            searchItems = Mock.searchedItems
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Search(
    animate: Boolean,
    action: Int,
    searchItems: List<SearchItemModel>,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onBackPressed: () -> Unit,
    onSearchClicked: () -> Unit
) {

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primaryContainer = Color.White,
            surfaceVariant = Color.White
        )
    ) {
        AnimatedVisibility(visible = animate,
            enter = fadeIn(initialAlpha = 0.5f)
        ) {
            Scaffold(
                topBar = {
                    Row(
                        modifier = Modifier
                            .background(color = MainPurple)
                            .padding(bottom = 16.dp, top = 16.dp, start = 6.dp, end = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            tint = Color.White,
                            modifier = Modifier
                                .size(width = 40.dp, height = 40.dp)
                                .animateEnterExit(
                                    enter = slideInHorizontally(
                                        animationSpec = tween(200, easing = EaseIn)
                                    )
                                )
                                .clickable {
                                    onBackPressed()
                                },
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        TextInputV2(
                            Modifier
                                .fillMaxWidth()
                                .animateEnterExit(
                                    enter = slideInVertically(
                                        initialOffsetY = {
                                            it / 2
                                        }
                                    )
                                ),
                            value = searchValue,
                            onValueChange = onSearchValueChange,
                            label = "Enter the recipient number...",
                            trailingIcon = {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .background(
                                            color = DarkOrange,
                                            shape = CircleShape
                                        )
                                        .size(40.dp)
                                        .padding(8.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.scanner),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Search
                            ),
                            onSearchClicked = {
                                onSearchClicked()
                            }
                            )
                    }
                },
                modifier = Modifier.padding(
                    top = 0.dp, bottom = 16.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .background(Background)
                ) {

                    Column(
                        modifier = Modifier
                            .animateEnterExit(
                                enter = slideInVertically(
                                    initialOffsetY = {
                                        it / 3
                                    },
                                    animationSpec = tween(500, easing = EaseIn)
                                )
                            )
                            .padding(vertical = 12.dp)
                            .fillMaxSize()
                    ) {

                        AnimatedContent(targetState = action,
                            transitionSpec = {
                                fadeIn() + slideInVertically(animationSpec = tween(600),
                                    initialOffsetY = { fullHeight -> fullHeight }) with
                                        fadeOut(animationSpec = tween(200))
                            },
                            label = "SearchAnimation") { state ->
                            when(state) {
                                1 -> SearchList(searchItems = searchItems)
                                else -> SearchList(searchItems = searchItems)
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun SearchList(searchItems: List<SearchItemModel>) {

    var animate by remember {
        mutableStateOf(false)
    }

    LaunchedEffect( key1 = Unit) {
        animate = true
    }

    AnimatedVisibility(visible = animate,
        enter = fadeIn(initialAlpha = 0.5f)
                + slideInVertically(
            initialOffsetY = {
                it / 2
            }
        )
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = .5.dp)
        ) {
            LazyColumn {
                items(searchItems.size) {
                    SearchItem(
                        title = "Pixel 7a",
                        idNumber = "#NE43857340854444",
                        from = "Abuja",
                        to = "Lagos",
                        showDivider = (searchItems.size - 1) != it
                    )
                }
            }
        }
    }
}

@Composable
fun SearchItem(
    title: String,
    idNumber: String,
    from: String,
    to: String,
    showDivider: Boolean
) {
    Column(Modifier.padding(top = 16.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(30.dp)
                    .background(color = MainPurple, shape = CircleShape)
            ) {
                Image(
                    painterResource(id = R.drawable.ic_package),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(20.dp)
                        .background(
                            color = Color.Transparent
                        )
                )
            }

            Column(modifier = Modifier.padding(start = 8.dp, end = 16.dp)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = idNumber,
                        fontSize = 16.sp,
                        color = TextGrey
                    )

                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(
                                color = TextGrey,
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = from,
                        fontSize = 14.sp,
                        color = TextGrey
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = TextGrey
                    )
                    Text(
                        text = to,
                        fontSize = 14.sp,
                        color = TextGrey
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        if (showDivider) {
            Divider(
                modifier = Modifier.padding(end = 16.dp, start = 16.dp),
                color = DividerGrey
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun SearchPreview() {
    Search(
        animate = false,
        action = 0,
        searchItems = Mock.searchItems,
        searchValue = "",
        onSearchValueChange = {},
        onBackPressed = {},
        onSearchClicked = {}
    )
}

@Preview
@Composable
fun SearchItemPreview() {
    SearchItem(
        title = "Pixel 7a",
        idNumber = "#NE43857340854444",
        from = "Abuja",
        to = "Lagos",
        showDivider = false
    )
}