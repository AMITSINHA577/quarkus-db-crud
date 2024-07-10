package com.harisraza.resources;

import java.util.List;

import com.harisraza.models.Note;
import com.harisraza.services.impl.NoteServiceImpl;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

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
    public Response getNote(@PathParam("id") int noteId) {
        Note note = this.noteService.getNote(noteId);
        return Response.status(Status.FOUND).entity(note).build();
    }

    @Path("/note/{id}")
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteNote(@PathParam("id") int noteId) {
        this.noteService.deleteNote(noteId);
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

}
