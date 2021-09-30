package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Project;
import ch.zli.m223.punchclock.service.ProjectService;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Authenticated
@Path("/projects")
@Tag(name = "projects", description = "Handling of categories")
public class ProjectController {

    @Inject
    ProjectService projectService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a list of all projects")
    public List<Project> list() {
        return projectService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a project by the provided id in the url")
    public Project findById(@PathParam("id") Long id) {
        return projectService.findById(id);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletes a project by the provided id in the url")
    public void delete(@PathParam("id") Long id) {
        projectService.delete(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates and returns the project provided in the request-body, if its id matches the id in the url")
    public Project update(Project Project, @PathParam("id") Long id) {
        return projectService.update(Project, id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Inserts and returns the project provided in the request-body")
    public Project add(Project Project) {
        return projectService.create(Project);
    }
}
