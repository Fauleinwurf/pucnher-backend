package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import io.quarkus.security.Authenticated;
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
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a list of all users")
    public List<User> list() {
        return userservice.findAll();
    }

    @GET
    @Path("/password-maxLength/{maxLength}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a list of all users where the password is longer than the provided maxLength")
    public List<User> listWherePasswordIsLongerThan6Character(@PathParam("maxLength") int maxLength) {
        return userservice.findAllUsersWherePasswordIsLongerThanMaxLength(maxLength);
    }

    @GET
    @Path("/{id}")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a user by the provided id in the url")
    public User findById(@PathParam("id") Long id) {
        return userservice.findById(id);
    }

    @GET
    @Path("/username/{username}")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a user by the provided username in the url")
    public User findByUsername(@PathParam("username") String username) {
        return userservice.findByUsername(username);
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    @Operation(summary = "Deletes a user by the provided id in the url")
    public void delete(@PathParam("id") Long id) {
        userservice.delete(id);
    }

    @PUT
    @Path("/{id}")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates and returns the user provided in the request-body, if its id matches the id in the url")
    public User update(User user, @PathParam("id") Long id) {
        return userservice.update(user, id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Inserts and returns the project provided in the request-body")
    public User add(User user) {
        return userservice.create(user);
    }
}
