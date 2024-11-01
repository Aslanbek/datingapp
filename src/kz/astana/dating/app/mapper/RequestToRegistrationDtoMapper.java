package kz.astana.dating.app.mapper;

import jakarta.servlet.http.HttpServletRequest;
import kz.astana.dating.app.dto.RegistrationDto;

public class RequestToRegistrationDtoMapper implements Mapper<HttpServletRequest, RegistrationDto> {
    private static final RequestToRegistrationDtoMapper INSTANCE = new RequestToRegistrationDtoMapper();

    private RequestToRegistrationDtoMapper() {
    }

    public static RequestToRegistrationDtoMapper getInstance() {
        return INSTANCE;
    }

    public RegistrationDto map(HttpServletRequest req) {
        return map(req, new RegistrationDto());
    }

    public RegistrationDto map(HttpServletRequest req, RegistrationDto dto) {
        dto.setEmail(req.getParameter("email"));
        dto.setPassword(req.getParameter("password"));
        return dto;
    }
}