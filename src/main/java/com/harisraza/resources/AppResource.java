package com.harisraza.resources;

import com.harisraza.models.Note;
import com.harisraza.services.impl.NoteServiceImpl;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.List;

@Path("/api")
public class AppResource {

    @Inject
    private NoteServiceImpl noteService;

    @Path("/note")
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNote(Note note) {
        note = this.noteService.createNote(note);
        return Response.status(Status.CREATED).entity(note).build();
    }

    @Path("/note/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNote(@PathParam("id") int noteId, Note noteToUpdate) {
        Note updatedNote = this.noteService.updateNote(noteId, noteToUpdate);
        return Response.status(Status.OK).entity(updatedNote).build();
    }

    @Path("/note/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNoteById(@PathParam("id") int noteId) {
        Note note = this.noteService.getNoteById(noteId);
        return Response.status(Status.OK).entity(note).build();
    }

    @Path("/note/{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteNoteById(@PathParam("id") int noteId) {
        this.noteService.deleteNoteById(noteId);
        return Response.ok("Note deleted.").build();
    }

    @Path("/notes")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNotes() {
        List<Note> notes = this.noteService.getAllNotes();
        return Response.status(Status.OK).entity(notes).build();
    }

    @Path("/notes/title/{title}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotesByTitle(@PathParam("title") String title) {
        List<Note> notes = this.noteService.getNotesByTitle(title);
        return Response.status(Status.OK).entity(notes).build();
    }

    @Path("/note/{id}/content")
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateNoteContent(@PathParam("id") int noteId, String content) {
        Note updatedNote = this.noteService.updateNoteContent(noteId, content);
        return Response.status(Status.OK).entity(updatedNote).build();
    }

    // New APIs

    @Path("/notes/count")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response countNotes() {
        long count = this.noteService.countNotes();
        return Response.ok("Total number of notes: " + count).build();
    }

    @Path("/notes/summary")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotesSummary() {
        List<String> summaries = this.noteService.getNotesSummary();
        return Response.status(Status.OK).entity(summaries).build();
    }

    @Path("/notes/recent")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRecentNotes() {
        List<Note> recentNotes = this.noteService.getRecentNotes();
        return Response.status(Status.OK).entity(recentNotes).build();
    }

    @Path("/notes/average-length")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAverageNoteLength() {
        double averageLength = this.noteService.getAverageNoteLength();
        return Response.ok("Average length of notes: " + averageLength).build();
    }

    @Path("/notes/{id}/tags")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTagsToNote(@PathParam("id") int noteId, List<String> tags) {
        Note note = this.noteService.addTagsToNote(noteId, tags);
        return Response.status(Status.OK).entity(note).build();
    }
}
