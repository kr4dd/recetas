package es.uvigo.dagss.recetas.controladores.dtos;

public class MensajeRespuesta {
    private String texto;

    public MensajeRespuesta() {
    }

    public MensajeRespuesta(String texto) {
        super();
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

}