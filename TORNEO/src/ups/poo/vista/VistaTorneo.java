package ups.poo.vista;


import ups.poo.modelo.Equipos;
import ups.poo.modelo.Partido;

import java.util.List;
import java.util.Scanner;

public class VistaTorneo {
    // Se declara el lector para capturar lo que el usuario ingresa
    private Scanner scanner;

    public VistaTorneo() {
        this.scanner = new Scanner(System.in);
    }
         
    // Se muestra el menú con las opciones disponibles
    public int mostrarMenu() {
        System.out.println("\n==============================");
        System.out.println("   GESTIÓN DE TORNEO DE FÚTBOL ");
        System.out.println("================================");
        System.out.println("1. Registrar Equipos (Debe ser 2, 4, 8 o 16)");
        System.out.println("2. Ver Lista de Equipos Inscritos");
        System.out.println("3. Realizar Sorteo del Torneo (Eliminatorias)");
        System.out.println("4. Ver Resultados de los Partidos");
        System.out.println("5. Guardar Torneo en Disco ");
        System.out.println("6. Cargar Torneo desde Disco");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
        
        // Validación segura sin errores de búfer
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("¡Error! Ingrese un número válido del menú.");
                System.out.print("Seleccione una opción: ");
            }
        }
    }

    // Método para pedir el número total de equipos de forma limpia y segura
    public int solicitarNumeroEquipos() {
        System.out.print("Ingrese el número de equipos (2, 4, 8 o 16): ");
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("¡Error! Ingrese un número válido.");
                System.out.print("Ingrese el número de equipos (2, 4, 8 o 16): ");
            }
        }
    }

    // Se crea un método para pedir al usuario que ingrese el nombre del equipo según el número de orden
    public String solicitarNombreEquipo(int numero) {
        System.out.print("Ingrese el nombre del equipo #" + numero + ": ");
        return scanner.nextLine();
    }
        
    // Se crean métodos para mostrar mensajes y errores
    public void mostrarMensaje(String mensaje) {
        System.out.println("\n[INFO] " + mensaje);
    }

    public void mostrarError(String mensaje) {
        System.err.println("\n[ERROR] " + mensaje);
    }

    // Este método se creó para recorrer la lista y mostrar los equipos registrados
    public void mostrarEquipos(List<Equipos> equipos) {
        System.out.println("\n--- EQUIPOS INSCRITOS (" + equipos.size() + ") ---");
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados todavía.");
            return;
        }
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println((i + 1) + ". " + equipos.get(i).getNombre());
        }
    }

    // Este método recorre la lista de partidos y muestra los enfrentamientos por etapas
    public void mostrarPartidos(List<Partido> partidos) {
        System.out.println("\n---  RESULTADOS DEL TORNEO ---");
        if (partidos.isEmpty()) {
            System.out.println("Aún no se ha realizado el sorteo del torneo.");
            return;
        }
        for (Partido p : partidos) {
            System.out.println("Etapa: " + p.getEtapa() + " | " + p.getLocal().getNombre() + " vs " + p.getVisitante().getNombre());
        }
    }
}