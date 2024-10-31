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
    public ProfileGetDto map(Profile profile) {
        ProfileGetDto dto = new ProfileGetDto();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setEmail(profile.getEmail());
        dto.setSurname(profile.getSurname());
        dto.setAbout(profile.getAbout());
        dto.setGender(profile.getGender());
        dto.setBirthDate(profile.getBirthDate());
        dto.setAge(Math.toIntExact(ChronoUnit.YEARS.between(profile.getBirthDate(), LocalDate.now())));
        return dto;
    }
}
