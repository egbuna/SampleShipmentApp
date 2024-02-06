package com.example.myapplication

import android.view.MotionEvent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.myapplication.components.TextInputV2
import com.example.myapplication.ui.theme.Background
import com.example.myapplication.ui.theme.DarkOrange
import com.example.myapplication.ui.theme.IconGrey
import com.example.myapplication.ui.theme.MainPurple
import com.example.myapplication.ui.theme.TextGrey
import com.example.myapplication.ui.theme.TextInputGrey

@Composable
fun CalculateScreen(navController: NavController) {
    CalculateComposable(
        navigateUp = {
            navController.navigateUp()
        },
        onPrimaryButtonClicked = {
            navController.navigate("success")
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun CalculateComposable(
    navigateUp: () -> Unit,
    onPrimaryButtonClicked: () -> Unit
) {
    var selectedIndex by remember {
        mutableIntStateOf(-1)
    }

    var animate by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        animate = true
    }

    Scaffold(modifier = Modifier.padding(0.dp),
    ) {
        AnimatedVisibility(visible = animate,
            enter = fadeIn(initialAlpha = 0.5f)
            ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                val (body, button) = createRefs()
                Column(
                    modifier = Modifier
                        .padding(it)
                        .verticalScroll(rememberScrollState())
                        .background(color = Background)
                        .constrainAs(body) {
                            top.linkTo(parent.top)
                            bottom.linkTo(button.top, 10.dp)

                            height = Dimension.fillToConstraints
                        }
                ) {
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
                                    enter = slideInHorizontally()
                                )
                                .clickable {
                                    navigateUp()
                                },
                            contentDescription = null
                        )

                        Text(
                            text = "Calculate", modifier = Modifier
                                .weight(1f)
                                .animateEnterExit(
                                    enter = fadeIn()
                                )
                                .padding(end = 24.dp),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center, color = Color.White
                        )

                    }

                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(
                            text = "Destination", fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Card(
                            modifier = Modifier
                                .padding(0.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 0.5.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .animateEnterExit(
                                        enter = slideInVertically(
                                            initialOffsetY = {
                                                it / 2
                                            }
                                        ),
                                    )
                            ) {
                                TextInputV2(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    value = "",
                                    onValueChange = {},
                                    label = "Sender location",
                                    trailingIcon = { /*TODO*/ },
                                    cornerRadius = 10.dp,
                                    leadingIcon = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_outbox_24),
                                                contentDescription = null,
                                                tint = IconGrey
                                            )

                                            Spacer(modifier = Modifier.width(8.dp))
                                            Divider(
                                                modifier = Modifier.size(
                                                    width = 1.dp,
                                                    height = 30.dp
                                                )
                                            )
                                        }
                                    },
                                    containerColor = TextInputGrey
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                TextInputV2(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    value = "",
                                    onValueChange = {},
                                    label = "Receiver location",
                                    trailingIcon = { /*TODO*/ },
                                    cornerRadius = 10.dp,
                                    leadingIcon = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_move_to_inbox_24),
                                                contentDescription = null,
                                                tint = IconGrey
                                            )

                                            Spacer(modifier = Modifier.width(8.dp))
                                            Divider(
                                                modifier = Modifier.size(
                                                    width = 1.dp,
                                                    height = 30.dp
                                                )
                                            )
                                        }
                                    },
                                    containerColor = TextInputGrey
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                TextInputV2(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    value = "",
                                    onValueChange = {},
                                    label = "Approx Weight",
                                    trailingIcon = { /*TODO*/ },
                                    cornerRadius = 10.dp,
                                    leadingIcon = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.baseline_scale_24),
                                                contentDescription = null,
                                                tint = IconGrey
                                            )

                                            Spacer(modifier = Modifier.width(8.dp))
                                            Divider(
                                                modifier = Modifier.size(
                                                    width = 1.dp,
                                                    height = 30.dp
                                                )
                                            )
                                        }
                                    },
                                    containerColor = TextInputGrey
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Packaging", fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "What are you sending?",
                            fontSize = 20.sp,
                            color = TextGrey
                        )

                        TextInputV2(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            value = "Box", onValueChange = {},
                            label = "", trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null, tint = IconGrey
                                )
                            },
                            enabled = false,
                            cornerRadius = 10.dp,
                            leadingIcon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_package),
                                        contentDescription = null, tint = IconGrey
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Divider(modifier = Modifier.size(width = 1.dp, height = 30.dp))
                                }
                            }
                        )

                        Text(
                            text = "Categories", fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "What are you sending?",
                            fontSize = 20.sp,
                            color = TextGrey
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        FlowRow(horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.animateEnterExit(
                                enter = slideInHorizontally(
                                    initialOffsetX = {
                                        it / 2
                                    }
                                ),

                                )) {
                            Mock.categories.forEachIndexed { index, s ->
                                CategoryItem(
                                    selectedIndex = selectedIndex,
                                    index = index,
                                    label = s
                                ) {
                                    selectedIndex = it
                                }
                            }
                        }
                    }
                }

                Button(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .constrainAs(button) {
                            bottom.linkTo(parent.bottom, 30.dp)
                        }
                        .animateEnterExit(
                            enter = slideInVertically(
                                initialOffsetY = {
                                    it / 2
                                }
                            ),
                        )
                        .fillMaxWidth(),
                    onClick = {
                        onPrimaryButtonClicked()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = DarkOrange
                    ),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(text = "Calculate", fontSize = 16.sp)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CategoryItem(
    selectedIndex: Int,
    index: Int,
    label: String,
    onClick: (Int) -> Unit
) {

    AssistChip(
        modifier = Modifier,
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selectedIndex == index)
                Color.Black else Color.Transparent
        ),
        onClick = {
            onClick(index)
        },
        label = {
            Text(text = label, color = if (selectedIndex == index)
                Color.White else Color.Black)
        },
        leadingIcon = {
            if (selectedIndex == index) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = if (selectedIndex == index)
                        Color.White else Color.Transparent
                )
            }
        }
    )
}

@Preview
@Composable
fun CalculatePreview() {
    CalculateComposable(
        navigateUp = {},
        onPrimaryButtonClicked = {}
    )
}