package com.ihebchiha.tech_test.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun RoundedButtonWithBorder(
    bgColor: Color,
    height: Dp,
    width: Dp,
    label: String,
    textColor: Color,
    textSize: TextUnit,
    borderRadius: Dp,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(borderRadius),
        modifier = Modifier
            .width(width)
            .height(height)
            .padding(start = 10.dp, end = 10.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = bgColor,
            contentColor = Color.White
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1
        )
    }
}