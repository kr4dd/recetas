package es.uvigo.dagss.recetas.controladores;


import es.uvigo.dagss.recetas.entidades.EstadoMedico;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.servicios.MedicoService;
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
@RequestMapping(path = "/api/medicos", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @GetMapping()
    public ResponseEntity<List<Medico>> buscarTodos(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "apellido", required = false) String apellido,
            @RequestParam(name = "localidad", required = false) String localidad,
            @RequestParam(name = "provincia", required = false) String provincia,
            @RequestParam(name = "centrosalud", required = false) Long centrosalud)
    {

        try {
            List<Medico> resultado = new ArrayList<>();

            if (nombre != null) {
                resultado = medicoService.buscarPorNombre(nombre);
            } else if (apellido != null) {
                resultado = medicoService.buscarPorApellidos(apellido);
            } else if (localidad != null) {
                resultado = medicoService.buscarPorLocalidad(localidad);
            } else if (provincia != null) {
                resultado = medicoService.buscarPorProvincia(provincia);
            }else if (centrosalud != null) {
                resultado = medicoService.buscarCentroDeSaludPorId(centrosalud);
            } else {
                resultado = medicoService.buscarTodos();
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
    public ResponseEntity<Medico> buscarPorId(@PathVariable("id") Long id) {
        Optional<Medico> medico = medicoService.buscarPorId(id);

        if (medico.isPresent()) {
            return new ResponseEntity<>(medico.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> crear(@RequestBody Medico medico) {
        try {
            //Creamos uno nuevo si no existe
            Medico nuevoMedico = medicoService.crear(medico);

            //Establecer como contraseña por defecto al crearlo su numero de colegiado
            nuevoMedico.setPassword(medico.getNumColegiado());
            medicoService.modificar(nuevoMedico);

            URI uri = crearURIMedico(nuevoMedico);

            return ResponseEntity.created(uri).body(nuevoMedico);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
        try {
            Optional<Medico> medico = medicoService.buscarPorId(id);
            if (medico.isPresent()) {
                Medico nuevoMedico = medico.get();

                nuevoMedico.setEstado(EstadoMedico.INACTIVO);

                //Fijar medico inactivo
                medicoService.modificar(nuevoMedico);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medico> modificar(@PathVariable("id") Long id, @RequestBody Medico medico) {
        Optional<Medico> medicoOptional = medicoService.buscarPorId(id);

        if (medicoOptional.isPresent()) {

            Medico nuevoMedico = medicoOptional.get();

            nuevoMedico.setNombre(medico.getNombre());
            nuevoMedico.setApellidos(medico.getApellidos());
            nuevoMedico.setDni(medico.getDni());
            nuevoMedico.setNumColegiado(medico.getNumColegiado());
            nuevoMedico.setTelefono(medico.getTelefono());
            nuevoMedico.setEmail(medico.getEmail());
            nuevoMedico.setCentroDeSalud(medico.getCentroDeSalud());
            nuevoMedico.setEstado(medico.getEstado());

            medicoService.modificar(nuevoMedico);

            return new ResponseEntity<>(nuevoMedico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Construye la URI del nuevo recurso creado con POST
    private URI crearURIMedico(Medico medico) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medico.getId()).toUri();
    }
}
