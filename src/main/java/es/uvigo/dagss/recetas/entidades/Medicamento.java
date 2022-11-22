package es.uvigo.dagss.recetas.entidades;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "MEDICAMENTO")
public class Medicamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE_COMERCIAL")
    private String nombreComercial;

    private String principioActivo;

    private String familia;

    private Integer numDosis;

    private String fabricante;

    @Enumerated(EnumType.STRING)
    private EstadoMedicamento estado;

    @ManyToOne
    private Prescripcion prescripcion;

    public Medicamento(Long id, String nombreComercial, String principioActivo, String familia, Integer numDosis, String fabricante, EstadoMedicamento estado, Prescripcion prescripcion) {
        this.id = id;
        this.nombreComercial = nombreComercial;
        this.principioActivo = principioActivo;
        this.familia = familia;
        this.numDosis = numDosis;
        this.fabricante = fabricante;
        this.estado = estado;
        this.prescripcion = prescripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getPrincipioActivo() {
        return principioActivo;
    }

    public void setPrincipioActivo(String principioActivo) {
        this.principioActivo = principioActivo;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public Integer getNumDosis() {
        return numDosis;
    }

    public void setNumDosis(Integer numDosis) {
        this.numDosis = numDosis;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public EstadoMedicamento getEstado() {
        return estado;
    }

    public void setEstado(EstadoMedicamento estado) {
        this.estado = estado;
    }

    public Prescripcion getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(Prescripcion prescripcion) {
        this.prescripcion = prescripcion;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "id=" + id +
                ", nombreComercial='" + nombreComercial + '\'' +
                ", principioActivo='" + principioActivo + '\'' +
                ", familia='" + familia + '\'' +
                ", numDosis=" + numDosis +
                ", fabricante='" + fabricante + '\'' +
                ", estado=" + estado +
                ", prescripcion=" + prescripcion +
                '}';
    }
}
