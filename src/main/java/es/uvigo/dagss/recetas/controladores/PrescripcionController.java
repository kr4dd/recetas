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

import es.uvigo.dagss.recetas.entidades.EstadoPrescripcion;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.servicios.PrescripcionService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/api/prescripciones", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrescripcionController {
    
    @Autowired
    PrescripcionService prescripcionService;

    @GetMapping()
    public ResponseEntity<List<Prescripcion>> buscarTodos(
            @RequestParam(name = "fechaInicioPrescripcion", required = false) Date fechaInicioPrescripcion,
            @RequestParam(name = "fechaFinPrescripcion", required = false) Date fechaFinPrescripcion)
    {
        try {

            List<Prescripcion> resultado = new ArrayList<>();

            if (fechaInicioPrescripcion != null && fechaFinPrescripcion != null) {
                resultado = prescripcionService.BuscarPorFechaInicioFinalPrescripcion(fechaInicioPrescripcion, fechaFinPrescripcion);
            }else {
                resultado = prescripcionService.buscarTodos();
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
    public ResponseEntity<Prescripcion> buscarPorId(@PathVariable("id") Long id) {
        Optional<Prescripcion> prescripcion = prescripcionService.buscarPorId(id);

        if (prescripcion.isPresent()) {
            return new ResponseEntity<>(prescripcion.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Prescripcion> crear(@RequestBody Prescripcion prescripcion) {
        try {
            Prescripcion nuevaPrescripcion = prescripcionService.crear(prescripcion);
            URI uri = crearURIPrescripcion(nuevaPrescripcion);

            return ResponseEntity.created(uri).body(nuevaPrescripcion);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
        try {
            Optional<Prescripcion> prescripcion = prescripcionService.buscarPorId(id);
            if (prescripcion.isPresent()) {
                Prescripcion nuevaPrescripcion = prescripcion.get();

                nuevaPrescripcion.setEstado(EstadoPrescripcion.ACTIVO);

                //Fijar administrador inactivo
                prescripcionService.modificar(nuevaPrescripcion);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Prescripcion> modificar(@PathVariable("id") Long id, @RequestBody Prescripcion prescripcion) {
        Optional<Prescripcion> prescripcionOptional = prescripcionService.buscarPorId(id);

        if (prescripcionOptional.isPresent()) {

            Prescripcion nuevaPrescripcion = prescripcionOptional.get();

            nuevaPrescripcion.setFechaInicioPrescripcion(prescripcion.getFechaInicioPrescripcion());
            nuevaPrescripcion.setFechaFinPrescripcion(prescripcion.getFechaFinPrescripcion());
            nuevaPrescripcion.setDosisDiaria(prescripcion.getDosisDiaria());
            nuevaPrescripcion.setIndicaciones(prescripcion.getIndicaciones());
            nuevaPrescripcion.setEstado(prescripcion.getEstado());
            nuevaPrescripcion.setMedico(prescripcion.getMedico());
            nuevaPrescripcion.setPaciente(prescripcion.getPaciente());
            nuevaPrescripcion.setRecetas(prescripcion.getRecetas());

            prescripcionService.modificar(nuevaPrescripcion);

            return new ResponseEntity<>(nuevaPrescripcion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    private URI crearURIPrescripcion(Prescripcion prescripcion) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prescripcion.getId()).toUri();
    }
}
