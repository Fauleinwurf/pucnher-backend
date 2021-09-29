package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Project;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ProjectService {
    @Inject
    private EntityManager entityManager;

    public ProjectService() {
    }

    @Transactional
    public Project create(Project project) {
        entityManager.persist(project);
        return project;
    }

    @Transactional
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    @Transactional
    public Project update(Project project, Long id) {
        this.ensureIdMatches(project, id);
        return entityManager.merge(project);
    }

    public Project findById(Long id) {
        return ensureProjectExists(id);
    }

    @SuppressWarnings("unchecked")
    public List<Project> findAll() {
        var query = entityManager.createQuery("FROM Project");
        return query.getResultList();
    }

    private void ensureIdMatches(Project project, Long id) {
        if (!Objects.equals(project.getId(), id)) {
            throw new IllegalArgumentException("Id of URL and project don't match");
        }
    }

    private Project ensureProjectExists(Long id) {
        Project project = entityManager.find(Project.class, id);
        if (project == null) {
            throw new EntityNotFoundException("Can't find project for ID "
                    + id);
        }
        return project;
    }
}
