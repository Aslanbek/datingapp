package kz.astana.dating.app.dao;

import kz.astana.dating.app.model.Gender;
import kz.astana.dating.app.model.Profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileDao {
    private static final ProfileDao INSTANCE = new ProfileDao();
    private final ConcurrentHashMap<Long, Profile> storage;
    private final AtomicLong idStorage;

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
            profile.setBirthDate(LocalDate.now().minusYears(20 - id));
            this.storage.put(id, profile);
        }
        this.idStorage = new AtomicLong(ids.length);

/*        Profile profile = new Profile();
        profile.setId(1L);
        profile.setEmail("email1@gmail.com");
        profile.setName("Name1");
        profile.setSurname("Surname1");
        profile.setAbout("About1");
        this.storage.put(1L, profile);
        Profile profile2 = new Profile();
        profile2.setId(2L);
        profile2.setEmail("email2@gmail.com");
        profile2.setName("Name2");
        profile2.setSurname("Surname2");
        profile2.setAbout("About2");
        this.storage.put(2L, profile2);
        this.idStorage = new AtomicLong(3L);*/
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
