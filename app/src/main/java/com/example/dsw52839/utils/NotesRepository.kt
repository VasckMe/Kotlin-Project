package com.example.dsw52839.utils

import com.example.dsw52839.model.NoteModel
import com.example.dsw52839.model.dummyNotes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NotesRepository private constructor() {
    private val items = arrayListOf<NoteModel>().apply {
        addAll(dummyNotes())
    }
    private val _newNoteInsertionListener = MutableSharedFlow<NoteModel>()
    private val _updateNoteListener = MutableSharedFlow<NoteModel>()
    private val _deleteNoteListener = MutableSharedFlow<Int>()

    val newNoteInsertionListener: SharedFlow<NoteModel> = _newNoteInsertionListener.asSharedFlow()
    val updateNoteListener: SharedFlow<NoteModel> = _updateNoteListener.asSharedFlow()
    val deleteNoteListener: SharedFlow<Int> = _deleteNoteListener.asSharedFlow()

    companion object {
        private var _instance: NotesRepository? = null

        fun getInstance(): NotesRepository {
            if (_instance == null)
                _instance = NotesRepository()

            return _instance as NotesRepository
        }
    }

    fun getAll(): List<NoteModel> {
        return items
    }

    fun get(id: Int): NoteModel? {
        return items.firstOrNull { it.id == id }
    }

    suspend fun insert(item: NoteModel): Int {
        val newId = items.size
        val newNote = item.copy(
            id = newId
        )
        items.add(newNote)

        _newNoteInsertionListener.emit(newNote)
        return newId
    }

    suspend fun update(item: NoteModel) {
        val itemIndex = items.indexOfFirst { it.id == item.id }
        items[itemIndex] = item

        _updateNoteListener.emit(item)
    }

    suspend fun delete(id: Int) {
        val itemIndex = items.indexOfFirst { it.id == id }

        if (itemIndex != -1) {
            items.removeAt(itemIndex)
        }

        _deleteNoteListener.emit(id)
    }
}