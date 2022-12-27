package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
public interface PrescripcionDAO extends JpaRepository<Prescripcion, String> {
    
    List<Prescripcion> findByNombrePaciente(String nombre, String apellidos);
    List<Prescripcion> findByIdPrescrion(long id);
    List<Prescripcion> findByNombreMedico(String nombre, String apellidos);
    List<Prescripcion> findByDniMedico(String dni);
}

/*
 prescripción con la siguiente información:

    medicamento prescrito
    paciente al que se le prescribe el medicamento
    médico que ha realizado la prescripción
    dosis diaria (como Double, indicando el número de unidades del medicamento (píldoras, ml, etc) a tomar por día)
    indicaciones (como String de texto libre, indicando, por ejemplo, nº de tomas por día, toma en ayunas o no, etc)
    fecha de inicio de la prescripción (que sera la fecha actual)
    fecha de fin de la prescripción
    estado de la prescripción [ACTIVO/INACTIVO], con el valor inicial a ACTIVO

*/