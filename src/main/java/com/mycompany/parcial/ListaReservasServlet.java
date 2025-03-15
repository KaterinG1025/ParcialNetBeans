package com.mycompany.parcial;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// IMPORTACIÓN CORRECTA DE RESERVA
import com.mycompany.parcial.ReservaServlet.Reserva;

@WebServlet(name = "ListaReservasServlet", urlPatterns = {"/ListaReservasServlet"})
public class ListaReservasServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            List<Reserva> reservas = ReservaServlet.getReservas(); 

            out.println("<html>");
            out.println("<head><title>Lista de Reservas</title>");
            out.println("<link rel='stylesheet' href='styles.css'>"); // Estilos externos
            out.println("</head><body>");
            out.println("<h2>Lista de Reservas</h2>");

            if (reservas == null || reservas.isEmpty()) {
                out.println("<p>No hay reservas registradas.</p>");
            } else {
                out.println("<table border='1'>"); // Se agrega borde a la tabla
                out.println("<tr><th>Nombre</th><th>Fecha</th><th>Espacio</th><th>Duración</th><th>Acciones</th></tr>");
                
                for (Reserva r : reservas) { // Se corrige el tipo de datos
                    out.println("<tr>");
                    out.println("<td>" + r.getNombre() + "</td>");
                    out.println("<td>" + r.getFecha() + "</td>");
                    out.println("<td>" + r.getEspacio() + "</td>");
                    out.println("<td>" + r.getDuracion() + "</td>");
                    out.println("<td>");
                    out.println("<form action='EliminarReservaServlet' method='post'>");
                    out.println("<input type='hidden' name='nombre' value='" + r.getNombre() + "'>");
                    out.println("<button type='submit'>Eliminar</button>");
                    out.println("</form>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }

            out.println("<br><a href='index.html'>Hacer otra reserva</a>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}