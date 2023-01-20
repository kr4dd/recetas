package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import es.uvigo.dagss.recetas.entidades.Cita;

public interface CitaService {
    Cita crear(Cita cita);
    Cita modificar(Cita prescripcion);
    void eliminar(Cita prescripcion);
    Optional<Cita> buscarPorId(Long id);
    List<Cita> buscarTodos();
    List<Cita> buscarFechaMedico();
    List<Cita> buscarFechaPaciente();


    //List<Cita> BuscarPorFechaInicioFinalPrescripcion(Date fechaInicioPrescripcion, Date fechaFinPrescripcion);
}
