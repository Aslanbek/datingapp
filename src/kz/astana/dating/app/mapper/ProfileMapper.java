package kz.astana.dating.app.mapper;

import kz.astana.dating.app.dto.ProfileSaveDto;
import kz.astana.dating.app.model.Profile;

public class ProfileMapper implements Mapper<ProfileSaveDto, Profile> {
    private static final ProfileMapper INSTANCE = new ProfileMapper();

    public static ProfileMapper getInstance() {
        return INSTANCE;
    }

    public Profile map(ProfileSaveDto dto, Profile profile) {
        Profile result = profile;
        if (result == null) {
            result = new Profile();
        }
        if (dto.getId() == null) {
            result.setEmail(dto.getEmail());
            result.setPassword(dto.getPassword());
        } else {
            result.setId(dto.getId());
            if (dto.getEmail() != null) {
                result.setEmail(dto.getEmail());
            }
            if (dto.getPassword() != null) {
                result.setPassword(dto.getPassword());
            }
            if (dto.getName() != null) {
                result.setName(dto.getName());
            }
            if (dto.getSurname() != null) {
                result.setSurname(dto.getSurname());
            }
            if (dto.getBirthDate() != null) {
                result.setBirthDate(dto.getBirthDate());
            }
            if (dto.getAbout() != null) {
                result.setAbout(dto.getAbout());
            }
            if (dto.getGender() != null) {
                result.setGender(dto.getGender());
            }
            if (dto.getStatus() != null) {
                result.setStatus(dto.getStatus());
            }
        }
        return result;
    }
}