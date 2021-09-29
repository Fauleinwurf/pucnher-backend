package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.UserService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Authenticated
@Path("/users")
@Tag(name = "Users", description = "Handles user interactions")
public class UserController {
    @Inject
    UserService userservice;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> list() {
        return userservice.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User findById(@PathParam("id") Long id) {
        return userservice.findById(id);
    }

    @Path("/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User findByUsername(@PathParam("username") String username) {
        return userservice.findByUsername(username);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        userservice.delete(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User update(User user, @PathParam("id") Long id) {
        return userservice.update(user, id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User add(User user) {
        return userservice.create(user);
    }
}
