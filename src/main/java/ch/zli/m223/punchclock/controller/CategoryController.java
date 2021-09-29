package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.service.CategoryService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
@Tag(name = "categories", description = "Handling of categories")
public class CategoryController {

    @Inject
    CategoryService CategoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> list() {
        return CategoryService.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Category findById(@PathParam("id") Long id) {
        return CategoryService.findById(id);
    }

    @Path("/{id}/projects")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> findProjectsByCategory(@PathParam("id") Long id) {
        return CategoryService.findProjectsByCategory(id);
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    public void delete(@PathParam("id") Long id) {
        CategoryService.delete(id);
    }

    @Path("/{id}")
    @PUT
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category update(Category category, @PathParam("id") Long id) {
        return CategoryService.update(category, id);
    }

    @POST
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Category add(Category category) {
        return CategoryService.create(category);
    }

}
