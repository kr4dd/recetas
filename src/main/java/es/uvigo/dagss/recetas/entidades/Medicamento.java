package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "MEDICAMENTO")
public class Medicamento {

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
