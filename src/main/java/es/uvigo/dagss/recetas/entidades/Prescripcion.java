package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue(value = "PRESCRIPCION")
public class Prescripcion {
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



}
