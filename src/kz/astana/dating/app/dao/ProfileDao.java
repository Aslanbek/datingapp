package kz.astana.dating.app.dao;

import kz.astana.dating.app.model.Profile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileDao {
    private static final ProfileDao INSTANCE = new ProfileDao();
    private final ConcurrentHashMap<Long, Profile> storage;
    private final AtomicLong idStorage;

    public ProfileDao() {
        Long ids[] = {1L,2L,3L,4L,5L};
        this.storage = new ConcurrentHashMap<>();
        for(Long id:ids){
            Profile profile = new Profile();
            profile.setId(id);
            profile.setEmail("email" + id + "@gmail.com");
            profile.setName("Name" + id);
            profile.setSurname("Surname" + id);
            profile.setAbout("About" + id);
            this.storage.put(id, profile);
        }
        this.idStorage = new AtomicLong(1L);
    }

    public static ProfileDao getInstance() {
        return INSTANCE;
    }

    public Profile save(Profile profile) {
        long id = idStorage.incrementAndGet();
        profile.setId(id);
        storage.put(id, profile);
        System.out.println(storage.values());
        return profile;
    }

    public Optional<Profile> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public boolean delete(Long id) {
        return storage.remove(id) != null;
    }

    public void update(Profile profile) {
        Long id = profile.getId();
        if (id == null) return;
        storage.put(id, profile);
    }

    public List<Profile> findAll() {
        return new ArrayList<>(storage.values());
    }

    //TODO delete, update, findAll
}
