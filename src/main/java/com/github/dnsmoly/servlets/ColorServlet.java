package com.github.dnsmoly.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/color")
public class ColorServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var colorAttr = req.getAttribute("color");
        var color = colorAttr == null ? req.getParameter("color") : colorAttr;
        var text = req.getParameter("text");
        resp.setContentType("text/html;charset=UTF-8");
        try (var out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ColorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 style=\"color:" + color + "\">" + text + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
