package kz.astana.dating.app.mapper;

import kz.astana.dating.app.dto.RegistrationDto;
import kz.astana.dating.app.model.Profile;
import kz.astana.dating.app.model.Status;

public class RegistrationDtoToProfileMapper implements Mapper<RegistrationDto, Profile> {

    private static final RegistrationDtoToProfileMapper INSTANCE = new RegistrationDtoToProfileMapper();

    private RegistrationDtoToProfileMapper() {
    }

    public static RegistrationDtoToProfileMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public Profile map(RegistrationDto dto) {
        return map(dto, new Profile());
    }

    @Override
    public Profile map(RegistrationDto dto, Profile profile) {
        profile.setEmail(dto.getEmail());
        profile.setPassword(dto.getPassword());
        profile.setStatus(Status.INACTIVE);
        return profile;
    }
}

