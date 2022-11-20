package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue(value = "RECETA")
public class Receta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne
    private Prescripcion prescripcion;

}
