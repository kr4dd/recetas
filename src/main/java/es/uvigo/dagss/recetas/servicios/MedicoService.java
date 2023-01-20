package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoService {
    Medico crear(Medico medico);
    Medico modificar(Medico medico);
    void eliminar(Medico medico);
    Optional<Medico> buscarPorId(Long id);
    List<Medico> buscarTodos();

    List<Medico> buscarPorNombre(String nombre);
    List<Medico> buscarPorApellidos(String apellidos);
    List<Medico> buscarCentroDeSaludPorId(Long id);
    List<Medico> buscarPorLocalidad(String localidad);
    List<Medico> buscarPorProvincia(String provincia);




}
