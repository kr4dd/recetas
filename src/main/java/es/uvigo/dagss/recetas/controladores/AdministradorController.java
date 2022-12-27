package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.entidades.EstadoAdministrador;
import es.uvigo.dagss.recetas.servicios.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrador> crear(@RequestBody Administrador administrador) {
        try {
            //Creamos uno nuevo si no existe
            Administrador nuevoAdministrador = administradorService.crear(administrador);
            URI uri = crearURIAdministrador(nuevoAdministrador);

            return ResponseEntity.created(uri).body(nuevoAdministrador);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
        try {
            Optional<Administrador> administrador = administradorService.buscarPorId(id);
            if (administrador.isPresent()) {
                Administrador nuevoAdministrador = administrador.get();

                nuevoAdministrador.setEstado(EstadoAdministrador.INACTIVO);

                //Fijar administrador inactivo
                administradorService.modificar(nuevoAdministrador);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Administrador> modificar(@PathVariable("id") Long id, @RequestBody Administrador administrador) {
        Optional<Administrador> administradorOptional = administradorService.buscarPorId(id);

        if (administradorOptional.isPresent()) {

            Administrador nuevoAdministrador = administradorOptional.get();

            nuevoAdministrador.setLogin(administrador.getLogin());
            nuevoAdministrador.setPassword(administrador.getPassword());
            nuevoAdministrador.setNombre(administrador.getNombre());
            nuevoAdministrador.setEmail(administrador.getEmail());
            nuevoAdministrador.setEstado(administrador.getEstado());

            administradorService.modificar(nuevoAdministrador);

            return new ResponseEntity<>(nuevoAdministrador, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Construye la URI del nuevo recurso creado con POST
    private URI crearURIAdministrador(Administrador administrador) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(administrador.getId()).toUri();
    }
}
