
package com.citasmedicas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/citas")
public class ApiCitasServlet extends HttpServlet {

    private static final Gson gson = new Gson();

    private static final List<Map<String, Object>> citas =
            new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

        response.getWriter().write(gson.toJson(citas));
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Map<String, Object> cita = gson.fromJson(
                    request.getReader(),
                    new TypeToken<Map<String, Object>>() {}.getType()
            );

            if (cita == null
                    || !tieneTexto(cita, "paciente")
                    || !tieneTexto(cita, "fecha")
                    || !tieneTexto(cita, "hora")
                    || !tieneTexto(cita, "motivo")) {

                response.setStatus(
                        HttpServletResponse.SC_BAD_REQUEST
                );

                response.getWriter().write(
                    "{\"error\":\"Todos los campos son obligatorios.\"}"
                );
                return;
            }

            synchronized (citas) {
                Map<String, Object> nuevaCita =
                        new HashMap<>(cita);

                nuevaCita.put("id", citas.size() + 1);
                citas.add(nuevaCita);
            }

            response.setStatus(
                    HttpServletResponse.SC_CREATED
            );

            response.getWriter().write(
                    gson.toJson(Map.of(
                        "mensaje", "Cita registrada correctamente.",
                        "cita", cita
                    ))
            );

        } catch (RuntimeException e) {
            response.setStatus(
                    HttpServletResponse.SC_BAD_REQUEST
            );

            response.getWriter().write(
                    "{\"error\":\"El JSON enviado no es válido.\"}"
            );
        }
    }

    private boolean tieneTexto(Map<String, Object> datos,
                               String campo) {

        Object valor = datos.get(campo);

        return valor instanceof String
                && !((String) valor).isBlank();
    }
}
