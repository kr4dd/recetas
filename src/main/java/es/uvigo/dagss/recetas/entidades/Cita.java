package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@DiscriminatorValue(value = "CITA")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private Integer duracion; //Minutos

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Temporal(TemporalType.TIME)
    private Time hora;

}
