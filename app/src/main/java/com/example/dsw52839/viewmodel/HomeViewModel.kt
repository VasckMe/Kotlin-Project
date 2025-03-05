package com.example.dsw52839.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsw52839.model.NoteModel
import com.example.dsw52839.utils.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val repository: NotesRepository = NotesRepository.getInstance()

    val notesList = mutableStateListOf<NoteModel>()
    private val _chosenNoteId = MutableStateFlow<Int>(0)
    val chosenNoteId: StateFlow<Int> = _chosenNoteId
    val _scope = viewModelScope

    fun changeChosenID(newID: Int) {
        _chosenNoteId.value = newID
    }

    init {
        Log.d("HomeVM", "NOOOOOOOO")
        val items = repository.getAll()
        notesList.addAll(items)

        _scope.launch {
            repository.newNoteInsertionListener.collect { newNote: NoteModel ->
                notesList.add(newNote)
            }
        }

        _scope.launch {
            repository.updateNoteListener.collect { updateNote: NoteModel ->
                val itemIndex = notesList.indexOfFirst { it.id == updateNote.id }

                if (itemIndex != -1) {
                    notesList[itemIndex] = updateNote
                }

            }
        }

        _scope.launch {
            repository.deleteNoteListener.collect { id ->
                val itemIndex = notesList.indexOfFirst { it.id == id }

                if (itemIndex != -1) {
                    notesList.removeAt(itemIndex)
                }

            }
        }

    }
}