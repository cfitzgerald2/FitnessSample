package com.fitz.home.view.composables.text

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun Title(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colors.contentColorFor(MaterialTheme.colors.surface),
        fontSize = 24.sp
    )
}