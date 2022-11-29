package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.servicios.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/administradores", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdministradorController {

    @Autowired
    AdministradorService administradorService;

    @GetMapping()
    public ResponseEntity<List<Administrador>> buscarTodos(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "estado", required = false) String estado)
    {

        try {
            List<Administrador> resultado = new ArrayList<>();

            if (nombre != null) {
                resultado = administradorService.buscarPorNombre(nombre);
            } else if (email != null) {
                resultado = administradorService.buscarPorEmail(email);
            } else if (estado != null) {
                resultado = administradorService.buscarPorEstado(estado);
            } else {
                resultado = administradorService.buscarTodos();
            }

            if (resultado.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Administrador> buscarPorId(@PathVariable("id") Long id) {
        Optional<Administrador> administrador = administradorService.buscarPorId(id);

        if (administrador.isPresent()) {
            return new ResponseEntity<>(administrador.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
