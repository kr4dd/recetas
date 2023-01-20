package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;

@Entity
@DiscriminatorValue(value = "PACIENTE")
public class Paciente extends Usuario implements Serializable {
    
    @Column(name = "DNI_PACIENTE")
    private String dni;

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

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("numCita asc")
    private List<Cita> citas = new ArrayList<>();

    public Paciente() {
        super(TipoUsuario.PACIENTE);        
    }

    public Paciente(String dni, String nombre, String apellidos, String telefono, String numTarjetaSanitaria, String NSS, String email, Direccion direccion, Date fechaNacimiento, EstadoPaciente estado, CentroDeSalud centroDeSalud, Medico medico, List<Cita> citas) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.numTarjetaSanitaria = numTarjetaSanitaria;
        this.NSS = NSS;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.centroDeSalud = centroDeSalud;
        this.medico = medico;
        this.citas = citas;
    }

    public Paciente(TipoUsuario tipo, String dni, String nombre, String apellidos, String telefono, String numTarjetaSanitaria, String NSS, String email, Direccion direccion, Date fechaNacimiento, EstadoPaciente estado, CentroDeSalud centroDeSalud, Medico medico, List<Cita> citas) {
        super(tipo);
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.numTarjetaSanitaria = numTarjetaSanitaria;
        this.NSS = NSS;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.centroDeSalud = centroDeSalud;
        this.medico = medico;
        this.citas = citas;
    }

    public Paciente(TipoUsuario tipo, String login, String password, String dni, String nombre, String apellidos,
                    String telefono, String numTarjetaSanitaria, String NSS, String email, Direccion direccion,
                    Date fechaNacimiento, EstadoPaciente estado, CentroDeSalud centroDeSalud, Medico medico,
                    List<Cita> citas) {
        super(tipo, login, password);
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.numTarjetaSanitaria = numTarjetaSanitaria;
        this.NSS = NSS;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.centroDeSalud = centroDeSalud;
        this.medico = medico;
        this.citas = citas;
    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String dni) {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumTarjetaSanitaria() {
        return numTarjetaSanitaria;
    }

    public void setNumTarjetaSanitaria(String numTarjetaSanitaria) {
        this.numTarjetaSanitaria = numTarjetaSanitaria;
    }

    public String getNSS() {
        return NSS;
    }

    public void setNSS(String NSS) {
        this.NSS = NSS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public EstadoPaciente getEstado() {
        return estado;
    }

    public void setEstado(EstadoPaciente estado) {
        this.estado = estado;
    }

    public CentroDeSalud getCentroDeSalud() {
        return centroDeSalud;
    }

    public void setCentroDeSalud(CentroDeSalud centroDeSalud) {
        this.centroDeSalud = centroDeSalud;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "DNI='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", numTarjetaSanitaria='" + numTarjetaSanitaria + '\'' +
                ", NSS='" + NSS + '\'' +
                ", email='" + email + '\'' +
                ", direccion=" + direccion +
                ", fechaNacimiento=" + fechaNacimiento +
                ", estado=" + estado +
                ", centroDeSalud=" + centroDeSalud +
                ", medico=" + medico +
                ", citas=" + citas +
                '}';
    }
}
