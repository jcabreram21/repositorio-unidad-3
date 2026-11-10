package ups.poo.modelo;


	import java.io.Serializable;
	
	
	// Clase que representa a los equipos del torneo e implementa Serializable para guardar datos en el disco
	public class Equipos implements Serializable {
	    private static final long serialVersionUID = 1L;
	    private String nombre;

	    
	 // Constructor para inicializar el equipo con su respectivo nombre
	    public Equipos(String nombre) {
	        this.nombre = nombre;
	    }

	    //Metodos getter y setter
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }
	    
	 // Sobrescribimos el método toString para que al imprimir el equipo devuelva su nombre directamente

	    @Override
	    public String toString() {
	        return nombre;
	    }
	}


