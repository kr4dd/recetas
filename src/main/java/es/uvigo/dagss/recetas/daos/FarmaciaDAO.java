package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.EstadoFarmaceutico;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmaciaDAO extends JpaRepository<Farmacia, String> {
    List<Farmacia> findByNombreEstablecimientoContaining(String nombre);
    List<Farmacia> findByEmailContaining(String email);
    List<Farmacia> findByTelefonoContaining(String telefono);
    List<Farmacia> findByEstadoContaining(EstadoFarmaceutico estado);

    List<Farmacia> findByDireccionDomicilio(String domicilio);
    List<Farmacia> findByDireccionCodigoPostal(String codigoPostal);
    List<Farmacia> findByDireccionLocalidad(String localidad);
    List<Farmacia> findByDireccionProvincia(String provincia);
}
