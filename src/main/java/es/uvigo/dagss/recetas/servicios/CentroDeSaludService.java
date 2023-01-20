package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import es.uvigo.dagss.recetas.entidades.EstadoCentroSalud;

import java.util.List;
import java.util.Optional;

public interface CentroDeSaludService {
    CentroDeSalud crear(CentroDeSalud centroDeSalud);
    CentroDeSalud modificar(CentroDeSalud centroDeSalud);
    void eliminar(CentroDeSalud centroDeSalud);
    Optional<CentroDeSalud> buscarPorId(Long id);
    List<CentroDeSalud> buscarTodos();

    List<CentroDeSalud> buscarPorNombre(String nombre);
    List<CentroDeSalud> buscarPorEmail(String email);
    List<CentroDeSalud> buscarPorTelefono(String telefono);
    List<CentroDeSalud> buscarPorEstado(String estado);

    List<CentroDeSalud> buscarPorDomicilio(String domicilio);
    List<CentroDeSalud> buscarPorCodigoPostal(String codigoPostal);
    List<CentroDeSalud> buscarPorLocalidad(String localidad);
    List<CentroDeSalud> buscarPorProvincia(String provincia);

}
