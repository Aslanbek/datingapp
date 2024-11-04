package kz.astana.dating.app.mapper;

import kz.astana.dating.app.dto.ProfileUpdateDto;
import kz.astana.dating.app.model.Profile;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProfileUpdateDtoToProfileMapper implements Mapper<ProfileUpdateDto, Profile> {
    private static final ProfileUpdateDtoToProfileMapper INSTANCE = new ProfileUpdateDtoToProfileMapper();

    private ProfileUpdateDtoToProfileMapper() {
    }

    public static ProfileUpdateDtoToProfileMapper getInstance() {
        return INSTANCE;
    }

    public Profile map(ProfileUpdateDto dto) {
        return map(dto, new Profile());
    }

    public Profile map(ProfileUpdateDto dto, Profile profile) {

        profile.setId(dto.getId());
        if (dto.getEmail() != null) {
            profile.setEmail(dto.getEmail());
        }
        if (dto.getName() != null) {
            profile.setName(dto.getName());
        }
        if (dto.getSurname() != null) {
            profile.setSurname(dto.getSurname());
        }
        if (dto.getBirthDate() != null) {
            profile.setBirthDate(dto.getBirthDate());
        }
        if (dto.getAbout() != null) {
            profile.setAbout(dto.getAbout());
        }
        if (dto.getGender() != null) {
            profile.setGender(dto.getGender());
        }
        if (dto.getStatus() != null) {
            profile.setStatus(dto.getStatus());
        }

        log.info(" ProfileUpdateDto  {} To Profile {}", dto.toString(), profile.toString());
        return profile;
    }
}
