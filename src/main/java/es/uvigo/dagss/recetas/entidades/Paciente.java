package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "PACIENTE")
public class Paciente extends Usuario implements Serializable {

	// Anadir atributos propios
   

    public Paciente() {
        super(TipoUsuario.PACIENTE);        
    }

}
