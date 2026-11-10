package ups.poo.modelo;


import java.io.Serializable;

//Clase que representa cada partido del torneo, asociando dos equipos y su respectiva etapa
public class Partido implements Serializable {
    private static final long serialVersionUID = 1L;

    private Equipos local;
    private Equipos visitante;
    private String etapa;

 // Constructor para inicializar el partido con sus equipos y su etapa
    public Partido(Equipos local, Equipos visitante, String etapa) {
        this.local = local;
        this.visitante = visitante;
        this.etapa = etapa;
    }

    //Metodos Getter
    public Equipos getLocal() {
        return local;
    }

    public Equipos getVisitante() {
        return visitante;
    }

    public String getEtapa() {
        return etapa;
    }
}

