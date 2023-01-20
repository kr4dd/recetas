package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.entidades.Cita;
@Service
public interface CitaService {
    Cita crear(Cita cita);
    Cita modificar(Cita cita);
    void eliminar(Cita cita);
    Optional<Cita> buscarPorId(Long id);
    List<Cita> buscarTodos();
    List<Cita> buscarMedico(String dni);
    List<Cita> buscarPaciente(String dni);
    List<Cita> buscarFecha(String fecha);
    List<Cita> buscarHora(String hora);
    List<Cita> buscarDuracion(String duracion);
    List<Cita> buscarEstado(String estado);
    List<Cita> buscarFechaMedico(String date, String dni);
    List<Cita> buscarFechaPaciente(String date, String dni);


    //List<Cita> BuscarPorFechaInicioFinalPrescripcion(Date fechaInicioPrescripcion, Date fechaFinPrescripcion);
}
