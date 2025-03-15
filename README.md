1. Estructura del Proyecto

/ParcialNetBeans
│── src/main/java/com/mycompany/parcial
│   ├── ReservaServlet.java
│   ├── ListaReservasServlet.java
│   ├── EliminarReservaServlet.java
│── web
│   ├── index.html
│   ├── reservas.jsp
│   ├── styles.css
│── README.md

El proyecto contiene archivos Java para los Servlets y archivos web para la interfaz de usuario.

2. Descripción de los Archivos y su Funcionamiento

 index.html - Formulario de Reserva

Contiene un formulario donde el usuario ingresa:

Nombre

Fecha de reserva

Espacio de trabajo (escritorio, sala de reuniones, oficina privada)

Duración de la reserva (en horas)

El formulario se envía al servlet ReservaServlet mediante POST.

Ejemplo de código:

<form action="ReservaServlet" method="post">
    <input type="text" name="nombre" placeholder="Nombre" required>
    <input type="date" name="fecha" required>
    <select name="espacio">
        <option value="Escritorio">Escritorio</option>
        <option value="Sala de reuniones">Sala de reuniones</option>
        <option value="Oficina privada">Oficina privada</option>
    </select>
    <input type="number" name="duracion" placeholder="Duración (horas)" required>
    <button type="submit">Reservar</button>
</form>

 ReservaServlet.java - Procesamiento de Reservas

Recibe los datos del formulario.

Valida que no estén vacíos.

Guarda la reserva en una lista en memoria.

Redirige a reservas.jsp para mostrar la lista actualizada.

Ejemplo de código:

@WebServlet("/ReservaServlet")
public class ReservaServlet extends HttpServlet {
    private static List<Reserva> reservas = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String espacio = request.getParameter("espacio");
        String duracion = request.getParameter("duracion");

        if (nombre.isEmpty() || fecha.isEmpty() || espacio.isEmpty() || duracion.isEmpty()) {
            response.getWriter().println("Error: Todos los campos son obligatorios");
            return;
        }

        reservas.add(new Reserva(nombre, fecha, espacio, duracion));
        response.sendRedirect("reservas.jsp");
    }

    public static List<Reserva> getReservas() {
        return reservas;
    }
}

reservas.jsp - Lista de Reservas

Muestra todas las reservas almacenadas en ReservaServlet.

Usa una tabla para organizar los datos.

Incluye un botón "Eliminar" que llama a EliminarReservaServlet.

Ejemplo de código:

<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.parcial.ReservaServlet" %>
<%@ page import="com.mycompany.parcial.ReservaServlet.Reserva" %>
<table>
    <tr>
        <th>Nombre</th>
        <th>Fecha</th>
        <th>Espacio</th>
        <th>Duración</th>
        <th>Acciones</th>
    </tr>
    <% List<Reserva> reservas = ReservaServlet.getReservas(); %>
    <% for (Reserva r : reservas) { %>
        <tr>
            <td><%= r.getNombre() %></td>
            <td><%= r.getFecha() %></td>
            <td><%= r.getEspacio() %></td>
            <td><%= r.getDuracion() %> horas</td>
            <td>
                <form action="EliminarReservaServlet" method="post">
                    <input type="hidden" name="nombre" value="<%= r.getNombre() %>">
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
    <% } %>
</table>

 EliminarReservaServlet.java - Eliminación de Reservas

Recibe el nombre de la reserva a eliminar.

Elimina la reserva de la lista.

Redirige nuevamente a reservas.jsp.

Ejemplo de código:

@WebServlet("/EliminarReservaServlet")
public class EliminarReservaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");

        ReservaServlet.getReservas().removeIf(reserva -> reserva.getNombre().equals(nombre));

        response.sendRedirect("reservas.jsp");
    }
}

3. Resumen de la Lógica del Sistema

El usuario ingresa sus datos en index.html y los envía a ReservaServlet.

ReservaServlet valida los datos y los almacena en una lista.

reservas.jsp muestra la lista de reservas recuperada de ReservaServlet.

Si el usuario presiona "Eliminar", EliminarReservaServlet elimina la reserva correspondiente.

Se redirige nuevamente a reservas.jsp con la lista actualizada.
