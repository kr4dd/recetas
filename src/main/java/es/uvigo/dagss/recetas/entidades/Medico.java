package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario implements Serializable {

    @Id
    @Column(name = "DNI_MEDICO")
    private String DNI;

    @Column(name = "NOMBRE_MEDICO")
    private String nombre;

    @Column(name = "APELLIDOS_MEDICO")
    private String apellidos;

    @Column(name = "NUMCOLE_MEDICO")
    private String numColegiado;

    @Column(name = "TLFNO_MEDICO")
    private String telefono;

    @Column(name = "EMAIL_MEDICO")
    private String email;

    @Enumerated(EnumType.STRING)
    private EstadoMedico estado;

    @ManyToOne
    private CentroDeSalud centroDeSalud;

    public Medico() {
        super(TipoUsuario.MEDICO);
    }

}
