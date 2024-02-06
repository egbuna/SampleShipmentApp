package com.example.myapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FireTruck
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SuccessScreen() {
    SuccessComposable()
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SuccessComposable() {
    var animate by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        animate = true
    }

    var amount by remember { mutableIntStateOf(1200) }
    val amountCounter by animateIntAsState(
        targetValue = amount,
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        ), label = "AmountAnimation"
    )
    LaunchedEffect(Unit) {
        amount = 1444
    }

    AnimatedVisibility(visible = animate,
        enter = fadeIn(initialAlpha = 0.5f)
    ) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(100.dp))

            Row (verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.animateEnterExit(
                    enter = slideInHorizontally (
                        animationSpec = tween(durationMillis = 500, easing = EaseIn))
                )
            ){
                Text(text = "MoveMate",
                    color = Color(0xFF4a3391),
                    fontSize = 35.sp,
                    fontWeight = FontWeight(700),
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.width(10.dp))
                Icon(imageVector = Icons.Outlined.FireTruck,
                    contentDescription = null,
                    tint = Color(0xFFf17a21),
                    modifier = Modifier.size(35.dp)
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            Icon(painter = painterResource(id = R.drawable.ic_package),
                tint = Color(0xFF899498),
                contentDescription = null, modifier = Modifier.size(150.dp)
                    .animateEnterExit(
                        enter = scaleIn(animationSpec =
                        tween(durationMillis = 1000, easing = EaseInExpo)
                        )
                    ))

            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Total Estimated Amount",
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight(500),
                modifier = Modifier.animateEnterExit(
                    enter = slideInVertically(
                        initialOffsetY = {
                            it / 2
                        },
                        animationSpec = tween(durationMillis = 500, easing = EaseIn)
                    ))
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row (verticalAlignment = Alignment.Bottom,
                modifier = Modifier.animateEnterExit(
                    enter = slideInVertically(
                        initialOffsetY = {
                            it / 2
                        },
                        animationSpec = tween(durationMillis = 500, easing = EaseIn)
                    ))) {
                Text(text = "$$amountCounter",
                    color = Color(0xFF378461),
                    fontSize = 30.sp,
                    fontWeight = FontWeight(500)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "USD",
                    color = Color(0xFF378461),
                    fontSize = 25.sp,
                    fontWeight = FontWeight(500)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "This amount is estimated this will vary if you change your location or weight",
                color = Color(0xFF899498),
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 40.dp)
                    .animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = {
                                it / 2
                            },
                            animationSpec = tween(durationMillis = 500, easing = EaseIn)
                        ))
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = {
                                it / 2
                            },
                            animationSpec = tween(durationMillis = 500, easing = EaseIn)
                        )),
                onClick = {

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFf17a21)
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "Back to home", fontSize = 16.sp)
            }
        }
    }
}

@Preview
@Composable
fun SuccessComposablePreview() {
    SuccessComposable()
}