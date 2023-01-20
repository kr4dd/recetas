package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import es.uvigo.dagss.recetas.entidades.Paciente;

public interface PacienteService {
    Paciente crear(Paciente paciente);
    Paciente modificar(Paciente paciente);
    void eliminar(Paciente paciente);
    //Optional<Paciente> buscarPorDNI(String dni);
    Optional<Paciente> buscarPorId(Long id);

    List<Paciente> buscarTodos();

    List<Paciente> buscarPorNombre(String nombre);
    List<Paciente> buscarPorNumTarjetaSanitaria(String numTarjetaSanitaria);
    List<Paciente> buscarPorApellidos(String apellidos);
    List<Paciente> buscarCentroDeSaludPorId(Long id);
    List<Paciente> buscarPorLocalidad(String localidad);
    List<Paciente> buscarPorProvincia(String provincia);

}
