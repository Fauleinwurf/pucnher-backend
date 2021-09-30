package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.domain.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    @Inject
    private EntryService entryService;

    public UserService() {
    }

    @Transactional
    public User create(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public void delete(Long id) {
        User entityToRemove = findById(id);
        for (Entry entry : entityToRemove.getEntries()) {
            entityManager.remove(entryService.findById(entry.getId()));
        }
        entityManager.remove(entityToRemove);
    }

    @Transactional
    public User update(User user, Long id) {
        this.ensureIdMatches(user, id);
        return entityManager.merge(user);
    }

    public User findById(Long id) {
        return ensureUserExists(id);
    }

    public User findByUsername(String username) {
        return entityManager.createQuery(
                        "SELECT u from User u WHERE u.username = :username", User.class).
                setParameter("username", username).getSingleResult();
    }

    public List<User> findAllUsersWherePasswordIsLongerThanMaxLength(int maxLength) {
        return entityManager.createQuery("From User user GROUP BY user.username HAVING (LENGTH(user.password)) > :maxLength").setParameter("maxLength", maxLength).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    private void ensureIdMatches(User user, Long id) {
        if (!Objects.equals(user.getId(), id)) {
            throw new IllegalArgumentException("Id of URL and User don't match");
        }
    }

    private User ensureUserExists(Long id) {
        User user = entityManager.find(User.class, id);
        if (user == null) {
            throw new EntityNotFoundException("Can't find User for ID "
                    + id);
        }
        return user;
    }
}
