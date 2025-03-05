package com.example.dsw52839.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsw52839.model.NoteModel
import com.example.dsw52839.utils.NotesRepository
import com.example.dsw52839.viewmodel.NoteViewModel.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(noteID: Int): ViewModel() {
    private val repository: NotesRepository = NotesRepository.getInstance()

    private var _title: MutableStateFlow<String> = MutableStateFlow("")
    private var _description = MutableStateFlow<String>("")
    var _noteId: Int = noteID

    val title = _title.asStateFlow()
    val description = _description.asStateFlow()

    val _scope = viewModelScope

    init {
        if (noteID != -1) {
            val note = repository.get(noteID)
            Log.d("DetailVIewModel", "${note.id}")
            _title.value = note.title
            _description.value = note.description
        }
    }

    fun titleOnValueChange(value: String) {
        _title.value = value
    }

    fun descriptionOnValueChange(value: String) {
        _description.value = value
    }

    fun saveOrUpdate() {
        _scope.launch {
            val noteModel = NoteModel(
                id = _noteId,
                title = _title.value,
                description = _description.value,
            )

            if (noteModel.id == -1) {
                repository.insert(noteModel)
            } else {
                repository.update(noteModel)
            }
        }
    }

    fun deleteNote() {
        _scope.launch {
            val itemId = _noteId
            repository.delete(itemId)
//        hideConfirmationDialog()
        }
    }
}