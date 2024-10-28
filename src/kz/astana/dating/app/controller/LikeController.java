package kz.astana.dating.app.controller;


import kz.astana.dating.app.service.LikeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/like")
public class LikeController extends HttpServlet {

    private String servletName;
    private final LikeService likeService = LikeService.getInstance();

    public void init(ServletConfig config) throws ServletException {
        this.servletName = config.getServletName();
        System.out.println("init sevlet " + servletName);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

 /*       String t = "dd";
        String[] strings = {"aa", "bb", "aa", "cc", "aa", "bb","cc","dd"};
        Map<String, Integer> map = new HashMap<>();
        for (String s : strings) {
            //map.merge(s, 1, (o, n) -> o + n);
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
        var result = 0;
        //result = map.computeIfPresent("bb", (k, v) -> k.length() + v);
        if (map.containsKey(t)) {
            result = map.get(t) + t.length();
        }
        System.out.println("result: = " + result);*/

        String id = req.getParameter("id");
        String answer = "10";
        if (id != null) {
            long l = Long.parseLong(id);
            long answerL = likeService.getLikesbyId(l);
            answer = answerL + "";
        }
        String UserAgent = req.getHeader("User-Agent");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h2><p>" + answer + "</p><br>UserAgent " + UserAgent + "<br>WebServlet " + servletName + "</h2>");
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy servlet " + servletName);
    }
}
