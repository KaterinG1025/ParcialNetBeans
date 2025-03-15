<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.parcial.ReservaServlet" %>
<%@ page import="com.mycompany.parcial.ReservaServlet.Reserva" %>
<html>
<head>
    <title>Lista de Reservas</title>
</head>
<body>
    <h2>Lista de Reservas</h2>
    <%
        List<Reserva> reservas = ReservaServlet.getReservas();
        if (reservas.isEmpty()) {
    %>
        <p>No hay reservas registradas.</p>
    <% } else { %>
        <table border="1">
            <tr>
                <th>Nombre</th>
                <th>Fecha</th>
                <th>Espacio</th>
                <th>Duración</th>
                <th>Acciones</th>
            </tr>
            <%
                for (Reserva r : reservas) {
            %>
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
    <% } %>

    <br>
    <a href="index.html">Hacer otra reserva</a>
</body>
</html>
