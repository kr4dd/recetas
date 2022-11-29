package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import es.uvigo.dagss.recetas.entidades.EstadoCentroSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroDeSaludDAO extends JpaRepository<CentroDeSalud, Long> {
    List<CentroDeSalud> findByNombreContaining(String nombre);
    List<CentroDeSalud> findByEmailContaining(String email);
    List<CentroDeSalud> findByTelefonoContaining(String telefono);
    List<CentroDeSalud> findByEstadoContaining(EstadoCentroSalud estado);

    List<CentroDeSalud> findByDireccionDomicilio(String domicilio);
    List<CentroDeSalud> findByDireccionCodigoPostal(String codigoPostal);
    List<CentroDeSalud> findByDireccionLocalidad(String localidad);
    List<CentroDeSalud> findByDireccionProvincia(String provincia);

}
