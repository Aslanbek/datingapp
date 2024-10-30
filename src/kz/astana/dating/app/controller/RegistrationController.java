package kz.astana.dating.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.astana.dating.app.model.Profile;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("profile", new Profile());
        req.getRequestDispatcher("WEB-INF/jsp/profile.jsp").forward(req, resp);
    }
}
