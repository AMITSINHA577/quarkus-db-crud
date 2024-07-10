package com.harisraza.services.impl;

import com.harisraza.exceptions.ResourceNotFound;
import com.harisraza.models.Note;
import com.harisraza.repositories.NoteRepository;
import com.harisraza.services.NoteService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class NoteServiceImpl implements NoteService {

    @Inject
    private NoteRepository noteRepo;

    @Override
    @Transactional
    public Note createNote(Note note) {
        this.noteRepo.persistAndFlush(note);
        return note;
    }

    @Override
    @Transactional
    public Note updateNote(int noteId, Note noteToUpdate) {
        Note note = this.findNoteById(noteId);
        note.setContent(noteToUpdate.getContent());
        note.setTitle(noteToUpdate.getTitle());
        this.noteRepo.persistAndFlush(note);
        return note;
    }

    @Override
    public Note getNoteById(int noteId) {
        return this.findNoteById(noteId);
    }

    @Override
    @Transactional
    public void deleteNoteById(int noteId) {
        Note note = this.findNoteById(noteId);
        this.noteRepo.delete(note);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepo.listAll();
    }

    @Override
    public List<Note> getNotesByTitle(String title) {
        return noteRepo.find("title", title).list();
    }

    @Override
    @Transactional
    public Note updateNoteContent(int noteId, String content) {
        Note note = this.findNoteById(noteId);
        note.setContent(content);
        this.noteRepo.persistAndFlush(note);
        return note;
    }

    @Override
    public long countNotes() {
        return noteRepo.count();
    }

    @Override
    public List<String> getNotesSummary() {
        List<Note> notes = noteRepo.listAll();
        List<String> summaries = new ArrayList<>();
        for (Note note : notes) {
            summaries.add("Note id: " + note.getId() + ", Title: " + note.getTitle());
        }
        return summaries;
    }

    @Override
    public List<Note> getRecentNotes() {
        // Assuming "recent" means notes created within the last 7 days
        Date weekAgo = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
        return noteRepo.find("creationDate > ?1", weekAgo).list();
    }

    @Override
    public double getAverageNoteLength() {
        List<Note> notes = noteRepo.listAll();
        if (notes.isEmpty()) {
            return 0;
        }
        int totalLength = 0;
        for (Note note : notes) {
            totalLength += note.getContent().length();
        }
        return (double) totalLength / notes.size();
    }

    @Override
    @Transactional
    public Note addTagsToNote(int noteId, List<String> tags) {
        Note note = this.findNoteById(noteId);
        // Assuming Note entity has a method setTags(List<String> tags)
        note.setTags(tags);
        this.noteRepo.persistAndFlush(note);
        return note;
    }

    private Note findNoteById(long noteId) {
        return this.noteRepo.findByIdOptional(noteId)
                .orElseThrow(() -> new ResourceNotFound("Note", "note id: " + noteId));
    }
}
