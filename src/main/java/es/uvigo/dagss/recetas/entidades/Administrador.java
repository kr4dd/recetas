package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario implements Serializable {

    // Anadir atributos propios
	
    public Administrador() {
        super(TipoUsuario.ADMINISTRADOR); 
    }



}
