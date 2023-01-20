package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.EstadoPaciente;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.servicios.PacienteService;

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
@RequestMapping(path = "/api/pacientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    // @RequestParam(name = "estadopaciente", required = false) EstadoPaciente
    // estado
    @GetMapping()
    public ResponseEntity<List<Paciente>> buscarTodos(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "apellidos", required = false) String apellidos,
            @RequestParam(name = "centroDeSalud", required = false) Long centroDeSalud,
            @RequestParam(name = "localidad", required = false) String localidad,
            @RequestParam(name = "provincia", required = false) String provincia,
            @RequestParam(name = "estado", required = false) String estado) {

        try {
            List<Paciente> resultado = new ArrayList<>();

            if (nombre != null) {
                resultado = pacienteService.buscarPorNombre(nombre);
            } else if (apellidos != null) {
                resultado = pacienteService.buscarPorApellidos(apellidos);
            } else if (localidad != null) {
                resultado = pacienteService.buscarPorLocalidad(localidad);
            } else if (provincia != null) {
                resultado = pacienteService.buscarPorProvincia(provincia);
            } else if (centroDeSalud != null) {
                resultado = pacienteService.buscarCentroDeSaludPorId(centroDeSalud);
            } else {
                resultado = pacienteService.buscarTodos();
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
    public ResponseEntity<Paciente> buscarPorDNI(@PathVariable("id") Long id) {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
        if (paciente.isPresent()) {
            return new ResponseEntity<>(paciente.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> crear(@RequestBody Paciente paciente) {
        try {
            // Creamos uno nuevo si no existe
            Paciente nuevoPaciente = pacienteService.crear(paciente);

            // Establecer como contraseña por defecto al crearlo su numero de colegiado
            nuevoPaciente.setPassword(paciente.getDNI());
            pacienteService.modificar(nuevoPaciente);

            URI uri = crearURIPaciente(nuevoPaciente);

            return ResponseEntity.created(uri).body(nuevoPaciente);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
        try {
            Optional<Paciente> paciente = pacienteService.buscarPorId(id);
            if (paciente.isPresent()) {
                Paciente nuevoPaciente = paciente.get();

                nuevoPaciente.setEstado(EstadoPaciente.INACTIVO);

                // Fijar paciente inactivo
                pacienteService.modificar(nuevoPaciente);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> modificar(@PathVariable("id") Long id, @RequestBody Paciente paciente) {
        Optional<Paciente> pacienteOptional = pacienteService.buscarPorId(id);

        if (pacienteOptional.isPresent()) {

            Paciente nuevoPaciente = pacienteOptional.get();

            nuevoPaciente.setNombre(paciente.getNombre());
            nuevoPaciente.setApellidos(paciente.getApellidos());
            nuevoPaciente.setDNI(paciente.getDNI());
            nuevoPaciente.setNumTarjetaSanitaria(paciente.getNumTarjetaSanitaria());
            nuevoPaciente.setNSS(paciente.getNSS());
            nuevoPaciente.setDireccion(nuevoPaciente.getDireccion());
            nuevoPaciente.setTelefono(paciente.getTelefono());
            nuevoPaciente.setEmail(paciente.getEmail());
            nuevoPaciente.setFechaNacimiento(nuevoPaciente.getFechaNacimiento());
            nuevoPaciente.setCentroDeSalud(paciente.getCentroDeSalud());
            nuevoPaciente.setMedico(nuevoPaciente.getMedico());
            nuevoPaciente.setEstado(paciente.getEstado());

            pacienteService.modificar(nuevoPaciente);

            return new ResponseEntity<>(nuevoPaciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Construye la URI del nuevo recurso creado con POST
    private URI crearURIPaciente(Paciente paciente) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{dni}").buildAndExpand(paciente.getDNI()).toUri();
    }
}
