package es.uvigo.dagss.recetas.daos;
import es.uvigo.dagss.recetas.entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface RecetaDAO extends JpaRepository<Receta, Long> {
    /*DAO HECHO POR MIRI*/

    @Query("SELECT r FROM Receta r WHERE r.prescripcion.paciente.numTarjetaSanitaria = :numTarjetaSanitaria")
    List<Receta> findByNumTarjetaSanitaria(@Param("numTarjetaSanitaria") String numTarjetaSanitaria);
    
    List<Receta> findByPrescripcionId(Long id);

    
}
