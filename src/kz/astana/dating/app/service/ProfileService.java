package kz.astana.dating.app.service;

import kz.astana.dating.app.dao.ProfileDao;
import kz.astana.dating.app.dto.ProfileGetDto;
import kz.astana.dating.app.mapper.ProfileGetDtoMapper;
import kz.astana.dating.app.model.Profile;

import java.util.List;
import java.util.Optional;

public class ProfileService {

    private static final ProfileService INSTANCE = new ProfileService();
    private final ProfileDao dao = ProfileDao.getInstance();
    private final ProfileGetDtoMapper profileGetDtoMapper = ProfileGetDtoMapper.getInstance();

    public static ProfileService getInstance() {
        return INSTANCE;

    }

    public ProfileService() {

    }

    public Long save(Profile profile) {
        return dao.save(profile).getId();
    }

    public Optional<ProfileGetDto> findById(Long id) {
        if (id == null) return Optional.empty();
        return dao.findById(id).map(profileGetDtoMapper::map);
    }

    public boolean delete(Long id) {
        if (id == null) return false;
        return dao.delete(id);
    }

    public void update(Profile profile) {
        dao.update(profile);
    }

    public List<ProfileGetDto> findAll() {
        return dao.findAll().stream().map(profileGetDtoMapper::map).toList();
    }
}
