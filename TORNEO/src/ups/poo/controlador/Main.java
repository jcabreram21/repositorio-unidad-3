
 
package ups.poo.controlador;

public class Main {
	
	// Método principal que inicia toda la aplicación del torneo
    public static void main(String[] args) {
    	
    	// Se instancio el controlador para poner en marcha el programa
        ControladorTorneo controlador = new ControladorTorneo();
        
        // Luego se llamo al método iniciar para desplegar el menú en consola
        controlador.iniciar();
    }
}


