package com.example.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.components.TextInputV2
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.BackgroundLightGreen
import com.example.myapplication.ui.theme.BackgroundOrange
import com.example.myapplication.ui.theme.BottomNavDividerDarkPurple
import com.example.myapplication.ui.theme.BottomNavUnselectedGrey
import com.example.myapplication.ui.theme.DarkBrown
import com.example.myapplication.ui.theme.DarkOrange
import com.example.myapplication.ui.theme.DividerGrey
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.theme.LightPurple
import com.example.myapplication.ui.theme.MainPurple
import com.example.myapplication.ui.theme.TextGrey
import com.skydoves.orbital.rememberContentWithOrbitalScope

@Composable
fun HomeScreen(navController: NavHostController) {
    var searchValue by remember {
        mutableStateOf("")
    }

//    var sharedElement by rememberContentWithOrbitalScope {
//
//    }

    Home(
        bottomNavItems = Mock.items,
        searchValue,
        onSearchValueChange = {
            searchValue = it
        },
        onSearchClick = {
            navController.navigate("search")
        },
        goToShipment = {
            navController.navigate("shipment")
        },
        gotoCalculator = {
            navController.navigate("calculator")
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Home(
    bottomNavItems: List<BottomNavigationItemModel>,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    goToShipment: () -> Unit = {},
    gotoCalculator: () -> Unit = {}
) {
    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }

    var itemWidth by remember { mutableIntStateOf(0) }

    var animate by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        animate = true
    }

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primaryContainer = Color.White,
            surfaceVariant = Color.White
        )
    ) {
        Scaffold(modifier = Modifier.padding(0.dp),
            containerColor = Color.White,
            bottomBar = {
                val indicatorWidth = 64
                AnimatedVisibility(visible = animate,
                    enter = fadeIn(initialAlpha = 0.5f)
                ) {
                    Column (modifier = Modifier
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = {
                                    it / 2
                                }
                            ))
                    ){
                        Divider(
                            modifier = Modifier.width(itemWidth.dp),
                            color = BottomNavDividerDarkPurple,
                            thickness = 4.dp
                        )
                        NavigationBar(
                            containerColor = Color.White,
                            tonalElevation = 2.dp
                        ) {
                            bottomNavItems.forEachIndexed { index, bottomNavigationItem ->

                                NavigationBarItem(
                                    modifier = Modifier.onSizeChanged {
                                        itemWidth = (it.width - indicatorWidth) / 2
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        indicatorColor = Color.White,
                                    ),
                                    selected = index == navigationSelectedItem,
                                    onClick = {
                                        navigationSelectedItem = index
                                        if (index == 1) {
                                            gotoCalculator()
                                        } else if (index == 2) {
                                            goToShipment()
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = bottomNavigationItem.selectedIcon,
                                            tint = if (index == navigationSelectedItem) BottomNavDividerDarkPurple
                                            else BottomNavUnselectedGrey,
                                            contentDescription = null
                                        )
                                    },
                                    label = {
                                        Text(text = bottomNavigationItem.title,
                                            color = if (index == navigationSelectedItem) BottomNavDividerDarkPurple
                                            else BottomNavUnselectedGrey)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = rememberScrollState()
                )
                .padding(it)
                .background(Background)) {
                Column {
                    HeaderSection(
                        onSearchClick = onSearchClick,
                        searchValue,
                        onSearchValueChange,
                        animate
                    )

                    BodySection(
                        animate
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    AvailableVehicleSection(
                        animate
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BodySection(animate: Boolean) {

    AnimatedVisibility(visible = animate,
        enter = fadeIn(
            initialAlpha = 0.5f
        ) + slideInVertically(
            initialOffsetY = {
                it / 4
            }
        )
    ) {
        Column (modifier = Modifier
            .padding(16.dp)
        ) {

            Text(text = "Tracking", fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))

            Card (
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
            ) {

                Column (modifier =
                Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column (Modifier.weight(1f)) {
                            Text(text = "Shipment Number", color = TextGrey)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "NEJ20089934122231", fontWeight = FontWeight.Medium,
                                fontSize = 20.sp, color = Color.Black)
                        }

                        Image(painter = painterResource(id = R.drawable.forklift_icon),
                            modifier = Modifier.size(50.dp),
                            contentDescription = null)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Divider(
                        color = DividerGrey
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Column {

                        Row {
                            Box (contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(30.dp)
                                    .background(color = BackgroundOrange, shape = CircleShape)

                            ) {
                                Image(
                                    painterResource(id = R.drawable.open_carton),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(modifier = Modifier.weight(0.5f)) {
                                Text(text = "Sender", color = TextGrey)
                                Text(text = "Atlanta, 5243",
                                    fontWeight = FontWeight(500),
                                    color = Color.Black, fontSize = 16.sp)
                            }

                            Column (modifier = Modifier.weight(0.5f)){
                                Text(text = "Time", color = TextGrey)
                                Row (verticalAlignment = Alignment.CenterVertically) {
                                    Box(modifier = Modifier
                                        .size(6.dp)
                                        .background(Green, shape = CircleShape)) {

                                    }
                                    
                                    Spacer(modifier = Modifier.width(4.dp))

                                    Text(text = "2 day - 3 days",
                                        fontWeight = FontWeight(500),
                                        color = Color.Black, fontSize = 16.sp)
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Row {

                            Box (contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(30.dp)
                                    .background(color = BackgroundLightGreen, shape = CircleShape)

                            ) {
                                Image(
                                    painterResource(id = R.drawable.closed_carton),
                                    contentDescription = null,
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .background(
                                            color = BackgroundLightGreen
                                        )
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Column(modifier = Modifier.weight(0.5f)) {
                                Text(text = "Receiver", color = TextGrey)
                                Text(text = "Chicago, 6342",
                                    fontWeight = FontWeight(500),
                                    color = Color.Black, fontSize = 16.sp)
                            }

                            Column (modifier = Modifier.weight(0.5f)) {
                                Text(text = "Status", color = TextGrey)
                                Text(text = "Waiting to collect",
                                    fontWeight = FontWeight(500),
                                    color = Color.Black, fontSize = 16.sp)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Divider(
                        color = DividerGrey
                    )

                    TextButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = { /*TODO*/ },) {
                        Row (verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null,
                                tint = DarkBrown
                            )
                            Text(text = "Add Stop", fontSize = 18.sp,
                                color = DarkBrown,
                                fontWeight = FontWeight(500)
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AvailableVehicleSection(animate: Boolean) {

    AnimatedVisibility(visible = animate,
        enter = fadeIn(initialAlpha = 0.5f)) {
        Column (modifier = Modifier
            .animateEnterExit(
                enter = slideInHorizontally(
                    initialOffsetX = {
                        it / 4
                    },
                    animationSpec = tween(500, easing = EaseIn)
                )
            )
            .padding(16.dp)
        ){
            Text(text = "Available vehicles", fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = Color.Black)

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(Mock.mockAvailableVehicles) {
                    AvailableVehicleItem(title = it.title,
                        subTitle = it.subTitle, image = it.drawable, animate)
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AvailableVehicleItem(
    title: String,
    subTitle: String,
    image: Int,
    animate: Boolean
) {

    AnimatedVisibility(
        visible = animate,
        enter = fadeIn(
            initialAlpha = 0.5f
        )
    ) {
        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.5.dp
            )
        ) {
            Box (modifier = Modifier.size(height = 230.dp, width = 200.dp))
            {

                AnimatedVehicleImage {
                    Image(
                        modifier = it
                            .size(130.dp)
                            .align(Alignment.CenterEnd),
                        painter = painterResource(id = image),
                        contentDescription = null)
                }
                Column {
                    Column(modifier = Modifier.padding(horizontal = 16.dp,
                        vertical = 8.dp)) {
                        Text(text = title, fontWeight = FontWeight.Medium,
                            fontSize = 22.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = subTitle, color = TextGrey,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun AnimatedVehicleImage(animatedImage: @Composable (modifier: Modifier) -> Unit) {

    var animate by remember {
        mutableStateOf(false)
    }

    LaunchedEffect( key1 = Unit) {
        animate = true
    }

    AnimatedVisibility(visible = animate,
        enter = fadeIn(
            initialAlpha = 0.5f
        ) + slideInHorizontally (
            initialOffsetX = {
                it / 2
            },
            animationSpec = tween(1500)
        )
        ) {
        Box (modifier = Modifier.fillMaxSize()){
            animatedImage(Modifier)
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeaderSection(
    onSearchClick: () -> Unit,
    searchValue: String,
    onSearchValueChange: (String) -> Unit,
    animate: Boolean
) {

    AnimatedVisibility(visible = animate,
        enter = fadeIn(initialAlpha = 0.5f)
    ) {
        Column (modifier = Modifier
            .background(color = MainPurple)
            .padding(16.dp)
            .animateEnterExit(
                enter = slideInVertically(
                    animationSpec = tween(200, easing = EaseIn)
                )
            )
        )
        {
            Row (verticalAlignment = Alignment.CenterVertically){
                Image(
                    painter = painterResource(R.drawable.spidy),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)                       // clip to the circle shap
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row (verticalAlignment = Alignment.CenterVertically){
                        Icon(painter = painterResource(id = R.drawable.baseline_near_me_24),
                            contentDescription = null,
                            tint = LightPurple,
                            modifier = Modifier.size(14.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(text = "Your location",
                            color = LightPurple,
                            fontSize = 14.sp
                        )
                    }

                    Row (verticalAlignment = Alignment.CenterVertically){
                        Text(text = "Abuja, Nigeria",
                            color = Color.White,
                            fontSize = 20.sp)

                        Icon(imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null, tint = Color.White)
                    }
                }

                Box(modifier = Modifier
                    .padding(8.dp)
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(color = Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(imageVector = Icons.Outlined.Notifications,
                        modifier = Modifier.size(30.dp),
                        contentDescription = null)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextInputV2(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSearchClick()
                    },
                value = searchValue,
                onValueChange = onSearchValueChange,
                label = "Enter the recipient number ...",
                enabled = false,
                trailingIcon = {
                    Box (contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = DarkOrange,
                                shape = CircleShape
                            )
                            .size(40.dp)
                            .padding(8.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.scanner),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                })

        }
    }
}

@Preview
@Composable
fun HomePreview() {
    Home(
        bottomNavItems = Mock.items,
        "Goods",
        onSearchValueChange = {},
        onSearchClick = {}
    )
}

@Preview
@Composable
fun HeaderSectionPreview() {
    HeaderSection(
        onSearchClick = {},
        searchValue = "",
        onSearchValueChange = {},
        animate = false
    )
}

@Preview
@Composable
fun BodySectionPreview() {
    BodySection(
        animate = false
    )
}

@Preview
@Composable
fun AvailableVehiclePreview() {
    AvailableVehicleSection(
        animate = false
    )
}