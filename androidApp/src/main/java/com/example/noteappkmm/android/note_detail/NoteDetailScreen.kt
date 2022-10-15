package com.example.noteappkmm.android.note_detail

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.noteappkmm.android.note_list.HideableSearchTextField
import com.example.noteappkmm.android.note_list.NoteItem

@Composable
fun NoteDetailScreen(
    navController: NavController,
    noteId: Long,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val hasNoteBeenSaved by viewModel.hasNoteBeenSaved.collectAsState()

    LaunchedEffect(key1 = hasNoteBeenSaved) {
        if (hasNoteBeenSaved) {
            navController.popBackStack()
        }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = viewModel::saveNote,
            backgroundColor = Color.Black
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Save Note",
                tint = Color.White
            )
        }
    }) { padding ->
        Column(
            modifier = Modifier
                .background(Color(state.noteColor))
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TransparentHintTextField(
                text = state.noteTitle,
                hint = "Enter  a title",
                isHintVisible = state.isNoteTitleHintVisible,
                onValueChanged = viewModel::onNoteTitleChanged,
                onFocusChanged = {
                    viewModel.onNoteTitleFocusChanged(it.isFocused)
                },
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height((16.dp)))
            TransparentHintTextField(
                text = state.noteContent,
                hint = "Enter some content",
                isHintVisible = state.isNoteContentHintVisible,
                onValueChanged = viewModel::onNoteContentChanged,
                onFocusChanged = {
                    viewModel.onNoteContentFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier.weight(1f)
            )

        }
    }
}