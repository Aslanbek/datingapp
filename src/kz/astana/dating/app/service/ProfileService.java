package kz.astana.dating.app.service;

import kz.astana.dating.app.dao.ProfileDao;
import kz.astana.dating.app.model.Profile;

import java.util.List;
import java.util.Optional;

public class ProfileService {

    private static final ProfileService INSTANCE = new ProfileService();
    private final ProfileDao dao = ProfileDao.getInstance();

    public static ProfileService getInstance() {
        return INSTANCE;

    }

    public ProfileService() {

    }

    public Profile save(Profile profile) {
        return dao.save(profile);
    }

    public Optional<Profile> findById(Long id) {
        if (id == null) return Optional.empty();
        return dao.findById(id);
    }

    public boolean delete(Long id) {
        if (id == null) return false;
        return dao.delete(id);
    }

    public void update(Profile profile) {
        dao.update(profile);
    }

    public List<Profile> findAll() {
        return dao.findAll();
    }
}