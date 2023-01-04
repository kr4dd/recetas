package es.uvigo.dagss.recetas.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue(value = "PRESCRIPCION")
public class Prescripcion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaInicioPrescripcion;

    @Temporal(TemporalType.DATE)
    private Date fechaFinPrescripcion;

    private Double dosisDiaria;

    private String indicaciones;

    @Enumerated(EnumType.STRING)
    private EstadoPrescripcion estado;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    @JsonIgnore
    private Paciente paciente;

    @OneToMany(mappedBy = "prescripcion", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("numReceta asc")
    @JsonIgnore
    private List<Receta> recetas = new ArrayList<>();

    public Prescripcion() {

    }

    public Prescripcion(Date fechaInicioPrescripcion, Date fechaFinPrescripcion, Double dosisDiaria, String indicaciones, EstadoPrescripcion estado, Medico medico, Paciente paciente, List<Receta> recetas) {
        this.fechaInicioPrescripcion = fechaInicioPrescripcion;
        this.fechaFinPrescripcion = fechaFinPrescripcion;
        this.dosisDiaria = dosisDiaria;
        this.indicaciones = indicaciones;
        this.estado = estado;
        this.medico = medico;
        this.paciente = paciente;
        this.recetas = recetas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicioPrescripcion() {
        return fechaInicioPrescripcion;
    }

    public void setFechaInicioPrescripcion(Date fechaInicioPrescripcion) {
        this.fechaInicioPrescripcion = fechaInicioPrescripcion;
    }

    public Date getFechaFinPrescripcion() {
        return fechaFinPrescripcion;
    }

    public void setFechaFinPrescripcion(Date fechaFinPrescripcion) {
        this.fechaFinPrescripcion = fechaFinPrescripcion;
    }

    public Double getDosisDiaria() {
        return dosisDiaria;
    }

    public void setDosisDiaria(Double dosisDiaria) {
        this.dosisDiaria = dosisDiaria;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public EstadoPrescripcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrescripcion estado) {
        this.estado = estado;
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

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }

    @Override
    public String toString() {
        return "Prescripcion{" +
                "id=" + id +
                ", fechaInicioPrescripcion=" + fechaInicioPrescripcion +
                ", fechaFinPrescripcion=" + fechaFinPrescripcion +
                ", dosisDiaria=" + dosisDiaria +
                ", indicaciones='" + indicaciones + '\'' +
                ", estado=" + estado +
                ", medico=" + medico +
                ", paciente=" + paciente +
                ", recetas=" + recetas +
                '}';
    }

}
