package com.harisraza.services.impl;

import com.harisraza.exceptions.ResourceNotFound;
import com.harisraza.models.Note;
import com.harisraza.repositories.NoteRepository;
import com.harisraza.services.NoteService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    public Note getNote(int noteId) {
        return this.findNoteById(noteId);
    }

    @Override
    @Transactional
    public void deleteNote(int noteId) {
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

    private Note findNoteById(long noteId) {
        return this.noteRepo.findByIdOptional(noteId).orElseThrow(() -> new ResourceNotFound("Note", "note id: " + noteId));
    }
}
