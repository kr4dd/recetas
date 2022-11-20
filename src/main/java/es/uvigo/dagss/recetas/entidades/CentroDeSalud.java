package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "CENTROSALUD")
public class CentroDeSalud implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE_CENTROSALUD")
    private String nombre;

    @Column(name = "EMAIL_CENTROSALUD")
    private String email;

    @Enumerated(EnumType.STRING)
    private EstadoCentroSalud estado;

    @Column(name = "TLFNO_CENTROSALUD")
    private String telefono;

    @Embedded
    private Direccion direccion;


}
