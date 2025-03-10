package com.example.dsw52839.model

data class NoteModel(
    val id: Int,
    val title: String,
    val description: String,
)



fun dummyNotes(): List<NoteModel> {
    val items = arrayListOf<NoteModel>()

//    for (i in 1..20) {
    for (i in 0..2) {
        items.add(
            NoteModel(i, "Title $i", "Description $i")
        )
    }

    return items
}