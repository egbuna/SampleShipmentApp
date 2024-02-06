package com.example.myapplication

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.CompletedGreen
import com.example.myapplication.ui.theme.DarkOrange
import com.example.myapplication.ui.theme.IconGrey
import com.example.myapplication.ui.theme.InProgressGreen
import com.example.myapplication.ui.theme.LightPurple
import com.example.myapplication.ui.theme.LoadingBlue
import com.example.myapplication.ui.theme.MainPurple
import com.example.myapplication.ui.theme.PendingOrange
import com.example.myapplication.ui.theme.StatusBackground
import com.example.myapplication.ui.theme.TextGrey

@Composable
fun ShipmentScreen(navController: NavController) {
    var shipments by remember {
        mutableStateOf(Mock.shipments)
    }

    ShipmentComposable(
        Mock.tabItems,
        shipments,
        onBackPressed = {
            navController.navigateUp()
        },
        onTabClicked = {
            when(it) {
                0 -> {
                    shipments = Mock.shipments
                }
                1 -> shipments = Mock.completed
                2 -> shipments = Mock.inProgress
                3 -> shipments = Mock.pending
                4 -> shipments = Mock.loading
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ShipmentComposable(
    tabs: List<TabItem>,
    shipments: List<ShipmentModel>,
    onBackPressed: () -> Unit,
    onTabClicked: (Int) -> Unit
) {

    var selectedTabIndex by remember { mutableIntStateOf(-1) }

    var animate by remember {
        mutableStateOf(false)
    }

    LaunchedEffect( key1 = Unit) {
        animate = true
        selectedTabIndex = 0
    }


    AnimatedVisibility(visible = animate,
        enter = fadeIn(initialAlpha = 0.5f)
    ) {

        Scaffold(modifier = Modifier.padding(0.dp), topBar = {
            Column (modifier = Modifier
                .background(
                    color = MainPurple
                )) {
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
                            .animateEnterExit(
                                enter = slideInHorizontally()
                            )
                            .size(width = 40.dp, height = 40.dp)
                            .clickable {
                                onBackPressed()
                            },
                        contentDescription = null
                    )

                    Text(
                        text = "Shipment history", modifier = Modifier
                            .weight(1f)
                            .padding(end = 24.dp)
                            .animateEnterExit(
                                enter = fadeIn()
                            ),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center, color = Color.White
                    )

                }

                ScrollableTabRow(
                    modifier = Modifier
                        .animateEnterExit(
                            enter = slideInHorizontally(
                                initialOffsetX = {
                                    it / 2
                                }
                            )),
                    edgePadding = 16.dp,
                    containerColor = MainPurple,
                    selectedTabIndex = selectedTabIndex,
                    indicator = {
                        TabRowDefaults.Indicator(
                            color = DarkOrange,
                            modifier = if (selectedTabIndex < 0) {
                                Modifier
                                    .tabIndicatorOffset(it[0])
                                    .fillMaxWidth()
                            } else {
                                Modifier
                                    .tabIndicatorOffset(it[selectedTabIndex])
                                    .fillMaxWidth()
                            }
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, tabItem ->
                        Tab(selected = selectedTabIndex == index,
                            onClick = {
                                selectedTabIndex = index
                                onTabClicked.invoke(selectedTabIndex)
                            },
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = tabItem.title, color = if (selectedTabIndex == index)
                                            Color.White else LightPurple
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(height = 20.dp, width = 30.dp)
                                            .background(
                                                color = if (selectedTabIndex == index)
                                                    Color(0xFFf07a24) else Color(0xFF694dbc),
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                    ) {
                                        Text(
                                            text = tabItem.count.toString(),
                                            fontSize = 12.sp,
                                            color = if (selectedTabIndex == index)
                                                Color.White else Color(0xFFa48ee9)
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }

        }) {
            Column(modifier = Modifier.padding(it)
                .background(color = Background)) {

                Column(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                ) {

                    Text(
                        text = "Shipments", fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    AnimatedContent(targetState = selectedTabIndex,
                        transitionSpec = {
                            fadeIn() + slideInVertically(animationSpec = tween(600),
                                initialOffsetY = { fullHeight -> fullHeight }) with
                                    fadeOut(animationSpec = tween(200))
                        },
                        label = "ShipmentAnimation") { state ->
                        when(state) {
                            -1 -> {
                                ShipmentList(shipments = shipments)
                            }
                            0 -> {
                                ShipmentList(shipments = shipments)
                            }
                            1 -> {
                                ShipmentList(shipments = shipments)
                            }
                            2 -> {
                                ShipmentList(shipments = shipments)
                            }
                            3 -> {
                                ShipmentList(shipments = shipments)
                            }
                            4 -> {
                                ShipmentList(shipments = shipments)
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ShipmentList(
    shipments: List<ShipmentModel>
) {
    var animate by remember {
        mutableStateOf(false)
    }

    LaunchedEffect( key1 = Unit) {
        animate = true
    }

    AnimatedVisibility(visible = animate,
        enter = fadeIn(
            initialAlpha = 0.5f
        ) + slideInVertically(
            initialOffsetY = {
                it / 2
            }
        )
        ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                shipments
            ) {
                ShipmentItem(shipment = it)
            }
        }
    }

}

@Composable
fun ShipmentItem(shipment: ShipmentModel) {
    Card (modifier = Modifier
        .fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.5.dp
        )
        ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)) {
            Column (modifier = Modifier.weight(1f)){
                ShipmentStatusView(shipmentStatus = shipment.status)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = shipment.title,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(600)
                    )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = shipment.details, fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextGrey)
                Text(text = shipment.subDetails,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextGrey)
                Spacer(modifier = Modifier.height(16.dp))
                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(text = shipment.amount,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MainPurple)
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(
                                color = IconGrey,
                                shape = CircleShape
                            )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = shipment.date, fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Icon(painter = painterResource(id = R.drawable.ic_package),
                tint = IconGrey,
                contentDescription = null, modifier = Modifier.size(100.dp))
        }
    }
}

@Composable
fun ShipmentStatusView(shipmentStatus: ShipmentStatus) {

    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = StatusBackground,
                shape = CircleShape
            )
            .padding(vertical = 6.dp, horizontal = 8.dp)
    ) {
        Icon(
            imageVector = when (shipmentStatus) {
                ShipmentStatus.IN_PROGRESS -> Icons.Default.Refresh
                ShipmentStatus.LOADING -> Icons.Default.Build
                ShipmentStatus.COMPLETED -> Icons.Default.Check
                ShipmentStatus.PENDING -> Icons.Default.Info
            },
            tint = when (shipmentStatus) {
                ShipmentStatus.IN_PROGRESS -> InProgressGreen
                ShipmentStatus.LOADING -> LoadingBlue
                ShipmentStatus.COMPLETED -> CompletedGreen
                ShipmentStatus.PENDING -> PendingOrange
            },
            modifier = Modifier.size(15.dp),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = when (shipmentStatus) {
                ShipmentStatus.IN_PROGRESS -> "in-progress"
                ShipmentStatus.LOADING -> "loading"
                ShipmentStatus.COMPLETED -> "completed"
                ShipmentStatus.PENDING -> "pending"
            },
            fontSize = 12.sp,
            fontWeight = FontWeight(600),
            color = when (shipmentStatus) {
                ShipmentStatus.IN_PROGRESS -> InProgressGreen
                ShipmentStatus.LOADING -> LoadingBlue
                ShipmentStatus.COMPLETED -> CompletedGreen
                ShipmentStatus.PENDING -> PendingOrange
            }
        )
    }
}

@Preview
@Composable
fun ShipmentPreview() {
    ShipmentComposable(
        tabs = Mock.tabItems,
        shipments = Mock.shipments,
        onBackPressed = {},
        onTabClicked = {}
    )
}

@Preview
@Composable
fun ShipmentStatusViewPreview() {
    ShipmentStatusView(shipmentStatus = ShipmentStatus.IN_PROGRESS)
}