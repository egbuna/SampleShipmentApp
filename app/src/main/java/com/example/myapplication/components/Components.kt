package com.example.myapplication.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputV2(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    mutableInteractionSource: MutableInteractionSource = remember {
        MutableInteractionSource()
    },
    label: String,
    maxLines: Int = 1,
    error: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    cornerRadius: Dp = 30.dp,
    trailingIcon: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit = {
        Icon(imageVector = Icons.Default.Search,
            tint = Color(0xFF4a3391),
            contentDescription = null)
    },
    enabled: Boolean = true,
    containerColor: Color = Color.White,
    onSearchClicked: () -> Unit = {}
) {

    val hasFocus by mutableInteractionSource.collectIsFocusedAsState()

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(cornerRadius)
            )
            .border(
                color = Color.Transparent,
                width = 0.dp,
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(vertical = 3.dp),
        interactionSource = mutableInteractionSource,
        maxLines = maxLines,
        singleLine = maxLines == 1,
        leadingIcon = leadingIcon,
        textStyle = TextStyle(
            //fontFamily = interFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            color = Color(0xFF000000),
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClicked()
            }
        ),
        placeholder = {
                      Text(text = label, fontSize = 15.sp)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = containerColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color(0xFF143EBD),
            errorCursorColor = Color(0xFFF47079),
            errorTrailingIconColor = Color(0xFFF47079),
            errorIndicatorColor = Color.Transparent,
            errorLabelColor = Color(0xFFF47079),
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius),
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon,
        enabled = enabled
    )
}

@Preview
@Composable
fun TextInputV2Preview() {
    TextInputV2(
        value = "Hello",
        label = "Hello",
        onValueChange = {},
        trailingIcon = {}
    )
}