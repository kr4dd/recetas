package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PacienteDAO extends JpaRepository<Paciente, String> {
    /*
     * La lista de pacientes podrá filtrarse por nombre, localidad o provincia,
     * permitiéndose en todos estos casos búsquedas aproximadas (tipo LIKE en SQL).
     */
    @Query("SELECT p FROM Paciente AS p WHERE p.nombre LIKE %:nombre%")
    List<Paciente> findByPatronNombre(@Param("nombre") String nombre);
        //77758585L
        //
    @Query("SELECT p FROM Paciente AS p " +
            "JOIN FETCH " +
            "p.centroDeSalud c WHERE c.direccion.localidad LIKE %:localidad%")    
    List<Paciente> findByDireccionLocalidad(@Param("localidad") String localidad);

    @Query("SELECT p FROM Paciente AS p " +
            "JOIN FETCH " +
            "p.centroDeSalud c WHERE c.direccion.provincia LIKE %:provincia%") 
    List<Paciente> findByDireccionProvincia(@Param("provincia") String provincia);

    /*
     * También podrá filtrarse la lista de pacientes por centro de salud
     */
    @Query("SELECT p FROM Paciente AS p WHERE p.centroDeSalud.id LIKE %:id%")
    List<Paciente> findByCentroDeSalud(Long id);

    /*
     * médico asignado (seleccionando un médico de una lista desplegable con todos
     * los médicos disponibles en el centro de salud indicado).
     */
    @Query("SELECT p FROM Paciente AS p WHERE p.medico.dni LIKE %:dni%")
    List<Paciente> findByMedico(String dni);

    /*
     * centros de salud de la provincia de residencia del paciente.
     */



 /* List<Paciente> findByNumTarjetaSanitaria(String numTarjetaSanitaria);

    // NO LO ENCONTRE EN LAS HITORRIAS, PERO LO DEJAMOS TAMBIEN?????''
    List<Paciente> findByEmailContaining(String email);

    List<Paciente> findByNSS(String nss);

    List<Paciente> findByApellidosContaining(String apellidos); */


}