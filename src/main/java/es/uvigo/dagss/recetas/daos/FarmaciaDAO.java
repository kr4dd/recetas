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
    List<Farmacia> findByEmailContaining(String email);
    List<Farmacia> findByTelefonoContaining(String telefono);
    List<Farmacia> findByEstadoContaining(EstadoFarmaceutico estado);

    List<Farmacia> findByDireccionDomicilio(String domicilio);
    List<Farmacia> findByDireccionProvincia(String provincia);
    List<Farmacia> findByDireccionCodigoPostal(String codigoPostal);

}
