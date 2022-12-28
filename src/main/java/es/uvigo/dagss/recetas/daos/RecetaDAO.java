package es.uvigo.dagss.recetas.daos;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface RecetaDAO extends JpaRepository<Receta, String> {
    /*DAO HECHO POR MIRI*/

    /*, desde la opción "Gestión de receta" se accede a un formulario, 
    donde introduciendo el nº de tarjeta sanitaria de un paciente se accederá
     a la lista de recetas que este estén en vigor para ese paciente, visualizándose 
     las recetas existentes en el sistema disponibles para ser servidas al paciente 
     indicado desde la fecha actual*/
     /*
      Para cada receta se mostrará los datos del medicamento de la correspondiente 
      prescripción (principio activo, fabricante), los datos del médico que hizo la 
      prescripción las fechas de validez de cada receta (inical y final) y el número
       de unidades ("cajas") que se servirán en la receta
     */
    @Query("SELECT r FROM Receta AS r "+
            "JOIN FETCH " +
            "r.prescripcion p WHERE p.paciente.numTarjetaSanitaria LIKE %:patron%")
    List<Receta> findByNumTarjetaSanit(@Param("patron") String patron);

    List<Receta> findByPrescripcionId(Long id);

    
}
