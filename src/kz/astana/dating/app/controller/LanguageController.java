package kz.astana.dating.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet("/lang")
@Slf4j
public class LanguageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lang = req.getParameter("lang");
        Cookie cookie = new Cookie("lang", "en");
        if ("ru".equals(lang)) {
            cookie.setValue("ru");
        } else if ("kz".equals(lang)) {
            cookie.setValue("kz");
        }
        log.info("language set: {}", lang);
        resp.addCookie(cookie);
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }
}
