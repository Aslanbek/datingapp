package kz.astana.dating.app.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.astana.dating.app.dto.ProfileGetDto;
import kz.astana.dating.app.dto.ProfileUpdateDto;
import kz.astana.dating.app.mapper.RequestToProfileUpdateDtoMapper;
import kz.astana.dating.app.service.ProfileService;
import kz.astana.dating.app.validator.ProfileUpdateValidator;
import kz.astana.dating.app.validator.RegistrationValidator;
import kz.astana.dating.app.validator.ValidationResult;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet("/profile")
@Slf4j
public class ProfileController extends HttpServlet {
    private final ProfileService service = ProfileService.getInstance();
    private final ProfileUpdateValidator profileFieldsValidator = ProfileUpdateValidator.getInstance();

    private final RequestToProfileUpdateDtoMapper requestToProfileUpdateDtoMapper = RequestToProfileUpdateDtoMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        String forwardUri = null;
        if (sId != null) {
            Optional<ProfileGetDto> optProfileDto = service.findById(Long.parseLong(sId));

            if (optProfileDto.isPresent()) {
                req.setAttribute("profile", optProfileDto.get());
                forwardUri = "WEB-INF/jsp/profile.jsp";
            }
        } else {
            req.setAttribute("profiles", service.findAll());
            forwardUri = "WEB-INF/jsp/profiles.jsp";
        }
        if (forwardUri == null) {
            resp.sendError(SC_NOT_FOUND);
        } else {
            req.getRequestDispatcher(forwardUri).forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ProfileUpdateDto dto = requestToProfileUpdateDtoMapper.map(req);
        ValidationResult result = profileFieldsValidator.validate(dto);
        if (!result.isValid()) {
            req.setAttribute("errors", result.getErrors());
            doGet(req, resp);
        } else {
            service.update(dto);
            log.info("profile {} has been updated", dto.getId());
            String referer = req.getHeader("referer");
            resp.sendRedirect(referer);
        }

    }
}
