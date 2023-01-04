    package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.FechaYhora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaDAO extends JpaRepository<Cita, Long> {

    @Query("SELECT m FROM Medico AS m WHERE m.dni LIKE %:dni%")
    List<Cita> findByMedico(@Param("dni") String dni);

    @Query("SELECT p FROM Paciente AS p WHERE p.dni LIKE %:dni%")
    List<Cita> findByPaciente(@Param("dni") String dni);

    /*1
    La lista de citas se deberá limitar por fecha (indicando el día sobre el que se realizará la búsqueda) y podrá filtrarse por médico o
    paciente (seleccionándose ambos de una lista).
    */

    List<Cita> findByFechaYHora(FechaYhora fechaYHora);

    /*2
    lista ordenada por hora de inicio con las citas actualmente registradas, indicando su datos esenciales
    (paciente, médico, centro de salud, fecha y hora, estado).
    */

    /*3
    citas registradas para el día de hoy, indicando su datos esenciales (nombre de paciente, fecha y hora, duración, estado).
    */



}
