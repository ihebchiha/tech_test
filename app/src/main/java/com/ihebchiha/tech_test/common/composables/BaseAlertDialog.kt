package com.ihebchiha.tech_test.common

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.window.DialogProperties

@Composable
fun BaseAlertDialog(
    isShow: MutableState<Boolean>,
    cornerShape: Shape? = MaterialTheme.shapes.medium,
    dismissOnBackPress: Boolean? = false,
    dismissOnClickOutside: Boolean? = false,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = Color.Black,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
    buttons: @Composable () -> Unit
) {
    if (isShow.value) {
        AlertDialog(
            onDismissRequest = { isShow.value = false },
            title = {
                header()
            },
            text = {
                content()
            },
            buttons = {
                buttons()
            },
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress!!,
                dismissOnClickOutside = dismissOnClickOutside!!

            ),
            shape = cornerShape!!,
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )
    }
}