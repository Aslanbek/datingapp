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
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet("/registration")
@Slf4j
public class RegistrationController extends HttpServlet {

    private final ProfileService service = ProfileService.getInstance();

    private final RequestToRegistrationDtoMapper requestToRegistrationDtoMapper = RequestToRegistrationDtoMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationDto dto = requestToRegistrationDtoMapper.map(req);
        log.trace("Profile created with email {}", dto.getEmail());
        Long id = service.save(dto);
        resp.sendRedirect(String.format("/profile?id=%s", id));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        if (!sId.isBlank()) {
            service.delete(Long.parseLong(sId));
        }
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        resp.sendRedirect("/registration");
    }
}
