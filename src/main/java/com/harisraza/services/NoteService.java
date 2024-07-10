package com.harisraza.services;

import com.harisraza.models.Note;
import java.util.List;

public interface NoteService {
    Note createNote(Note note);
    Note updateNote(int noteId, Note noteToUpdate);
    Note getNote(int noteId);
    void deleteNote(int noteId);
    List<Note> getAllNotes();
    List<Note> getNotesByTitle(String title);
    Note updateNoteContent(int noteId, String content);
}
