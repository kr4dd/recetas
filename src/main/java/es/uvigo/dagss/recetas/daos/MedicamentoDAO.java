package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface MedicamentoDAO extends JpaRepository<Medicamento, Long> {
 
    /*DAO HECHO POR MIRI -------- todo OK*/ 
    /*La lista de medicamentos podrá filtrarse por nombre comercial, principio activo, fabricante o famila.*/
    @Query("SELECT m FROM Medicamento AS m WHERE m.nombreComercial LIKE %:patron%")
    List<Medicamento> findByNombreComercial(@Param("patron") String patron);

    @Query("SELECT m FROM Medicamento AS m WHERE m.principioActivo LIKE %:patron%")
    List<Medicamento> findByPrincipioActivo(@Param("patron") String patron);

    @Query("SELECT m FROM Medicamento AS m WHERE m.fabricante LIKE %:patron%")
    List<Medicamento> findByFabricante(@Param("patron") String patron);

    /*@Query("SELECT m FROM Medicamento AS m WHERE m.familia LIKE %:patron%")
    List<Medicamento> findByFamiliaFarmacos(String familia);*/

}
