package ups.poo.modelo;


	import java.io.*;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.List;

	/* Clase principal del Modelo (Torneo) que gestiona la lista de equipos
	  los partidos, el algoritmo recursivo del sorteo y la persistencia en el disco.*/

	public class Torneo implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    private List<Equipos> listaEquipos;
	    private List<Partido> listaPartidos;
	    
	    // Constructor que inicializa las listas de equipos y partidos
	     
	    public Torneo() {
	        this.listaEquipos = new ArrayList<>();
	        this.listaPartidos = new ArrayList<>();
	    }

	    //Método para agregar un nuevo equipo participante a la lista general del torneo.
	     
	    public void agregarEquipo(Equipos equipo) {
	        listaEquipos.add(equipo);
	    }
	    
	    
        //Metodos getter
	    public List<Equipos> getListaEquipos() {
	        return listaEquipos;
	    }

	    public List<Partido> getListaPartidos() {
	        return listaPartidos;
	    }

	    // Validación del número de equipos.(16, 8, 4, 2)
	    public void validarNumeroEquipos() throws NumeroEquiposInvalidosException {
	        int n = listaEquipos.size();
	        if (n != 16 && n != 8 && n != 4 && n != 2) {
	            throw new NumeroEquiposInvalidosException("El número de equipos (" + n + ") no es válido para un torneo de eliminación directa. Deben ser 2, 4, 8 o 16.");
	        }
	    }

	    // Metodo recursivo para sortear la fase

public void sortearEtapaRecursiva(List<Equipos> equiposFase, String etapa) throws SorteoInvalidoException {
	        if (equiposFase == null || equiposFase.size() % 2 != 0) {
	            throw new SorteoInvalidoException("No se puede realizar el sorteo: la cantidad de equipos debe ser par.");
	        }

	        // Caso base de la recursividad: si quedan 2 equipos, se juega la final
	        if (equiposFase.size() == 2) {
	            listaPartidos.add(new Partido(equiposFase.get(0), equiposFase.get(1), etapa));
	            return;
	        }

	        // Mezclamos de manera aleatoria los equipos para el sorteo
	        List<Equipos> mezcla = new ArrayList<>(equiposFase);
	        Collections.shuffle(mezcla);

	        List<Equipos> siguienteFase = new ArrayList<>();

	        // Emparejamos de 2 en 2
	        for (int i = 0; i < mezcla.size(); i += 2) {
	            Equipos e1 = mezcla.get(i);
	            Equipos e2 = mezcla.get(i + 1);
	            
	            listaPartidos.add(new Partido(e1, e2, etapa));
	            
	            // Simulamos que el ganador avanza a la siguiente ronda (temporalmente elegimos al primero de la pareja para la recursividad)
	            siguienteFase.add(e1); 
	        }

	        // Llamada recursiva para la siguiente etapa con la mitad de equipos
	        String siguienteEtapa = obtenerSiguienteEtapa(etapa);
	        if (siguienteEtapa != null) {
	            sortearEtapaRecursiva(siguienteFase, siguienteEtapa);
	        }
	    }

        // Método auxiliar privado para determinar el nombre de la siguiente fase del torneo.

	    private String obtenerSiguienteEtapa(String etapaActual) {
	        if (etapaActual.equalsIgnoreCase("Octavos de Final")) return "Cuartos de Final";
	        if (etapaActual.equalsIgnoreCase("Cuartos de Final")) return "Semifinal";
	        if (etapaActual.equalsIgnoreCase("Semifinal")) return "Final";
	        return null;
	    }

	    // Método de serializacion, se guarda el estado completo del objeto Torneo en un archivo binario en el disco.
	     
	    public void guardarTorneo(String nombreArchivo) throws IOException {
	        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
	            oos.writeObject(this);
	        }
	    }

	    // Para la deserializacion se carga y reconstruye el objeto torneo desde el archivo binario q se guardo en el disco
	    public static Torneo cargarTorneo(String nombreArchivo) throws IOException, ClassNotFoundException {
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
	            return (Torneo) ois.readObject();
	        }
	    }
	}


