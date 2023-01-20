package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
public interface PrescripcionDAO extends JpaRepository<Prescripcion, Long> {

    @Query("SELECT e FROM Prescripcion e WHERE e.fechaInicioPrescripcion BETWEEN :fechaInicioPrescripcion AND :fechaFinPrescripcion")
    List<Prescripcion> findByFechaInicioPrescripcionBetween(
            @Param("fechaInicioPrescripcion")Date fechaInicioPrescripcion,
            @Param("fechaFinPrescripcion")Date fechaFinPrescripcion);

}
    
