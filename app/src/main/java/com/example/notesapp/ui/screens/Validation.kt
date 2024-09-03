package com.example.notesapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Validaion(isTitleError : Boolean,isDetailsError : Boolean,modifier: Modifier = Modifier) {
    if (isTitleError) {
        Text(
            text = "Title cannot be empty",
            color = androidx.compose.material3.MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
    if (isDetailsError) {
        Text(
            text = "Details cannot be empty",
            color = androidx.compose.material3.MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }

}