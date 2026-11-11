package ups.poo.controlador;

import ups.poo.modelo.*;
import ups.poo.vista.VistaTorneo;

import java.io.IOException;

public class ControladorTorneo {
    private Torneo torneo;
    private VistaTorneo vista;
    
    //// Se define la ruta y nombre del archivo binario para la serialización
    private static final String ARCHIVO_TORNEO = "torneo.ser";

   
    // Se definio un constructor que inicializa los objetos del Modelo y de la vista
    public ControladorTorneo() {
        this.torneo = new Torneo();
        this.vista = new VistaTorneo();
    }

    //// Este es el método principal que gestiona el ciclo del menú y el flujo  del programa 
    public void iniciar() {
        int opcion = 0;
        do {
            opcion = vista.mostrarMenu();
            
            
            // Estructura condicional para direccionar la opción que escoga el usuario
            switch (opcion) {
                case 1:
                    registrarEquipos();
                    break;
                case 2:
                    vista.mostrarEquipos(torneo.getListaEquipos());
                    break;
                case 3:
                    realizarSorteo();
                    break;
                case 4:
                    vista.mostrarPartidos(torneo.getListaPartidos());
                    break;
                case 5:
                    guardarDatosTorneo();
                    break;
                case 6:
                    cargarDatosTorneo();
                    break;
                case 7:
                    vista.mostrarMensaje("Saliendo del sistema de torneo. ¡Hasta luego!");
                    break;
                default:
                    vista.mostrarError("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 7);
    }

 
 // Método que controla el registro de equipos y valida las reglas del torneo
    private void registrarEquipos() {
        try {
        	
        	// Se limpian las colecciones en caso de iniciar un registro nuevo
            torneo.getListaEquipos().clear();
            torneo.getListaPartidos().clear();

vista.mostrarMensaje("¿Cuántos equipos desea registrar? (Deben ser 2, 4, 8 o 16): ");
            
// Se valida para que la entrada que se ingresara sea un numero entero valido
            java.util.Scanner lector = new java.util.Scanner(System.in);
            while (!lector.hasNextInt()) {
                vista.mostrarError("Por favor ingrese un número válido.");
                lector.next();
                vista.mostrarMensaje("¿Cuántos equipos desea registrar?: ");
            }
            int total = lector.nextInt(); 
            
         // Se implemento esta estructura para evitar que se pida el nombre de equipo si el numero no es un entero valido
            if (total != 2 && total != 4 && total != 8 && total != 16) {
                throw new NumeroEquiposInvalidosException("El número de equipos (" + total + ") no es válido para un torneo de eliminación directa. Deben ser 2, 4, 8 o 16.");
            }
            
            
            // Se definio un ciclo para solicitar y agregar cada equipo
            for (int i = 1; i <= total; i++) {
                String nombre = vista.solicitarNombreEquipo(i);
                torneo.agregarEquipo(new Equipos(nombre));
            }
              
      
         // Se valida el número de equipos mediante el manejo de excepciones
            torneo.validarNumeroEquipos();
            vista.mostrarMensaje("Equipos registrados correctamente");

        } catch (NumeroEquiposInvalidosException e) {
            vista.mostrarError(e.getMessage());
        }
    }

 // Método que verifica los equipos, limpia los partidos anteriores y ejecuta el sorteo automático
    private void realizarSorteo() {
        try {
            torneo.validarNumeroEquipos();
            torneo.getListaPartidos().clear();
            
            
         // Se calcula en qué fase arranca el torneo según cuántos equipos se hayan inscrito
            String etapaInicial = (torneo.getListaEquipos().size() == 16) ? "Octavos de Final" : 
                                  (torneo.getListaEquipos().size() == 8) ? "Cuartos de Final" : "Semifinal";
            
         // Se llama al método que arma los emparejamientos
            torneo.sortearEtapaRecursiva(torneo.getListaEquipos(), etapaInicial);
            vista.mostrarMensaje("Sorteo del torneo realizado con éxito");

        } catch (NumeroEquiposInvalidosException | SorteoInvalidoException e) {
            vista.mostrarError(e.getMessage());
        }
    }

 // Método para serializar y guardar el estado actual del objeto Torneo en disco
    private void guardarDatosTorneo() {
        try {
            torneo.guardarTorneo(ARCHIVO_TORNEO);
            vista.mostrarMensaje("Torneo guardado correctamente en el archivo binario: " + ARCHIVO_TORNEO);
        } catch (IOException e) {
            vista.mostrarError("Error al guardar el archivo: " + e.getMessage());
        }
    }

 // Método para volver a cargar el torneo guardado en el archivo
    private void cargarDatosTorneo() {
        try {
        	
        	// Invocamos el método estático del modelo para leer y reconstruir el objeto guardado
            torneo = Torneo.cargarTorneo(ARCHIVO_TORNEO);
            vista.mostrarMensaje("Torneo cargado exitosamente desde el archivo disco");
        } catch (IOException | ClassNotFoundException e) {
        	
        	// Capturamos errores si el archivo no existe o si la estructura de la clase cambió
            vista.mostrarError("No se pudo cargar el archivo guardado: " + e.getMessage());
        }
    }
}