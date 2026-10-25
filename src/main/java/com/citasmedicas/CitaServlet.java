
package com.citasmedicas;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CitaServlet")
public class CitaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/index.html")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String paciente = request.getParameter("paciente");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String motivo = request.getParameter("motivo");

        if (paciente == null || paciente.isBlank()
                || fecha == null || fecha.isBlank()
                || hora == null || hora.isBlank()
                || motivo == null || motivo.isBlank()) {

            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Todos los campos son obligatorios."
            );
            return;
        }

        request.setAttribute("paciente", paciente);
        request.setAttribute("fecha", fecha);
        request.setAttribute("hora", hora);
        request.setAttribute("motivo", motivo);

        request.getRequestDispatcher("/resultado.jsp")
               .forward(request, response);
    }
}
