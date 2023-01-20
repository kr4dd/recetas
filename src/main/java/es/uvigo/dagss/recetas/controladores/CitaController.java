package es.uvigo.dagss.recetas.controladores;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.EstadoCita;
import es.uvigo.dagss.recetas.servicios.CitaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/api/citas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CitaController {

    @Autowired
    CitaService citaService;

    @GetMapping()
    public ResponseEntity<List<Cita>> buscarTodos(
            @RequestParam(name = "paciente", required = false) String paciente,
            @RequestParam(name = "medico", required = false) String medico,
            @RequestParam(name = "fecha", required = false) String fecha,
            @RequestParam(name = "hora", required = false) String hora,
            @RequestParam(name = "duracion", required = false) String duracion,
            @RequestParam(name = "estado", required = false) String estado) {
        try {

            List<Cita> resultado = new ArrayList<>();

            if (paciente != null) {

                resultado = citaService.buscarPaciente(paciente);

            } else if (medico != null) {

                resultado = citaService.buscarMedico(medico);

            } else if (fecha != null) {

                resultado = citaService.buscarFecha(fecha);

            } else if (hora != null) {

                resultado = citaService.buscarHora(hora);

            } else if (duracion != null) {

                resultado = citaService.buscarDuracion(duracion);

            } else if (estado != null) {

                resultado = citaService.buscarEstado(estado);

            } else {
                resultado = citaService.buscarTodos();
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
    public ResponseEntity<Cita> buscarPorId(@PathVariable("id") Long id) {
        Optional<Cita> cita = citaService.buscarPorId(id);

        if (cita.isPresent()) {
            return new ResponseEntity<>(cita.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cita> crear(@RequestBody Cita cita) {
        try {
            Cita nuevaCita = citaService.crear(cita);
            nuevaCita.setPaciente(cita.getPaciente());
            nuevaCita.setMedico(cita.getMedico());
            nuevaCita.setFechaYHora(cita.getFechaYHora());
            if (cita.getDuracion() == null) {
                nuevaCita.setDuracion(15);
            } else {
                nuevaCita.setDuracion(cita.getDuracion());
            }
            nuevaCita.setEstado(cita.getEstado());
            URI uri = crearURICita(nuevaCita);
            citaService.crear(nuevaCita);
            return ResponseEntity.created(uri).body(nuevaCita);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
        try {
            Optional<Cita> cita = citaService.buscarPorId(id);
            if (cita.isPresent()) {
                Cita nuevaCita = cita.get();

                nuevaCita.setEstado(EstadoCita.ANULADA);

                // Fijar administrador inactivo
                citaService.modificar(nuevaCita);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cita> modificar(@PathVariable("id") Long id, @RequestBody Cita cita) {
        Optional<Cita> citaOptional = citaService.buscarPorId(id);

        if (citaOptional.isPresent()) {

            Cita nuevaCita = citaOptional.get();

            nuevaCita.setPaciente(cita.getPaciente());
            nuevaCita.setMedico(cita.getMedico());
            nuevaCita.setFechaYHora(cita.getFechaYHora());
            if (cita.getDuracion() == null) {
                nuevaCita.setDuracion(15);
            } else {
                nuevaCita.setDuracion(cita.getDuracion());
            }
            nuevaCita.setEstado(cita.getEstado());

            citaService.modificar(nuevaCita);

            return new ResponseEntity<>(nuevaCita, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    private URI crearURICita(Cita cita) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cita.getNumCita()).toUri();
    }
}
