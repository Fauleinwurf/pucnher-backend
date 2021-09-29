package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager entityManager;

    public CategoryService() {
    }

    @Transactional
    public Category create(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Transactional
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }

    @Transactional
    public Category update(Category category, Long id) {
        this.ensureIdMatches(category, id);
        return entityManager.merge(category);
    }

    @Transactional
    public Category findById(Long id) {
        return ensureCategoryExists(id);
    }

    @SuppressWarnings("unchecked")
    public List<Category> findAll() {
        var query = entityManager.createQuery("FROM Category");
        return query.getResultList();
    }

    private void ensureIdMatches(Category category, Long CategoryId) {
        if (!Objects.equals(category.getId(), CategoryId)) {
            throw new IllegalArgumentException("Id of URL and Category don't match");
        }
    }

    private Category ensureCategoryExists(Long id) {
        Category category = entityManager.find(Category.class, id);
        if (category == null) {
            throw new EntityNotFoundException("Can't find Category for ID "
                    + id);
        }
        return category;
    }
}
