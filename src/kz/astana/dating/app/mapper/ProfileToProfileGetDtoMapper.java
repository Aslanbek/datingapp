package kz.astana.dating.app.mapper;

import kz.astana.dating.app.dto.ProfileGetDto;
import kz.astana.dating.app.model.Profile;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileToProfileGetDtoMapper implements Mapper<Profile, ProfileGetDto> {
    private static final ProfileToProfileGetDtoMapper INSTANCE = new ProfileToProfileGetDtoMapper();

    public static ProfileToProfileGetDtoMapper getInstance() {
        return INSTANCE;
    }

    public ProfileGetDto map(Profile profile) {
        return map(profile, new ProfileGetDto());
    }

    public ProfileGetDto map(Profile profile, ProfileGetDto dto) {
        dto.setId(profile.getId());
        dto.setEmail(profile.getEmail());
        dto.setName(profile.getName());
        dto.setSurname(profile.getSurname());
        dto.setBirthDate(profile.getBirthDate());
        if (profile.getBirthDate() != null) {
            dto.setAge(Math.toIntExact(ChronoUnit.YEARS.between(profile.getBirthDate(), LocalDate.now())));
        }
        dto.setAbout(profile.getAbout());
        dto.setGender(profile.getGender());
        dto.setStatus(profile.getStatus());
        log.info("Convert Profile {} To ProfileGetDto {}", profile.toString(), dto.toString());
        return dto;
    }
}
