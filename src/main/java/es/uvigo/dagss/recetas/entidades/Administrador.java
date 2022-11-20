package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario implements Serializable {

    @Column(name = "EMAIL_ADMINISTRADOR")
    private String email;

    @Enumerated(EnumType.STRING)
    private EstadoAdministrador estado;

    @Column(name = "NOMBRE_ADMINISTRADOR")
    private String nombre;

    public Administrador() {
        super(TipoUsuario.ADMINISTRADOR);
    }

}
