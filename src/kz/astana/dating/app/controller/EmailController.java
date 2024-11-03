package kz.astana.dating.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.astana.dating.app.controller.filter.ErrorFilter;
import kz.astana.dating.app.dto.ProfileGetDto;
import kz.astana.dating.app.dto.ProfileUpdateDto;
import kz.astana.dating.app.mapper.RequestToProfileUpdateDtoMapper;
import kz.astana.dating.app.model.exception.DuplicateEmailException;
import kz.astana.dating.app.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet("/email")
public class EmailController extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(EmailController.class);
    private final ProfileService service = ProfileService.getInstance();
    private final RequestToProfileUpdateDtoMapper requestToProfileUpdateDtoMapper = RequestToProfileUpdateDtoMapper.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sId = req.getParameter("id");
        String forwardUri = null;
        if (sId != null) {
            Optional<ProfileGetDto> optProfileDto = service.findById(Long.parseLong(sId));

            if (optProfileDto.isPresent()) {
                req.setAttribute("profile", optProfileDto.get());
                forwardUri = "WEB-INF/jsp/email.jsp";
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
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProfileUpdateDto dto = requestToProfileUpdateDtoMapper.map(req, new ProfileUpdateDto());
        try {
            service.update(dto);
            log.info("email {} was changed in profile id {}", dto.getEmail(), dto.getId());
            resp.sendRedirect(String.format("/profile?id=%s", dto.getId()));
        } catch (DuplicateEmailException e) {
            resp.sendError(SC_BAD_REQUEST);
        }
    }
}
