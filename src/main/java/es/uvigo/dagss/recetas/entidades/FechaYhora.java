package es.uvigo.dagss.recetas.entidades;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
public class FechaYhora implements Serializable {
    private String fecha;
    private String hora;

    public FechaYhora() {
        this.fecha = LocalDate.now().toString();
        this.hora = LocalTime.now().toString();
    }

    public FechaYhora(String fecha, String hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "FechaYhora{" +
                "fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
