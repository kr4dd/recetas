package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoDAO extends JpaRepository<Medico, String> {
    // Filtrado de nombre del medico
    //@Query("SELECT m FROM MEDICO m WHERE m.NOMBRE_MEDICO LIKE %:nombre%")
    //List<Medico> findByNombreContaining(@Param("nombre") String nombre);

    List<Medico> findByApellidosContaining(String apellidos);

    // Buscar a que centro de salud pertecene el medico
    List<Medico> findByCentroDeSaludId(Long id);

    // Filtrar medico por localidad
    //@Query("SELECT m FROM MEDICO, CENTROSALUD "m JOIN m.CENTRO_DE_SALUD=c.NOMBRE_CENTROSALUD WHERE c.DIRECCION_CENTROSALUD LIKE %:localidad%")
    //List<Medico> findByDireccionLocalidad(@Param("localidad") String localidad);

    // Filtrar medico por provincia
    //@Query("SELECT m FROM Medico m WHERE m.NOMBRE_MEDICO LIKE %:provincia%")
    //List<Medico> findByDireccionProvincia(@Param("provincia") String provincia);

}
