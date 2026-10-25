
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resultado del registro</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>
    <main class="contenedor">
        <h1>Cita registrada</h1>
        <p>Los datos fueron recibidos correctamente.</p>

        <section class="resultado">
            <p><strong>Paciente:</strong> ${paciente}</p>
            <p><strong>Fecha:</strong> ${fecha}</p>
            <p><strong>Hora:</strong> ${hora}</p>
            <p><strong>Motivo:</strong> ${motivo}</p>
        </section>

        <a href="CitaServlet">Registrar otra cita</a>
    </main>
</body>
</html>
