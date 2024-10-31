package kz.astana.dating.app.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.astana.dating.app.dto.ProfileGetDto;
import kz.astana.dating.app.model.Gender;
import kz.astana.dating.app.model.Profile;
import kz.astana.dating.app.service.ProfileService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
    private final ProfileService service = ProfileService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String forwardUri = "/notFound";
        if (id != null) {
            Optional<ProfileGetDto> optionalProfileGetDto = service.findById(Long.parseLong(id));

            if (optionalProfileGetDto.isPresent()) {
                req.setAttribute("profile", optionalProfileGetDto.get());
                forwardUri = "WEB-INF/jsp/profile.jsp";
            }
        } else {
            req.setAttribute("profiles", service.findAll());
            forwardUri = "WEB-INF/jsp/profiles.jsp";
        }
        req.getRequestDispatcher(forwardUri).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ProfileGetDto profileGetDto = getProfileGetDto(req);
        Long id = service.save(profileGetDto);
        resp.sendRedirect(String.format("/profile?id=%s", id));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProfileGetDto profile = getProfileGetDto(req);
        service.update(profile);
        resp.sendRedirect(String.format("/profile?id=%s", profile.getId()));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sId = req.getParameter("id");
        if (!sId.isBlank()) {
            service.delete(Long.parseLong(sId));
        }
        resp.sendRedirect("/registration");
    }

/*    public String save(String request) {
        String[] params = request.split(",");
        if (params.length < 4) return "bad request";
        Profile profile = new Profile();

        profile.setEmail(params[0]);
        profile.setName(params[1]);
        profile.setSurname(params[2]);
        profile.setAbout(params[3]);
        return service.save(profile).toString();
    }*/


/*    public String update(String request) {
        String[] strings = request.split(",");
        if (strings.length != 5) return "Bad request: need 5 params";
        long id;
        try {
            id = Long.parseLong(strings[0]);
        } catch (NumberFormatException e) {
            return "Bad request: cant parse string[" + strings[0] + "] to long";
        }
        Profile profile = new Profile();
        profile.setId(id);
        profile.setEmail(strings[1]);
        profile.setName(strings[2]);
        profile.setSurname(strings[3]);
        profile.setAbout(strings[4]);
        service.update(profile);
        return "update success";
    }*/

/*    public String delete(String request) {
        String[] strings = request.split(",");
        if (strings.length != 1) return "Bad request: need one param";
        long id;
        try {
            id = Long.parseLong(strings[0]);
        } catch (NumberFormatException e) {
            return "Bad request: cant parse string[" + strings[0] + "] to long";
        }
        boolean result = service.delete(id);
        if (!result) return "not found";
        return "delete success";
    }*/

/*    public String findById(String request) {
        String[] strings = request.split(",");
        if (strings.length != 1) return "Bad request: need one param";
        long id;
        try {
            id = Long.parseLong(strings[0]);
        } catch (NumberFormatException e) {
            return "Bad request: cant parse string[" + strings[0] + "] to long";
        }
        Optional<ProfileGetDto> maybeProfile = service.findById(id);
        if (maybeProfile.isEmpty()) return "not found";
        return maybeProfile.get().toString();
    }*/

/*    public String findAll() {
        return service.findAll().toString();
    }*/

/*    private Profile getProfile(HttpServletRequest req) {
        Profile profile = new Profile();
        String sId = req.getParameter("id");
        if (!sId.isBlank()) {
            profile.setId(Long.parseLong(sId));
        }
        profile.setEmail(req.getParameter("email"));
        profile.setName(req.getParameter("name"));
        profile.setSurname(req.getParameter("surname"));
        profile.setAbout(req.getParameter("about"));
        profile.setBirthDate(LocalDate.parse(req.getParameter("birthDate")));
        profile.setGender(Gender.valueOf(req.getParameter("gender")));
        return profile;
    }*/

    private ProfileGetDto getProfileGetDto(HttpServletRequest req) {
        ProfileGetDto dto = new ProfileGetDto();
        String sId = req.getParameter("id");
        if (!sId.isBlank()) {
            dto.setId(Long.parseLong(sId));
        }
        dto.setEmail(req.getParameter("email"));
        dto.setName(req.getParameter("name"));
        dto.setSurname(req.getParameter("surname"));
        dto.setAbout(req.getParameter("about"));
        dto.setBirthDate(LocalDate.parse(req.getParameter("birthDate")));
        dto.setGender(Gender.valueOf(req.getParameter("gender")));
        dto.setAge(Math.toIntExact(ChronoUnit.YEARS.between(LocalDate.parse(req.getParameter("birthDate")), LocalDate.now())));
        return dto;
    }
}
