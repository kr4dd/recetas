package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface CitaDAO extends JpaRepository<Cita, String> {
    List<Cita> findById(long id);
    List<Cita> findByMedico(String dni);
    List<Cita> findByPaciente(String dni);

}
