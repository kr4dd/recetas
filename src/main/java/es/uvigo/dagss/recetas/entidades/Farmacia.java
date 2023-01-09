package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario implements Serializable {

    @Column(name = "DNI_FARMACIA")
    private String dni;

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
        //super(TipoUsuario.FARMACIA);
    }

    public Farmacia(String dni, String numColegiado, String email, String telefono, String nombreFarmaceutico, String apellidosFarmaceutico, String nombreEstablecimiento, EstadoFarmaceutico estado, Direccion direccion) {
        this.dni = dni;
        this.numColegiado = numColegiado;
        this.email = email;
        this.telefono = telefono;
        this.nombreFarmaceutico = nombreFarmaceutico;
        this.apellidosFarmaceutico = apellidosFarmaceutico;
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.estado = estado;
        this.direccion = direccion;
    }

    public Farmacia(String login, String password, String dni, String numColegiado, String email, String telefono, String nombreFarmaceutico, String apellidosFarmaceutico, String nombreEstablecimiento, EstadoFarmaceutico estado, Direccion direccion) {
        super(login, password);
        this.dni = dni;
        this.numColegiado = numColegiado;
        this.email = email;
        this.telefono = telefono;
        this.nombreFarmaceutico = nombreFarmaceutico;
        this.apellidosFarmaceutico = apellidosFarmaceutico;
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.estado = estado;
        this.direccion = direccion;
    }

//    public Farmacia(TipoUsuario tipo, String dni, String numColegiado, String email, String telefono, String nombreFarmaceutico, String apellidosFarmaceutico, String nombreEstablecimiento, EstadoFarmaceutico estado, Direccion direccion) {
//        super(tipo);
//        this.dni = dni;
//        this.numColegiado = numColegiado;
//        this.email = email;
//        this.telefono = telefono;
//        this.nombreFarmaceutico = nombreFarmaceutico;
//        this.apellidosFarmaceutico = apellidosFarmaceutico;
//        this.nombreEstablecimiento = nombreEstablecimiento;
//        this.estado = estado;
//        this.direccion = direccion;
//    }
//
//    public Farmacia(TipoUsuario tipo, String login, String password, String dni, String numColegiado, String email,
//                    String telefono, String nombreFarmaceutico, String apellidosFarmaceutico,
//                    String nombreEstablecimiento, EstadoFarmaceutico estado, Direccion direccion) {
//        super(tipo, login, password);
//        this.dni = dni;
//        this.numColegiado = numColegiado;
//        this.email = email;
//        this.telefono = telefono;
//        this.nombreFarmaceutico = nombreFarmaceutico;
//        this.apellidosFarmaceutico = apellidosFarmaceutico;
//        this.nombreEstablecimiento = nombreEstablecimiento;
//        this.estado = estado;
//        this.direccion = direccion;
//    }

    public String getDNI() {
        return dni;
    }

    public void setDNI(String DNI) {
        this.dni = dni;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreFarmaceutico() {
        return nombreFarmaceutico;
    }

    public void setNombreFarmaceutico(String nombreFarmaceutico) {
        this.nombreFarmaceutico = nombreFarmaceutico;
    }

    public String getApellidosFarmaceutico() {
        return apellidosFarmaceutico;
    }

    public void setApellidosFarmaceutico(String apellidosFarmaceutico) {
        this.apellidosFarmaceutico = apellidosFarmaceutico;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public EstadoFarmaceutico getEstado() {
        return estado;
    }

    public void setEstado(EstadoFarmaceutico estado) {
        this.estado = estado;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Farmacia{" +
                "DNI='" + dni + '\'' +
                ", numColegiado='" + numColegiado + '\'' +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                ", nombreFarmaceutico='" + nombreFarmaceutico + '\'' +
                ", apellidosFarmaceutico='" + apellidosFarmaceutico + '\'' +
                ", nombreEstablecimiento='" + nombreEstablecimiento + '\'' +
                ", estado=" + estado +
                ", direccion=" + direccion +
                '}';
    }
}
