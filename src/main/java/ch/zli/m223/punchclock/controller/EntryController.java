package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.service.EntryService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lists all Entries", description = "")
    public List<Entry> list() {
        return entryService.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lists an Entry with a specific id", description = "")
    public Entry findById(@PathParam("id") Long id) {
        return entryService.findById(id);
    }

    @Path("/{id}")
    @Authenticated
    @DELETE
    @Operation(summary = "Deletes an Entry", description = "")
    public void delete(@PathParam("id") Long id) {
        entryService.deleteEntry(id);
    }

    @Path("/{id}")
    @Authenticated
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an Entry with a specific id", description = "")
    public Entry update(Entry entry, @PathParam("id") Long id) {
        return entryService.updateEntry(entry, id);
    }

    @POST
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Entry", description = "The newly created entry is returned. The id may not be passed.")
    public Entry add(Entry entry) {
        return entryService.createEntry(entry);
    }

}
