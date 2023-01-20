package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Cita;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaDAO extends JpaRepository<Cita, Long> {

    /*
     * 1
     * La lista de citas se deberá limitar por fecha (indicando el día sobre el que
     * se realizará la búsqueda) y podrá filtrarse por médico o
     * paciente (seleccionándose ambos de una lista).
     */
    @Query("SELECT c FROM Cita AS c " +
            "JOIN FETCH " +
            "c.medico m WHERE c.fechaYHora.fecha LIKE %:fecha% AND m.dni LIKE %:dni%")
    List<Cita> findByFechaAndMedicoDni(@Param("fecha") String fecha, @Param("dni") String dni);
    @Query("SELECT c FROM Cita AS c " +
            "JOIN FETCH " +
            "c.paciente p WHERE c.fechaYHora.fecha LIKE %:fecha% AND p.dni LIKE %:dni%")
    List<Cita> findByFechaAndPacienteDni(@Param("fecha") String fecha, @Param("dni") String dni);

    /*
     * 2
     * lista ordenada por hora de inicio con las citas actualmente registradas,
     * indicando su datos esenciales
     * (paciente, médico, centro de salud, fecha y hora, estado).
     */
    @Query("SELECT c FROM Cita AS c WHERE c.medico.dni LIKE %:dni%")
    List<Cita> findByMedicoDni(@Param("dni") String dni);

    @Query("SELECT c FROM Cita AS c WHERE c.paciente.dni LIKE %:dni%")
    List<Cita> findByPacienteDni(@Param("dni") String dni);

    @Query("SELECT c FROM Cita AS c WHERE c.fechaYHora.fecha LIKE %:fecha%")
    List<Cita> findByFechaYHoraFecha(@Param("fecha") String fecha);

    @Query("SELECT c FROM Cita AS c WHERE c.fechaYHora.hora LIKE %:hora%")
    List<Cita> findByFechaYHoraHora(@Param("hora") String hora);

    @Query("SELECT c FROM Cita AS c WHERE c.duracion LIKE %:duracion%")
    List<Cita> findByDuracion(@Param("duracion")  String duracion);

    @Query("SELECT c FROM Cita AS c WHERE c.estado LIKE %:estado%")
    List<Cita> findByEstado(@Param("estado") String estado);


}
