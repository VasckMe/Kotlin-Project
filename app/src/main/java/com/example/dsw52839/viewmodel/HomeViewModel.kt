package com.example.dsw52839.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsw52839.model.NoteModel
import com.example.dsw52839.utils.NotesRepository
import com.example.dsw52839.utils.Routes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private val TAG = "HomeViewModel"

    private val repository: NotesRepository = NotesRepository.getInstance()

    val notesList = mutableStateListOf<NoteModel>()
    private val _eventFlow = MutableSharedFlow<HomeEvent>()
    val eventFlow: SharedFlow<HomeEvent> = _eventFlow.asSharedFlow()
    val _scope = viewModelScope

    init {

        val items = repository.getAll()
        notesList.addAll(items)

        _scope.launch {
            repository.newNoteInsertionListener.collect { newNote: NoteModel ->
                notesList.add(0, newNote)
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

    fun listItemOnClick(id: Int) = _scope.launch(Dispatchers.Main) {
        Log.d(TAG, "listItemOnClick: $id")
        val route = Routes.notePage + "/$id"
        _eventFlow.emit(HomeEvent.NavigateNext(route))
    }


    sealed class HomeEvent {
        data class NavigateNext(val route: String): HomeEvent()

    }
}