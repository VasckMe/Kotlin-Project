package com.example.dsw52839.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dsw52839.model.NoteModel
import com.example.dsw52839.utils.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(noteID: Int): ViewModel() {
    private val repository: NotesRepository = NotesRepository.getInstance()
    private val _showConfirmationDialog = MutableStateFlow(false)
    private var _title: MutableStateFlow<String> = MutableStateFlow("")
    private var _description = MutableStateFlow("")
    private var _noteId: Int = noteID
    private val _scope = viewModelScope

    val showConfirmationDialog = _showConfirmationDialog.asStateFlow()
    val title = _title.asStateFlow()
    val description = _description.asStateFlow()

    init {
        if (noteID != -1) {
            val note = repository.get(noteID)

            if (note != null) {
                _title.value = note.title
                _description.value = note.description
            }
        }
    }

    fun titleOnValueChange(value: String) {
        _title.value = value
    }

    fun descriptionOnValueChange(value: String) {
        _description.value = value
    }

    fun saveOrUpdate(onChanges: () -> Unit) {
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
            onChanges()
        }
    }

    fun deleteNote(onDeleted: () -> Unit) {
        _scope.launch {
            if (_noteId != -1) {
                repository.delete(_noteId)
                onDeleted()
            }
        }
    }

    fun hideConfirmationDialog() {
        _showConfirmationDialog.value = false
    }

    fun showConfirmationDialog() {
        _showConfirmationDialog.value = true
    }
}