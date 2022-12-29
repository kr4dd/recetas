package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Prescripcion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PrescripcionService {
    Prescripcion crear(Prescripcion prescripcion);
    Prescripcion modificar(Prescripcion prescripcion);
    void eliminar(Prescripcion prescripcion);
    Optional<Prescripcion> buscarPorId(Long id);
    List<Prescripcion> buscarTodos();

    List<Prescripcion> BuscarPorFechaInicioFinalPrescripcion(Date fechaInicioPrescripcion, Date fechaFinPrescripcion);
}
