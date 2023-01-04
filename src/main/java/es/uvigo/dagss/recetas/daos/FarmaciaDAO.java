package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.EstadoFarmaceutico;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmaciaDAO extends JpaRepository<Farmacia, Long> {
    List<Farmacia> findByNombreEstablecimientoContaining(String nombre);
    List<Farmacia> findByEmailContaining(String email);
    List<Farmacia> findByTelefonoContaining(String telefono);
    List<Farmacia> findByEstadoContaining(EstadoFarmaceutico estado);

    List<Farmacia> findByDireccionDomicilio(String domicilio);
    List<Farmacia> findByDireccionCodigoPostal(String codigoPostal);
    List<Farmacia> findByDireccionLocalidad(String localidad);
    List<Farmacia> findByDireccionProvincia(String provincia);

    /*
    La lista de farmacias podrá filtrarse por nombre de establecimiento, localidad o provincia, permitiéndose en todos
    estos casos búsquedas aproximadas (tipo LIKE en SQL).
     */


}
