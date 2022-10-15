package com.example.noteappkmm.domain.note

import com.example.noteappkmm.domain.time.DateTimeUtil

class SearchNote {
    fun execute(notes: List<Note>, query: String): List<Note> {
        if (query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.title.trim().toLowerCase().contains(query.lowercase()) ||
                    it.content.trim().contains(query.lowercase())
        }.sortedBy {
            DateTimeUtil.toEpochMillis((it.created))
        }
    }
}