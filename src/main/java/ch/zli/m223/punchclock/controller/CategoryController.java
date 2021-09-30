package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.service.CategoryService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Authenticated
@Path("/categories")
@Tag(name = "categories", description = "Handling of categories")
public class CategoryController {

    @Inject
    CategoryService CategoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a list of all categories")
    public List<Category> list() {
        return CategoryService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a category by the provided id in the url")
    public Category findById(@PathParam("id") Long id) {
        return CategoryService.findById(id);
    }

    @GET
    @Path("/{id}/projects")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a list of all Projects that are in relation to the category with the provided id")
    public List<Project> findProjectsByCategory(@PathParam("id") Long id) {
        return CategoryService.findProjectsByCategory(id);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes a category by the provided id in the url")
    public void delete(@PathParam("id") Long id) {
        CategoryService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates and returns the category provided in the request-body, if its id matches the id in the url")
    public Category update(Category category, @PathParam("id") Long id) {
        return CategoryService.update(category, id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Inserts and returns the category provided in the request-body")
    public Category add(Category category) {
        return CategoryService.create(category);
    }

}
