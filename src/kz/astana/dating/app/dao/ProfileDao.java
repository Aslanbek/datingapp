package kz.astana.dating.app.dao;

import kz.astana.dating.app.model.Gender;
import kz.astana.dating.app.model.Profile;
import kz.astana.dating.app.model.Status;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ProfileDao {
    private static final ProfileDao INSTANCE = new ProfileDao();
    private final AtomicLong idStorage;
    private final ConcurrentHashMap<Long, Profile> storage;

    public ProfileDao() {
        this.storage = new ConcurrentHashMap<>();
        //
        Long ids[] = {1L, 2L, 3L, 4L, 5L};
        for (Long id : ids) {
            Profile profile = new Profile();
            profile.setId(id);
            profile.setEmail("email" + id + "@gmail.com");
            profile.setName("Name" + id);
            profile.setSurname("Surname" + id);
            profile.setAbout("About" + id);
            profile.setGender(id % 2 == 0 ? Gender.FEMALE : Gender.OTHER);
            profile.setStatus(id % 2 == 0 ? Status.ACTIVE : Status.INACTIVE);
            profile.setBirthDate(LocalDate.now().minusYears(20 - id));
            profile.setPassword(String.valueOf(ids + "" + ids + "" + ids));
            this.storage.put(id, profile);
        }
        this.idStorage = new AtomicLong(ids.length);
    }

    public static ProfileDao getInstance() {
        return INSTANCE;
    }

    public Profile save(Profile profile) {
        long id = idStorage.incrementAndGet();
        profile.setId(id);
        storage.put(id, profile);
        return profile;
    }

    public Optional<Profile> findById(Long id) {
        if (id == null) return Optional.empty();
        return Optional.ofNullable(storage.get(id));
    }

    public List<Profile> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(Profile profile) {
        Long id = profile.getId();
        if (id == null) return;
        storage.put(id, profile);
    }

    public boolean delete(Long id) {
        return storage.remove(id) != null;
    }

    public Set<String> getAllEmails() {
        return storage.values().stream().map(Profile::getEmail).collect(Collectors.toSet());
    }
}
