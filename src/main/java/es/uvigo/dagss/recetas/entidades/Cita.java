package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue(value = "CITA")
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numCita;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private Integer duracion; //Minutos

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Temporal(TemporalType.TIME)
    private Date hora;

    @Id
    @ManyToOne
    private Medico medico;

    @Id
    @ManyToOne
    private Paciente paciente;

    public Cita() {

    }

    public Cita(EstadoCita estado, Integer duracion, Date fecha, Date hora, Medico medico, Paciente paciente) {
        this.estado = estado;
        this.duracion = duracion;
        this.fecha = fecha;
        this.hora = hora;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
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
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", medico=" + medico +
                ", paciente=" + paciente +
                '}';
    }
}
