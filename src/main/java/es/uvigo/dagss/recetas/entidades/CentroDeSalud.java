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

    public CentroDeSalud(Long id, String nombre, String email, EstadoCentroSalud estado, String telefono, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadoCentroSalud getEstado() {
        return estado;
    }

    public void setEstado(EstadoCentroSalud estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "CentroDeSalud{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", estado=" + estado +
                ", telefono='" + telefono + '\'' +
                ", direccion=" + direccion +
                '}';
    }
}
