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

@Authenticated
@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a list of all entries")
    public List<Entry> list() {
        return entryService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a entry by the provided id in the url")
    public Entry findById(@PathParam("id") Long id) {
        return entryService.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes a entry by the provided id in the url")
    public void delete(@PathParam("id") Long id) {
        entryService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates and returns the entry provided in the request-body, if its id matches the id in the url")
    public Entry update(Entry entry, @PathParam("id") Long id) {
        return entryService.update(entry, id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Inserts and returns the entry provided in the request-body")
    public Entry add(Entry entry) {
        return entryService.create(entry);
    }

}
