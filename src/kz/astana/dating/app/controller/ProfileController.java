package kz.astana.dating.app.controller;

import kz.astana.dating.app.model.Profile;
import kz.astana.dating.app.service.ProfileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
    private String servletName;

    private final ProfileService service = ProfileService.getInstance();

    public void init(ServletConfig config) throws ServletException {
        this.servletName = config.getServletName();
        System.out.println("init sevlet " + servletName);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String forwardUri = "/notFound";
        if (id != null) {
            Optional<Profile> profile = service.findById(Long.parseLong(id));
            if (profile.isPresent()) {
                req.setAttribute("profile", profile.get());
                forwardUri = "WEB-INF/jsp/profile.jsp";
            }
        }
        req.getRequestDispatcher(forwardUri).forward(req, resp);
    }

    public String save(String request) {
        String[] params = request.split(",");
        if (params.length < 4) return "bad request";
        Profile profile = new Profile();

        profile.setEmail(params[0]);
        profile.setName(params[1]);
        profile.setSurname(params[2]);
        profile.setAbout(params[3]);
        return service.save(profile).toString();
    }


    public String update(String request) {
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
    }

    public String delete(String request) {
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
    }

    public String findById(String request) {
        String[] strings = request.split(",");
        if (strings.length != 1) return "Bad request: need one param";
        long id;
        try {
            id = Long.parseLong(strings[0]);
        } catch (NumberFormatException e) {
            return "Bad request: cant parse string[" + strings[0] + "] to long";
        }
        Optional<Profile> maybeProfile = service.findById(id);
        if (maybeProfile.isEmpty()) return "not found";
        return maybeProfile.get().toString();
    }

    public String findAll() {
        return service.findAll().toString();
    }
}
