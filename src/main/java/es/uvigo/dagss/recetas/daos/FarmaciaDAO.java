package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.EstadoFarmaceutico;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FarmaciaDAO extends JpaRepository<Farmacia, String> {
    /*
    La lista de farmacias podrá filtrarse por nombre de establecimiento, localidad o provincia, permitiéndose en todos
    estos casos búsquedas aproximadas (tipo LIKE en SQL).
    */
    @Query("SELECT c FROM farmacia AS p WHERE p.nombre LIKE %:nombre%")
    List<Farmacia> findByNombreEstablecimiento(@Param("nombre") String nombre);

    @Query("SELECT c FROM farmacia AS p WHERE p.direccion.localidad LIKE %:localidad%")
    List<Farmacia> findByDireccionLocalidad(@Param("localidad") String localidad);

    @Query("SELECT c FROM farmacia AS p WHERE p.direccion.provincia LIKE %:provincia%")
    List<Farmacia> findByDireccionProvincia(@Param("provincia") String provincia);
    

    //No lo veo en las historias, pero se puede dejar???
    List<Farmacia> findByEmailContaining(String email);
    List<Farmacia> findByTelefonoContaining(String telefono);
    List<Farmacia> findByEstadoContaining(EstadoFarmaceutico estado);

    List<Farmacia> findByDireccionDomicilio(String domicilio);
    List<Farmacia> findByDireccionCodigoPostal(String codigoPostal);


    
    @Query("SELECT f FROM farmacia AS f WHERE f.nombre LIKE %:nombre%")
    List<Farmacia> findByPatronNombre(@Param("nombre") String nombre);


}
