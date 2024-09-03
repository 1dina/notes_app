package com.example.notesapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.database.Note
import com.example.notesapp.ui.viewmodels.NoteViewModel

@Composable
fun EditingNoteScreen(
    id: Int,
    noteDetails: String,
    noteTitle: String?,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel()
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp)
    ) {
        var details by remember { mutableStateOf(noteDetails) }
        var title by remember { mutableStateOf(noteTitle) }
        var isTitleError by remember { mutableStateOf(false) }
        var isDetailsError by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = title ?: "",
            onValueChange = {
                title = it
                isTitleError = false
            },
            label = {
                Text(text = "Note Title")
            }, isError = isTitleError,
            modifier = modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = details,
            onValueChange = {
                details = it
                isDetailsError = false
            },
            label = {
                Text(text = "Note Details")
            }, isError = isDetailsError,
            modifier = modifier.fillMaxWidth()
        )
        Validaion(isTitleError = isTitleError, isDetailsError = isDetailsError)
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            OutlinedButton(
                onClick = {
                    isTitleError = title.isNullOrEmpty()
                    isDetailsError = details.isEmpty()
                    if (!isTitleError && !isDetailsError) {
                        viewModel.upsert(Note(id, title = title, details = details))
                        Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Update")
            }
            OutlinedButton(
                onClick = {
                    isTitleError = title.isNullOrEmpty()
                    isDetailsError = details.isEmpty()
                    if (!isTitleError && !isDetailsError) {
                        viewModel.delete(Note(id, title = title, details = details))
                        Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                },
                modifier = modifier
                    .weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(text = "Delete")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditingNoteScreenPreview() {
    EditingNoteScreen(2, "test details", "note title", rememberNavController())
}