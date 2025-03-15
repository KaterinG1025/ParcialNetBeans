package com.mycompany.parcial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "ReservaServlet", urlPatterns = {"/ReservaServlet"})
public class ReservaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Lista en memoria para almacenar las reservas
    private static List<Reserva> reservas = new ArrayList<>();

    public static List<Reserva> getReservas() {
        return reservas;
    }

    // Clase interna Reserva
    public static class Reserva {
        private String nombre;
        private String fecha;
        private String espacio;
        private int duracion;

        public Reserva(String nombre, String fecha, String espacio, int duracion) {
            this.nombre = nombre;
            this.fecha = fecha;
            this.espacio = espacio;
            this.duracion = duracion;
        }

        public String getNombre() { return nombre; }
        public String getFecha() { return fecha; }
        public String getEspacio() { return espacio; }
        public int getDuracion() { return duracion; }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String espacio = request.getParameter("espacio");
        String duracionStr = request.getParameter("duracion");

        if (nombre == null || fecha == null || espacio == null || duracionStr == null ||
            nombre.isEmpty() || fecha.isEmpty() || espacio.isEmpty() || duracionStr.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
            dispatcher.forward(request, response);
            return;
        }

        int duracion = Integer.parseInt(duracionStr);
        reservas.add(new Reserva(nombre, fecha, espacio, duracion));

        response.sendRedirect("reservas.jsp");
    }
}
