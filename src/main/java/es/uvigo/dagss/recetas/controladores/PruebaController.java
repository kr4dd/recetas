package es.uvigo.dagss.recetas.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/prueba")
public class PruebaController {

//    @GetMapping("/todos")
//    public String paraTodos() {
//        return "Accesible a todos";
//    }
//
//    @GetMapping("/usuario")
//    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('ROLE_GERENTE') or hasRole('ROLE_ADMINISTRADOR')")
//    public String paraUsuario() {
//        return "Accesible a todos los usuarios";
//    }

//    @GetMapping("/administradores")
//    @PreAuthorize("hasRole('ADMINISTRADOR')")
//    public String paraAdmin() {
//        return "Accesible solo a administradores";
//    }

    @GetMapping("/pacientes")
    @PreAuthorize("hasRole('PACIENTE') or hasRole('ADMINISTRADOR')")
    public String paraPaciente() {
        return "Accesible solo a pacientes o administradores";
    }

//    @GetMapping("/medicos")
//    @PreAuthorize("hasRole('ROLE_MEDICO') or hasRole('ROLE_ADMINISTRADOR')")
//    public String paraMedico() {
//        return "Accesible solo a medicos o administradores";
//    }

    @GetMapping("/farmacias")
    @PreAuthorize("hasRole('FARMACIA') or hasRole('ADMINISTRADOR')")
    public String paraFarmacia() {
        return "Accesible solo a farmacias o administradores";
    }


    /*
    --------------------------------------------
     Accesos solo para rol administrador
    --------------------------------------------
     */
//    @GetMapping("/centrosdesalud")
//    @PreAuthorize("hasRole('ADMINISTRADOR')")
//    public String paraCentroDeSalud() {
//        return "Accesible solo a administradores";
//    }

    @GetMapping("/citas")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('PACIENTE')")
    public String paraCita() {
        return "Accesible solo a administradores";
    }

//    @GetMapping("/medicamentos")
//    @PreAuthorize("hasRole('ADMINISTRADOR')")
//    public String paraMedicamentos() {
//        return "Accesible solo a administradores";
//    }

//    @GetMapping("/recetas")
//    @PreAuthorize("hasRole('PACIENTE') or hasRole('FARMACIA')")
//    public String paraRecetas() {
//        return "Accesible solo a pacientes o farmacias";
//    }

//    @GetMapping("/prescripciones")
//    @PreAuthorize("hasRole('MEDICO') or hasRole('PACIENTE')")
//    public String paraPrescripciones() {
//        return "Accesible solo a pacientes o farmacias";
//    }

}