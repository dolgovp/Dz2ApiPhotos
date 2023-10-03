package com.example.dz2apiphotos.ui.theme

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val DarkBlue = Color(0xFF262662)
val LightBlue = Color(0xFFA9C0E2)
val Orange = Color(0xFFFF8000)
val DarkRed = Color(0xFF702737)
val LightGrey = Color(0xFFF6F4F5)

@Composable
fun testTextFieldColors(
    primary: Color = DarkBlue,
    secondary: Color = LightBlue,
    backgroundColor: Color = Color.White

) = TextFieldDefaults.textFieldColors(
    textColor = primary,
    disabledTextColor = secondary,
    cursorColor = primary,
    focusedIndicatorColor = primary,
    unfocusedIndicatorColor = secondary,
    disabledIndicatorColor = secondary,
    backgroundColor = backgroundColor,
    focusedLabelColor = primary,
    unfocusedLabelColor = secondary,
    disabledLabelColor = secondary,
    placeholderColor = primary,
)