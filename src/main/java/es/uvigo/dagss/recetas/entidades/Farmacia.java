package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario implements Serializable {

    @Column(name = "DNI_FARMACIA")
    private String DNI;

    @Column(name = "NUMCOLE_FARMACEUTICO")
    private String numColegiado;

    private String email;

    @Column(name = "TLFNO_FARMACEUTICO")
    private String telefono;

    @Column(name = "NOMBRE_FARMACEUTICO")
    private String nombreFarmaceutico;

    @Column(name = "APELLIDOS_FARMACEUTICO")
    private String apellidosFarmaceutico;

    @Column(name = "NOMBRE_ESTABLECIMIENTO")
    private String nombreEstablecimiento;

    @Enumerated(EnumType.STRING)
    private EstadoFarmaceutico estado;

    @Embedded
    private Direccion direccion;

    public Farmacia() {
        super(TipoUsuario.FARMACIA);
    }

}
