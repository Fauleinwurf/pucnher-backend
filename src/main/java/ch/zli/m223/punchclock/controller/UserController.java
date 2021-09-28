package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Tag(name = "Users", description = "Handles user interactions")
public class UserController {
    @Inject
    UserService userservice;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lists all Entries", description = "")
    public List<User> list() {
        return userservice.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lists an User with a specific id", description = "")
    public User findById(@PathParam("id") Long id) {
        return userservice.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes an User", description = "")
    public void delete(@PathParam("id") Long id) {
        userservice.deleteUser(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an User with a specific id", description = "")
    public User update(User User, @PathParam("id") Long id) {
        return userservice.updateUser(User, id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new User", description = "The newly created User is returned. The id may not be passed.")
    public User add(User User) {
        return userservice.createUser(User);
    }
}
