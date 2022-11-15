package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario implements Serializable {

    // Anadir atributos propios
	
    public Farmacia() {
        super(TipoUsuario.FARMACIA);
    }
    
}
