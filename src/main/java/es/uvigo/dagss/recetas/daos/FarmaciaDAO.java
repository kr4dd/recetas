package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.EstadoFarmaceutico;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FarmaciaDAO extends JpaRepository<Farmacia, Long> {
    List<Farmacia> findByNombreEstablecimientoContaining(String nombre);
    List<Farmacia> findByEmailContaining(String email);
    List<Farmacia> findByTelefonoContaining(String telefono);
    List<Farmacia> findByEstadoContaining(EstadoFarmaceutico estado);

    List<Farmacia> findByDireccionDomicilio(String domicilio);
    List<Farmacia> findByDireccionCodigoPostal(String codigoPostal);


    
    @Query("SELECT f FROM farmacia AS f WHERE f.nombre LIKE %:nombre%")
    List<Farmacia> findByPatronNombre(@Param("nombre") String nombre);


}
