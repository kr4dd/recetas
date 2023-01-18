package es.uvigo.dagss.recetas.controladores;


import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.uvigo.dagss.recetas.servicios.RecetaService;
import es.uvigo.dagss.recetas.entidades.EstadoReceta;
import es.uvigo.dagss.recetas.entidades.Receta;

@RestController
@RequestMapping(path = "/api/recetas", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecetasController {

    @Autowired
    RecetaService recetaService;
    
    @GetMapping()
    public ResponseEntity<List<Receta>> buscarTodos(
            @RequestParam(name = "prescripcion", required = false) Long prescripcion,
            @RequestParam(name = "numTarjetaSanitaria", required = false) String numTarjetaSanitaria)
    {
        try {
            List<Receta> resultado = new ArrayList<>();

            if (prescripcion != null) {
                resultado = recetaService.buscarPorPrescripcionId(prescripcion);
            } else if (numTarjetaSanitaria != null) {
                resultado = recetaService.buscarPorNumTarjetaSanit(numTarjetaSanitaria);
            } else {
                resultado = recetaService.buscarTodos();
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
    public ResponseEntity<Receta> buscarPorNumReceta(@PathVariable("id") Long numReceta) {
        Optional<Receta> receta = recetaService.buscarPorId(numReceta);

        if (receta.isPresent()) {
            return new ResponseEntity<>(receta.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Receta> crear(@RequestBody Receta receta) {
        try {
            
            Receta nuevaReceta = recetaService.crear(receta);
            URI uri = crearURIReceta(nuevaReceta);

            return ResponseEntity.created(uri).body(nuevaReceta);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long numReceta) {
        try {
            Optional<Receta> receta = recetaService.buscarPorId(numReceta);
            if (receta.isPresent()) {
                Receta nuevaReceta = receta.get();

                nuevaReceta.setEstado(EstadoReceta.ANULADA);

                recetaService.modificar(nuevaReceta);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Receta> modificar(@PathVariable("id") Long id, @RequestBody Receta receta) {
        Optional<Receta> recetaOptional = recetaService.buscarPorId(id);

        if (recetaOptional.isPresent()) {

            Receta nuevaReceta = recetaOptional.get();

            nuevaReceta.setEstado(receta.getEstado());
            nuevaReceta.setNumUdsMedicamento(receta.getNumUdsMedicamento());
            nuevaReceta.setFechaValidezInicial(receta.getFechaValidezInicial());
            nuevaReceta.setFechaValidezFinal(receta.getFechaValidezFinal());
            nuevaReceta.setDireccion(receta.getDireccion());
            nuevaReceta.setFarmacia(receta.getFarmacia());
            nuevaReceta.setPrescripcion(receta.getPrescripcion());

            recetaService.modificar(nuevaReceta);

            return new ResponseEntity<>(nuevaReceta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    private URI crearURIReceta(Receta receta) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(receta.getNumReceta()).toUri();
    }
    
}
