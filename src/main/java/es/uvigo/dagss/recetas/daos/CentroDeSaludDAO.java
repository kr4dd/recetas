package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import es.uvigo.dagss.recetas.entidades.EstadoCentroSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CentroDeSaludDAO extends JpaRepository<CentroDeSalud, Long> {
    List<CentroDeSalud> findByEmailContaining(String email);
    List<CentroDeSalud> findByTelefonoContaining(String telefono);
    List<CentroDeSalud> findByEstado(EstadoCentroSalud estado);

    List<CentroDeSalud> findByDireccionDomicilio(String domicilio);
    List<CentroDeSalud> findByDireccionCodigoPostal(String codigoPostal);

    @Query("SELECT c FROM CentroDeSalud AS c WHERE c.nombre LIKE %:nombre%")
    List<CentroDeSalud> findByPatronNombre(@Param("nombre") String nombre);

    @Query("SELECT c FROM CentroDeSalud AS c WHERE c.direccion.localidad LIKE %:localidad%")
    List<CentroDeSalud> findByDireccionLocalidad(@Param("localidad") String localidad);

    @Query("SELECT c FROM CentroDeSalud AS c WHERE c.direccion.provincia LIKE %:provincia%")
    List<CentroDeSalud> findByDireccionProvincia(@Param("provincia") String provincia);

}
