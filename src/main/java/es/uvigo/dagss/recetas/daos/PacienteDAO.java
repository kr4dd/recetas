package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import es.uvigo.dagss.recetas.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteDAO extends JpaRepository<Paciente, String> {

    List<Paciente> findByDni(String dni);

    List<Paciente> findByNumTarjetaSanitaria(String numTarjetaSanitaria);

    //NO LO ENCONTRE EN LAS HITORRIAS, PERO LO DEJAMOS TAMBIEN?????''
    List<Paciente> findByEmailContaining(String email);

    List<Paciente> findByNSS(String nss);

    List<Paciente> findByApellidosContaining(String apellidos);

    // Buscar que medico atiende el paciente
    List<Paciente> findByMedico(String dni);

    /*
     * La lista de pacientes podrá filtrarse por nombre, localidad o provincia,
     * permitiéndose en todos estos casos búsquedas aproximadas (tipo LIKE en SQL).
     */
    @Query("SELECT p FROM paciente AS c WHERE p.nombre LIKE %:nombre%")
    List<Paciente> findByPatronNombre(@Param("nombre") String nombre);

    @Query("SELECT p FROM paciente AS c WHERE p.direccion.localidad LIKE %:localidad%")
    List<Paciente> findByDireccionLocalidad(@Param("localidad") String localidad);

    @Query("SELECT p FROM paciente AS c WHERE p.direccion.provincia LIKE %:provincia%")
    List<Paciente> findByDireccionProvincia(@Param("provincia") String provincia);

    /*
     * También podrá filtrarse la lista de pacientes por centro de salud
     */

    // Buscar a que centro de salud pertecene el paciente
    // ESTO FUNCIONA?????? COMPROIBAR
    List<Paciente> findByCentroDeSaludId(Long id);

    /*
     * médico asignado (seleccionando un médico de una lista desplegable con todos
     * los médicos disponibles en el centro de salud indicado).
     */
    // TENGO QUE DAR LA LISTA???? O SOLO INDICAR EL MEDICO DE ESTE PACIENTE????
    List<Paciente> findByDniMedico(String dni);

    /*
     * centros de salud de la provincia de residencia del paciente.
     */
    @Query("SELECT p FROM paciente AS p " +
            "JOIN FETCH " +
            "c.centroDeSalud WHERE c.direccion.provincia LIKE %:patron%")
    List<CentroDeSalud> findByPatronDireccionProvincia(@Param("patron") String patron);

}