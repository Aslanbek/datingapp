package kz.astana.dating.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.astana.dating.app.dto.ProfileGetDto;
import kz.astana.dating.app.dto.RegistrationDto;
import kz.astana.dating.app.mapper.RequestToRegistrationDtoMapper;
import kz.astana.dating.app.model.Profile;
import kz.astana.dating.app.service.ProfileService;
import kz.astana.dating.app.validator.RegistrationValidator;
import kz.astana.dating.app.validator.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/registration")
@Slf4j
public class RegistrationController extends HttpServlet {

    private final ProfileService service = ProfileService.getInstance();
    private final RegistrationValidator validator = RegistrationValidator.getInstance();

    private final RequestToRegistrationDtoMapper requestToRegistrationDtoMapper = RequestToRegistrationDtoMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req, resp);
        log.info("Somebody open registration page from {}", req.getHeader("User-Agent"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationDto dto = requestToRegistrationDtoMapper.map(req);
        ValidationResult result = validator.validate(dto);
        if (!result.isValid()) {
            req.setAttribute("errors", result.getErrors());
            doGet(req, resp);
        } else {
            Long id = service.save(dto);
            log.trace("Profile {} created with email {}", id, dto.getEmail());
            resp.sendRedirect(String.format("/profile?id=%s", id));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        if (!sId.isBlank()) {
            service.delete(Long.parseLong(sId));
            log.info("profile {} is deleted", sId);
        }
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        resp.sendRedirect("/registration");
    }
}
