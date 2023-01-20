package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.EstadoFarmaceutico;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FarmaciaDAO extends JpaRepository<Farmacia, Long> {
    @Query("SELECT f FROM Farmacia AS f WHERE f.nombreEstablecimiento LIKE %:nombre%")
    List<Farmacia> findByNombreEstablecimientoContaining(@Param("nombre") String nombre);

    @Query("SELECT f FROM Farmacia AS f WHERE f.direccion.localidad LIKE %:localidad%")
    List<Farmacia> findByDireccionLocalidad(@Param("localidad") String localidad);

    @Query("SELECT f FROM Farmacia AS f WHERE f.direccion.provincia LIKE %:provincia%")
    List<Farmacia> findByDireccionProvincia(@Param("provincia") String provincia);

    List<Farmacia> findByEmailContaining(String email);
    List<Farmacia> findByTelefonoContaining(String telefono);
    List<Farmacia> findByEstadoContaining(String estado);

/*     @Query("SELECT f FROM Farmacia AS f WHERE f.direccion.domicilio LIKE %:domicilio%")
    List<Farmacia> findByDireccionDomicilio(@Param("domicilio") String domicilio);
    
    @Query("SELECT f FROM Farmacia AS f WHERE f.direccion.codigoPostal LIKE %:codigoPostal%")
    List<Farmacia> findByDireccionCodigoPostal(String codigoPostal);

    @Query("SELECT f FROM Farmacia AS f WHERE f.nombreFarmaceutico LIKE %:nombreFarmaceutico%")
    List<Farmacia> findByNombreFarmaceutico(@Param("nombreFarmaceutico") String nombreFarmaceutico);

    @Query("SELECT f FROM Farmacia AS f WHERE f.apellidosFarmaceutico LIKE %:apellidosFarmaceutico%")
    List<Farmacia> findByApellidosFarmaceutico(@Param("apellidosFarmaceutico") String apellidosFarmaceutico);
    
    @Query("SELECT f FROM Farmacia AS f WHERE f.apellidosFarmaceutico LIKE %:dni%")
    List<Farmacia> findByDNI(@Param("dni") String dni);
    
    @Query("SELECT f FROM Farmacia AS f WHERE f.numColegiado LIKE %:numColegiado%")
    List<Farmacia> findByNumColegiado(@Param("numColegiado") String numColegiado); */

}
