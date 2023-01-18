package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.FechaYhora;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaDAO extends JpaRepository<Cita, Long> {

/*     List<Cita> findByMedico(@Param("dni") String dni);

    List<Cita> findByPaciente(@Param("dni") String dni);
 */
    /*1
    La lista de citas se deberá limitar por fecha (indicando el día sobre el que se realizará la búsqueda) y podrá filtrarse por médico o
    paciente (seleccionándose ambos de una lista).
    */
    @Query("SELECT c FROM Cita AS c " +
    "JOIN FETCH " +
    "c.medico m WHERE c.fechaYHora.fecha = fechaYHora AND m = medico") 
    List<Cita> findByFechaYHoraAndMedico(FechaYhora fechaYHora, Medico medico);

    @Query("SELECT c FROM Cita AS c " +
    "JOIN FETCH " +
    "c.paciente p WHERE c.fechaYHora.fecha = fechaYHora AND p = paciente") 
    List<Cita> findByFechaYHoraAndPaciente(FechaYhora fechaYHora, Paciente paciente);

    /*2
    lista ordenada por hora de inicio con las citas actualmente registradas, indicando su datos esenciales
    (paciente, médico, centro de salud, fecha y hora, estado).
    */
    //Query personalizada que devuelva valores por (paciente, médico, centro de salud, fecha y hora, estado). by hora

/*     List<Cita> findByFechaYHora(FechaYhora fechaYhora);
 */
    /*3
    citas registradas para el día de hoy, indicando su datos esenciales (nombre de paciente, fecha y hora, duración, estado).
    */

    //hacer consulta personalidad pero con la funcion de sql par ala fecha actual





}
