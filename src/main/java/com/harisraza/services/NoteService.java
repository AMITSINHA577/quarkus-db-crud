package com.harisraza.services;

import com.harisraza.models.Note;
import java.util.List;

public interface NoteService {
    Note createNote(Note note);
    Note updateNote(int noteId, Note noteToUpdate);
    Note getNoteById(int noteId);
    void deleteNoteById(int noteId);
    List<Note> getAllNotes();
    List<Note> getNotesByTitle(String title);
    Note updateNoteContent(int noteId, String content);
    long countNotes();
    List<String> getNotesSummary();
    List<Note> getRecentNotes();
    double getAverageNoteLength();
    Note addTagsToNote(int noteId, List<String> tags);
}
