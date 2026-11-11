package ups.poo.modelo;

//Excepción personalizada para controlar cuando el número de equipos ingresados no es válido
public class NumeroEquiposInvalidosException extends Exception {
    private static final long serialVersionUID = 1L;

    
 // Constructor que recibe un mensaje personalizado y lo envía a la clase padre (Exception)
    public NumeroEquiposInvalidosException(String mensaje) {
        super(mensaje);
    }
}