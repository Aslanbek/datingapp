package kz.astana.dating.app.dao;

import kz.astana.dating.app.dto.ProfileGetDto;
import kz.astana.dating.app.mapper.ProfileGetDtoMapper;
import kz.astana.dating.app.model.Gender;
import kz.astana.dating.app.model.Profile;
import kz.astana.dating.app.model.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileDao {
    private static final ProfileDao INSTANCE = new ProfileDao();
    private final ConcurrentHashMap<Long, ProfileGetDto> storage;
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
            profile.setStatus(id % 2 == 0 ? Status.ACTIVE : Status.INACTIVE);
            profile.setBirthDate(LocalDate.now().minusYears(20 - id));
            this.storage.put(id, ProfileGetDtoMapper.getInstance().map(profile));
        }
        this.idStorage = new AtomicLong(ids.length);
    }

    public static ProfileDao getInstance() {
        return INSTANCE;
    }

    public ProfileGetDto save(ProfileGetDto profile) {
        long id = idStorage.incrementAndGet();
        profile.setId(id);
        storage.put(id, profile);
        System.out.println(storage.values());
        return profile;
    }

    public Optional<ProfileGetDto> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public boolean delete(Long id) {
        return storage.remove(id) != null;
    }

    public void update(ProfileGetDto profile) {
        Long id = profile.getId();
        if (id == null) return;
        storage.put(id, profile);
    }

    public List<ProfileGetDto> findAll() {
        return new ArrayList<>(storage.values());
    }

    //TODO delete, update, findAll
}
