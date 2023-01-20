package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoDAO extends JpaRepository<Medico, Long> {
    // Filtrado de nombre del medico
    @Query("SELECT m FROM Medico AS m WHERE m.nombre LIKE %:patron%")
    List<Medico> findByPatronNombre(@Param("patron") String patron);

    List<Medico> findByApellidosContaining(String apellidos);

    // Buscar a que centro de salud pertecene el medico
    List<Medico> findByCentroDeSaludId(Long id);

    // Filtrar medico por localidad
    @Query("SELECT m FROM Medico AS m " +
            "JOIN FETCH " +
            "m.centroDeSalud c WHERE c.direccion.localidad LIKE %:patron%")
    List<Medico> findByPatronDireccionLocalidad(@Param("patron") String patron);

    // Filtrar medico por provincia
    @Query("SELECT m FROM Medico AS m " +
            "JOIN FETCH " +
            "m.centroDeSalud c WHERE c.direccion.provincia LIKE %:patron%")
    List<Medico> findByPatronDireccionProvincia(@Param("patron") String patron);

}
