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
    public Category createCategory(Category Category) {
        entityManager.persist(Category);
        return Category;
    }

    @Transactional
    public void deleteCategory(Long id) {
        entityManager.remove(findById(id));
    }

    @Transactional
    public Category updateCategory(Category Category, Long id) {
        this.ensureIdMatches(Category, id);
        return entityManager.merge(Category);
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

    private void ensureIdMatches(Category Category, Long CategoryId) {
        if (!Objects.equals(Category.getId(), CategoryId)) {
            throw new IllegalArgumentException("Id of URL and Category don't match");
        }
    }

    private Category ensureCategoryExists(Long id) {
        Category Category = entityManager.find(Category.class, id);
        if (Category == null) {
            throw new EntityNotFoundException("Can't find Category for ID "
                    + id);
        }
        return Category;
    }
}
