package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import es.uvigo.dagss.recetas.entidades.Farmacia;

public interface FarmaciaService {
    Farmacia crear(Farmacia farmacia);
    Farmacia modificar(Farmacia farmacia);
    void eliminar(Farmacia farmacia);
    Optional<Farmacia> buscarPorId(Long id);
    List<Farmacia> buscarTodos();
    List<Farmacia> buscarPorNombreEstablecimiento(String nombreEstablecimiento);
    List<Farmacia> buscarPorLocalidad(String localidad);
    List<Farmacia> buscarPorProvincia(String provincia);
    List<Farmacia> buscarPorEstado(String estado);

/*     List<Farmacia> buscarPorDomicilio(String domicilio);
    List<Farmacia> buscarPorCodigoPostal(String codigoPostal);
    List<Farmacia> buscarPorEmail(String email);
    List<Farmacia> buscarPorTelefono(String telefono);
 */



}
