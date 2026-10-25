# Documentación de la API de gestión de citas médicas

## 1. Información general

**Nombre del proyecto:** Sistema de gestión de citas médicas  
**Tecnología:** Java, Maven, Jakarta Servlet 6.0 y Apache Tomcat 10.1  
**Formato de intercambio:** JSON  
**Versión:** 1.0

## 2. Objetivo

La API permite registrar y consultar citas médicas mediante solicitudes HTTP. Los datos se envían y reciben en formato JSON.

## 3. Dirección base

`http://localhost:8080/CitasMedicas/api/citas`

Esta dirección corresponde al entorno local de pruebas.

## 4. Servicio para consultar citas

**Método HTTP:** GET  
**Endpoint:** `/api/citas`

**Descripción:** devuelve la lista de citas registradas.

**Respuesta exitosa:** HTTP 200 OK.

Ejemplo de respuesta:

```json
[
  {
    "paciente": "Ana Perez",
    "fecha": "2026-11-02",
    "hora": "09:00",
    "motivo": "Consulta general",
    "id": 1
  }
]
```

Si no existen citas registradas, devuelve una lista vacía: `[]`.

## 5. Servicio para registrar citas

**Método HTTP:** POST  
**Endpoint:** `/api/citas`

**Descripción:** recibe los datos de una cita en formato JSON y la registra temporalmente en memoria.

**Datos requeridos:**

- `paciente`: nombre del paciente.
- `fecha`: fecha de la cita.
- `hora`: hora de la cita.
- `motivo`: motivo de la consulta.

Ejemplo de solicitud:

```json
{
  "paciente": "Ana Perez",
  "fecha": "2026-11-02",
  "hora": "09:00",
  "motivo": "Consulta general"
}
```

**Respuesta exitosa:** HTTP 201 Created.

Ejemplo:

```json
{
  "mensaje": "Cita registrada correctamente.",
  "cita": {
    "paciente": "Ana Perez",
    "fecha": "2026-11-02",
    "hora": "09:00",
    "motivo": "Consulta general"
  }
}
```

## 6. Validaciones y códigos de respuesta

- **200 OK:** consulta realizada correctamente.
- **201 Created:** cita registrada correctamente.
- **400 Bad Request:** faltan campos obligatorios o el JSON enviado no es válido.
- **404 Not Found:** el recurso solicitado no se encuentra disponible.
- **500 Internal Server Error:** error interno del servidor.

## 7. Herramientas utilizadas

- Java para la lógica del servicio.
- Maven para administrar dependencias y compilar el proyecto.
- Jakarta Servlet para gestionar solicitudes HTTP.
- Gson para convertir datos JSON.
- Apache Tomcat para desplegar y ejecutar la aplicación.
- Git y GitHub para el control de versiones y almacenamiento del código fuente.

## 8. Limitaciones

En esta versión, las citas se almacenan temporalmente en memoria. Por lo tanto, se pierden cuando la aplicación se reinicia. No se ha implementado persistencia en una base de datos.

## 9. Pruebas realizadas

Se comprobó el funcionamiento del servicio GET, que devuelve las citas registradas con estado HTTP 200, y del servicio POST, que registra una cita de prueba con estado HTTP 201. También se verificó que la cita registrada apareciera posteriormente en la consulta GET.

## 10. Conclusión

Se implementó y probó una API básica para la gestión de citas médicas con operaciones de consulta y registro. La documentación describe sus endpoints, los datos requeridos y las respuestas HTTP obtenidas durante las pruebas.