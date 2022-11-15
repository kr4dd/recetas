package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario implements Serializable {

    // Anadir atributos propios
    
    public Medico() {
        super(TipoUsuario.MEDICO);
    }


}
