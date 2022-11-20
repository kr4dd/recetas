package es.uvigo.dagss.recetas.entidades;

import javax.persistence.Embeddable;

@Embeddable
public class Direccion {
    private String domicilio;
    private String localidad;
    private String codigoPostal;
    private String provincia;

    public Direccion() {

    }

    public Direccion(String domicilio, String localidad, String codigoPostal, String provincia) {
        this.domicilio = domicilio;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Direccion{" + "domicilio=" + domicilio + ", localidad=" + localidad + ", codigoPostal=" + codigoPostal
                + ", provincia=" + provincia + '}';
    }
}