package kz.astana.dating.app.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.astana.dating.app.service.LikeService;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/like")
public class LikeController extends HttpServlet {

    private final LikeService likeService = LikeService.getInstance();

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
            writer.write("<h2><p>" + answer + "</p><br>UserAgent " + UserAgent + "<br>WebServlet " + getServletConfig().getServletName() + "</h2>");
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy servlet ");
    }
}
