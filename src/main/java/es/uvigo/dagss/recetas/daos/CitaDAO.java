package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CitaDAO extends JpaRepository<Cita, Long> {
    List<Cita> findByMedicoDni(String dni);
    List<Cita> findByPacienteDni(String dni);

    /*1
    La lista de citas se deberá limitar por fecha (indicando el día sobre el que se realizará la búsqueda) y podrá filtrarse por médico o
    paciente (seleccionándose ambos de una lista).
     */


    /*2
    lista ordenada por hora de inicio con las citas actualmente registradas, indicando su datos esenciales
    (paciente, médico, centro de salud, fecha y hora, estado).
     */

    /*3
    citas registradas para el día de hoy, indicando su datos esenciales (nombre de paciente, fecha y hora, duración, estado).
     */



}
