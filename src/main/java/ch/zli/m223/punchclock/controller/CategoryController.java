package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.service.CategoryService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;
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
    @Operation(summary = "Lists all Entries", description = "")
    public List<Category> list() {
        return CategoryService.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lists an Category with a specific id", description = "")
    public Category findById(@PathParam("id") Long id) {
        return CategoryService.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Authenticated
    @Operation(summary = "Deletes an Category", description = "")
    public void delete(@PathParam("id") Long id) {
        CategoryService.deleteCategory(id);
    }

    @Path("/{id}")
    @PUT
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an Category with a specific id", description = "")
    public Category update(Category Category, @PathParam("id") Long id) {
        return CategoryService.updateCategory(Category, id);
    }

    @POST
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Add a new Category", description = "The newly created Category is returned. The id may not be passed.")
    public Category add(Category Category) {
        return CategoryService.createCategory(Category);
    }

}
