package es.uvigo.dagss.recetas.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario implements Serializable {

    @Column(name = "DNI_MEDICO")
    private String dni;

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

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("numCita asc")
    @JsonIgnore
    private List<Cita> citas = new ArrayList<>();

    public Medico() {
        //super(TipoUsuario.MEDICO);
    }

    public Medico(String dni, String nombre, String apellidos, String numColegiado, String telefono, String email, EstadoMedico estado, CentroDeSalud centroDeSalud, List<Cita> citas) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numColegiado = numColegiado;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.centroDeSalud = centroDeSalud;
        this.citas = citas;
    }

    public Medico(String login, String password, String dni, String nombre, String apellidos, String numColegiado, String telefono, String email, EstadoMedico estado, CentroDeSalud centroDeSalud, List<Cita> citas) {
        super(login, password);
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numColegiado = numColegiado;
        this.telefono = telefono;
        this.email = email;
        this.estado = estado;
        this.centroDeSalud = centroDeSalud;
        this.citas = citas;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EstadoMedico getEstado() {
        return estado;
    }

    public void setEstado(EstadoMedico estado) {
        this.estado = estado;
    }

    public CentroDeSalud getCentroDeSalud() {
        return centroDeSalud;
    }

    public void setCentroDeSalud(CentroDeSalud centroDeSalud) {
        this.centroDeSalud = centroDeSalud;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", numColegiado='" + numColegiado + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", estado=" + estado +
                ", centroDeSalud=" + centroDeSalud +
                ", citas=" + citas +
                '}';
    }
}
