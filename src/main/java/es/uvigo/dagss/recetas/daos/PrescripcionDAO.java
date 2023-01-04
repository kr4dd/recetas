package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
public interface PrescripcionDAO extends JpaRepository<Prescripcion, Long> {
    
    List<Prescripcion> findByFechaInicioPrescripcionBetween(Date fechaInicioPrescripcion, Date fechaFinPrescripcion);
      

}
    
