package kz.astana.dating.app.mapper;

import jakarta.servlet.http.HttpServletRequest;
import kz.astana.dating.app.dto.ProfileUpdateDto;
import kz.astana.dating.app.model.Gender;
import kz.astana.dating.app.model.Status;

import java.time.LocalDate;

import static kz.astana.dating.app.utils.StringUtils.isBlank;

public class RequestToProfileUpdateDtoMapper implements Mapper<HttpServletRequest, ProfileUpdateDto> {
    private static final RequestToProfileUpdateDtoMapper INSTANCE = new RequestToProfileUpdateDtoMapper();

    private RequestToProfileUpdateDtoMapper() {
    }

    public static RequestToProfileUpdateDtoMapper getInstance() {
        return INSTANCE;
    }

    public ProfileUpdateDto map(HttpServletRequest req) {
        return map(req, new ProfileUpdateDto());
    }

    public ProfileUpdateDto map(HttpServletRequest req, ProfileUpdateDto dto){
        String id = req.getParameter("id");
        if (!isBlank(id)) {
            dto.setId(Long.parseLong(id));
        }
        String email = req.getParameter("email");
        if (!isBlank(email)) {
            dto.setEmail(email);
        }
        dto.setName(req.getParameter("name"));
        dto.setSurname(req.getParameter("surname"));
        String birthDate = req.getParameter("birthDate");
        if (!isBlank(birthDate)) {
            dto.setBirthDate(LocalDate.parse(birthDate));
        }
        dto.setAbout(req.getParameter("about"));
        String gender = req.getParameter("gender");
        if (!isBlank(gender)) {
            dto.setGender(Gender.valueOf(gender));
        }
        String status = req.getParameter("status");
        if (!isBlank(status)) {
            dto.setStatus(Status.valueOf(status));
        }
        return dto;
    }
}