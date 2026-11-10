package ups.poo.modelo;


//Excepcion personalizada para manejar errores o fallos al momento de realizar el sorteo
public class SorteoInvalidoException extends Exception {
    private static final long serialVersionUID = 1L;

    
    // Constructor que recibe un mensaje de error y lo envía a la clase padre(Exception)
    public SorteoInvalidoException(String mensaje) {
        super(mensaje);
    }
}


