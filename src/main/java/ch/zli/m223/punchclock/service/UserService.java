package ch.zli.m223.punchclock.service;

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

    public UserService() {
    }

    @Transactional
    public User createUser(User User) {
        entityManager.persist(User);
        return User;
    }

    @Transactional
    public void deleteUser(Long id) {
        entityManager.remove(findById(id));
    }

    @Transactional
    public User updateUser(User User, Long id) {
        this.ensureIdMatches(User, id);
        return entityManager.merge(User);
    }

    @Transactional
    public User findById(Long id) {
        return ensureUserExists(id);
    }

    @Transactional
    public User findByUsername(String username) {
        System.out.println(username);
        return entityManager.createQuery(
                        "SELECT u from User u WHERE u.username = :username", User.class).
                setParameter("username", username).getSingleResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    private void ensureIdMatches(User User, Long UserId) {
        if (!Objects.equals(User.getId(), UserId)) {
            throw new IllegalArgumentException("Id of URL and User don't match");
        }
    }

    private User ensureUserExists(Long id) {
        User User = entityManager.find(User.class, id);
        if (User == null) {
            throw new EntityNotFoundException("Can't find User for ID "
                    + id);
        }
        return User;
    }
}
