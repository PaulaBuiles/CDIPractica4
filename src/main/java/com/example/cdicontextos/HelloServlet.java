package com.example.cdicontextos;

import java.io.*;
import java.sql.SQLException;

import com.example.cdicontextos.service.impl.ProductServiceImpl;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    @Inject
    @Named("Service")
    private ProductServiceImpl service;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(service);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        try {
            service.list().forEach(element->out.println(element.getProductName()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}