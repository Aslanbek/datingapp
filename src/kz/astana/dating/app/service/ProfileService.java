package kz.astana.dating.app.service;

import kz.astana.dating.app.dao.ProfileDao;
import kz.astana.dating.app.dto.ProfileGetDto;
import kz.astana.dating.app.dto.ProfileUpdateDto;
import kz.astana.dating.app.dto.RegistrationDto;
import kz.astana.dating.app.mapper.ProfileToProfileGetDtoMapper;
import kz.astana.dating.app.mapper.ProfileUpdateDtoToProfileMapper;
import kz.astana.dating.app.mapper.RegistrationDtoToProfileMapper;
import kz.astana.dating.app.model.exception.DuplicateEmailException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ProfileService {

    private static final ProfileService INSTANCE = new ProfileService();
    private final ProfileDao dao = ProfileDao.getInstance();
    private final ProfileToProfileGetDtoMapper profileToProfileGetDtoMapper = ProfileToProfileGetDtoMapper.getInstance();
    private final ProfileUpdateDtoToProfileMapper profileUpdateDtoToProfileMapper = ProfileUpdateDtoToProfileMapper.getInstance();
    private final RegistrationDtoToProfileMapper registrationDtoToProfileMapper = RegistrationDtoToProfileMapper.getInstance();

    public ProfileService() {

    }

    public static ProfileService getInstance() {
        return INSTANCE;

    }

    public Long save(RegistrationDto dto) {
        return dao.save(registrationDtoToProfileMapper.map(dto)).getId();
    }

    public Optional<ProfileGetDto> findById(Long id) {
        return dao.findById(id).map(profileToProfileGetDtoMapper::map);
    }

    public List<ProfileGetDto> findAll() {
        return dao.findAll().stream().map(profileToProfileGetDtoMapper::map).toList();
    }

    public void update(ProfileUpdateDto dto) {
        dao.findById(dto.getId())
                .ifPresent(profile -> {
                    checkEmail(profile.getEmail(), dto.getEmail());
                    dao.update(profileUpdateDtoToProfileMapper.map(dto, profile));
                });
    }

    public boolean delete(Long id) {
        return dao.delete(id);
    }

    private void checkEmail(String oldEmail, String newEmail) {
        if (newEmail == null) return;
        Set<String> emails = dao.getAllEmails();
        if (!Objects.equals(oldEmail, newEmail) &&
                emails.contains(newEmail)) {
            throw new DuplicateEmailException();
        }
    }

}