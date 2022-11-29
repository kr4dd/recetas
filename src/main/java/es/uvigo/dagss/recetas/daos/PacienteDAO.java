package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteDAO extends JpaRepository<Paciente, String> {
    // Filtrado de nombre del paciente
    @Query("SELECT p FROM Paciente AS p WHERE p.nombre LIKE %:patron%")
    List<Paciente> findByPatronNombre(@Param("patron") String patron);

    List<Paciente> findByDni(String dni);
    List<Paciente> findByNumTarjetaSanitaria(String numTarjetaSanitaria);
    List<Paciente> findByEmailContaining(String email);
    List<Paciente> findByNSS(String nss);

    List<Paciente> findByApellidosContaining(String apellidos);

    // Buscar que medico atiende el paciente
    List<Paciente> findByMedicoDni(String dni);

    /*
    La lista de pacientes podrá filtrarse por nombre, localidad o provincia, permitiéndose en todos estos casos búsquedas aproximadas (tipo LIKE en SQL).
     */

    /*
    También podrá filtrarse la lista de pacientes por centro de salud
     */

    /*
    médico asignado (seleccionando un médico de una lista desplegable con todos los médicos disponibles en el centro de salud indicado).
     */

    /*
     centros de salud de la provincia de residencia del paciente.
     */


}