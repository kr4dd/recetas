package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "CITA")
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numCita;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private Integer duracion; //En minutos

    @Embedded
    private FechaYhora fechaYHora;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    public Cita() {

    }

    public Cita(EstadoCita estado, Integer duracion, FechaYhora fechaYHora,
                Medico medico, Paciente paciente) {
        this.estado = estado;
        this.duracion = duracion;
        this.fechaYHora = fechaYHora;
        this.medico = medico;
        this.paciente = paciente;
    }

    public Long getNumCita() {
        return numCita;
    }

    public void setNumCita(Long numCita) {
        this.numCita = numCita;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public FechaYhora getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(FechaYhora fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "numCita=" + numCita +
                ", estado=" + estado +
                ", duracion=" + duracion +
                ", fechaYHora=" + fechaYHora +
                ", medico=" + medico +
                ", paciente=" + paciente +
                '}';
    }
}
