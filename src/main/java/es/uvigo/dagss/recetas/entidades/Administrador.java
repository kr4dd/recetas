package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.io.Serializable;

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

    public Administrador(String email, EstadoAdministrador estado, String nombre) {
        this.email = email;
        this.estado = estado;
        this.nombre = nombre;
    }

    public Administrador(TipoUsuario tipo, String email, EstadoAdministrador estado, String nombre) {
        super(tipo);
        this.email = email;
        this.estado = estado;
        this.nombre = nombre;
    }

    public Administrador(TipoUsuario tipo, String login, String password, String email, EstadoAdministrador estado, String nombre) {
        super(tipo, login, password);
        this.email = email;
        this.estado = estado;
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadoAdministrador getEstado() {
        return estado;
    }

    public void setEstado(EstadoAdministrador estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "email='" + email + '\'' +
                ", estado=" + estado +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
