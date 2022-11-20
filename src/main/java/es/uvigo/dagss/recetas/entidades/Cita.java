package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Entity
@DiscriminatorValue(value = "CITA")
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCita;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private Integer duracion; //Minutos

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Temporal(TemporalType.TIME)
    private Time hora;

    @Id
    @ManyToOne
    private Medico medico;

    @Id
    @ManyToOne
    private Paciente paciente;

    @Id
    @ManyToOne
    private Agenda agenda;

}
