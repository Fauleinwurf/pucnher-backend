package ch.zli.m223.punchclock.service;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Entry;

@ApplicationScoped
public class EntryService {
    @Inject
    private EntityManager entityManager;

    public EntryService() {
    }

    @Transactional
    public Entry createEntry(Entry entry) {
        if (entry.getCheckIn().isAfter(entry.getCheckOut())) {
            throw new IllegalArgumentException("The checkout time can't be before the checkin time");
        }
        entityManager.persist(entry);
        return entry;
    }

    @Transactional
    public void deleteEntry(Long id) {
        entityManager.remove(findById(id));
    }

    @Transactional
    public Entry updateEntry(Entry entry, Long id) {
        this.ensureIdMatches(entry, id);
        return entityManager.merge(entry);
    }

    @Transactional
    public Entry findById(Long id){
        return ensureEntryExists(id);
    }

    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }

    private void ensureIdMatches(Entry entry, Long entryId){
        if (!Objects.equals(entry.getId(), entryId)){
            throw new IllegalArgumentException("Id of URL and ENTRY don't match");
        }
    }

    private Entry ensureEntryExists(Long id){
        Entry entry = entityManager.find(Entry.class, id);
        if (entry == null) {
            throw new EntityNotFoundException("Can't find entry for ID "
                    + id);
        }
        return entry;
    }
}
