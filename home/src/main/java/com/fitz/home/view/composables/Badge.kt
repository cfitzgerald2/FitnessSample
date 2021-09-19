package com.fitz.home.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fitz.home.R

@Composable
fun Badge(text: String) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.sizeIn(minHeight = 36.dp)){
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_star_border_24),
            "Badge background",
            modifier = Modifier.sizeIn(minWidth = 32.dp, minHeight = 32.dp)
        )
        Text(text = text, modifier = Modifier.padding(horizontal = 16.dp))
    }
}