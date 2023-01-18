package es.uvigo.dagss.recetas.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue(value = "RECETA")
public class Receta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numReceta;

    @Enumerated(EnumType.STRING)
    private EstadoReceta estado;

    private Integer numUdsMedicamento;

    @Temporal(TemporalType.DATE)
    private Date fechaValidezInicial;

    @Temporal(TemporalType.DATE)
    private Date fechaValidezFinal;

    @Embedded
    private Direccion direccion;

    @ManyToOne
    private Farmacia farmacia;

    @ManyToOne
    @JsonIgnore
    private Prescripcion prescripcion;

    public Receta() {

    }

    public Receta(EstadoReceta estado, Integer numUdsMedicamento, Date fechaValidezInicial, Date fechaValidezFinal, Direccion direccion, Farmacia farmacia, Prescripcion prescripcion) {
        this.estado = estado;
        this.numUdsMedicamento = numUdsMedicamento;
        this.fechaValidezInicial = fechaValidezInicial;
        this.fechaValidezFinal = fechaValidezFinal;
        this.direccion = direccion;
        this.farmacia = farmacia;
        this.prescripcion = prescripcion;
    }

    public Long getNumReceta() {
        return numReceta;
    }

    public void setNumReceta(Long numReceta) {
        this.numReceta = numReceta;
    }

    public EstadoReceta getEstado() {
        return estado;
    }

    public void setEstado(EstadoReceta estado) {
        this.estado = estado;
    }

    public Integer getNumUdsMedicamento() {
        return numUdsMedicamento;
    }

    public void setNumUdsMedicamento(Integer numUdsMedicamento) {
        this.numUdsMedicamento = numUdsMedicamento;
    }

    public Date getFechaValidezInicial() {
        return fechaValidezInicial;
    }

    public void setFechaValidezInicial(Date fechaValidezInicial) {
        this.fechaValidezInicial = fechaValidezInicial;
    }

    public Date getFechaValidezFinal() {
        return fechaValidezFinal;
    }

    public void setFechaValidezFinal(Date fechaValidezFinal) {
        this.fechaValidezFinal = fechaValidezFinal;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    public Prescripcion getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(Prescripcion prescripcion) {
        this.prescripcion = prescripcion;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "numReceta=" + numReceta +
                ", estado=" + estado +
                ", numUdsMedicamento=" + numUdsMedicamento +
                ", fechaValidezInicial=" + fechaValidezInicial +
                ", fechaValidezFinal=" + fechaValidezFinal +
                ", direccion=" + direccion +
                ", farmacia=" + farmacia +
                ", prescripcion=" + prescripcion +
                '}';
    }
}
