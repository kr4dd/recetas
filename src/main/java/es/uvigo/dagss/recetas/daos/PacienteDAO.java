package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteDAO extends JpaRepository<Medico, String> {
    // Filtrado de nombre del paciente
    @Query("SELECT p FROM Paciente AS p WHERE p.nombre LIKE %:patron%")
    List<Paciente> findByPatronNombre(@Param("patron") String patron);

    List<Paciente> findByApellidosContaining(String apellidos);

    // Buscar que medico atiende el paciente
    List<Paciente> findByMedico(String dni);
    List<Paciente> findByEmailContaining(String email);
}
