package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "PACIENTE")
public class Paciente extends Usuario implements Serializable {

    @Column(name = "DNI_PACIENTE")
    private String DNI;

    @Column(name = "NOMBRE_PACIENTE")
    private String nombre;

    @Column(name = "APELLIDOS_PACIENTE")
    private String apellidos;

    @Column(name = "TLFNO_PACIENTE")
    private String telefono;

    private String numTarjetaSanitaria;

    private String NSS;

    @Column(name = "EMAIL_PACIENTE")
    private String email;

    @Embedded
    private Direccion direccion;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Enumerated(EnumType.STRING)
    private EstadoPaciente estado;

    @ManyToOne
    private CentroDeSalud centroDeSalud;

    @ManyToOne
    private Medico medico;

    public Direccion getDireccion() {
        return direccion;
    }

    public Paciente() {
        super(TipoUsuario.PACIENTE);        
    }

}
