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

 ReservaServlet.java - Procesamiento de Reservas

Recibe los datos del formulario.

Valida que no estén vacíos.

Guarda la reserva en una lista en memoria.

Redirige a reservas.jsp para mostrar la lista actualizada.

reservas.jsp - Lista de Reservas

Muestra todas las reservas almacenadas en ReservaServlet.

Usa una tabla para organizar los datos.

Incluye un botón "Eliminar" que llama a EliminarReservaServlet.

 EliminarReservaServlet.java - Eliminación de Reservas

Recibe el nombre de la reserva a eliminar.

Elimina la reserva de la lista.

Redirige nuevamente a reservas.jsp.

3. Resumen de la Lógica del Sistema

El usuario ingresa sus datos en index.html y los envía a ReservaServlet.

ReservaServlet valida los datos y los almacena en una lista.

reservas.jsp muestra la lista de reservas recuperada de ReservaServlet.

Si el usuario presiona "Eliminar", EliminarReservaServlet elimina la reserva correspondiente.

Se redirige nuevamente a reservas.jsp con la lista actualizada.
