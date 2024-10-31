package kz.astana.dating.app.mapper;

import kz.astana.dating.app.dao.ProfileDao;
import kz.astana.dating.app.dto.ProfileGetDto;
import kz.astana.dating.app.model.Profile;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProfileGetDtoMapper implements Mapper<Profile, ProfileGetDto> {
    private static final ProfileGetDtoMapper INSTANCE = new ProfileGetDtoMapper();
    private final ProfileDao dao = ProfileDao.getInstance();

    public static ProfileGetDtoMapper getInstance() {
        return INSTANCE;

    }

    public ProfileGetDtoMapper() {
    }

    @Override
    public ProfileGetDto map(Profile profile, ProfileGetDto dto) {
        ProfileGetDto result = dto;
        if (result == null) {
            result = new ProfileGetDto();
        }
        result.setId(profile.getId());
        result.setName(profile.getName());
        result.setEmail(profile.getEmail());
        result.setSurname(profile.getSurname());
        result.setAbout(profile.getAbout());
        result.setGender(profile.getGender());
        result.setBirthDate(profile.getBirthDate());
        if (profile.getBirthDate() != null) {
            result.setAge(Math.toIntExact(ChronoUnit.YEARS.between(profile.getBirthDate(), LocalDate.now())));
        }
        result.setStatus(profile.getStatus());
        return result;
    }
}
