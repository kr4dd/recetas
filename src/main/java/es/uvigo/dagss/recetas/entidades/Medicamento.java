package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "MEDICAMENTO")
public class Medicamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE_COMERCIAL")
    private String nombreComercial;

    private String principioActivo;

    private String familia;

    private Integer numDosis;

    private String fabricante;

    @Enumerated(EnumType.STRING)
    private EstadoMedicamento estado;


}
