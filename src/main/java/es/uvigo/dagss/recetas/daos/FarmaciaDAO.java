package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface FarmaciaDAO extends JpaRepository<Farmacia, String> {
    List<Farmacia> findByNombreEstablecimientoContaining(String nombre);
    List<Farmacia> findByIdFarmaceutico(long id);
    List<Farmacia> findByEmailContaining(String email);
    List<Farmacia> findByTelefonoContaining(String telefono);
    List<Farmacia> findByEstadoContaining(EstadoCentroSalud estado);

    List<Farmacia> findByDireccion(String domicilio);
    List<Farmacia> findByDireccionCodigoPostal(String codigoPostal);
    List<Farmacia> findByDireccionLocalidad(String localidad);
    List<Farmacia> findByDireccionProvincia(String provincia);
}
