package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.EstadoFarmaceutico;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.servicios.FarmaciaService;
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
@RequestMapping(path = "/api/farmacias", produces = MediaType.APPLICATION_JSON_VALUE)
public class FarmaciaController {

    @Autowired
    FarmaciaService farmaciaService;

    @GetMapping()
    public ResponseEntity<List<Farmacia>> buscarTodos(
            @RequestParam(name = "nombreEstablecimiento", required = false) String nombreEstablecimiento,
            @RequestParam(name = "localidad", required = false) String localidad,
            @RequestParam(name = "provincia", required = false) String provincia,
            @RequestParam(name = "estado", required = false) String estado) {

        try {
            List<Farmacia> resultado = new ArrayList<>();

            if (nombreEstablecimiento != null) {
                resultado = farmaciaService.buscarPorNombreEstablecimiento(nombreEstablecimiento);
            } else if (localidad != null) {
                resultado = farmaciaService.buscarPorLocalidad(localidad);
            } else if (provincia != null) {
                resultado = farmaciaService.buscarPorProvincia(provincia);
            } else if (estado != null) {
                resultado = farmaciaService.buscarPorEstado(estado);
            } else {
                resultado = farmaciaService.buscarTodos();
            }

            if (resultado.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Farmacia> buscarPorId(@PathVariable("id") Long id) {
        Optional<Farmacia> farmacia = farmaciaService.buscarPorId(id);

        if (farmacia.isPresent()) {
            return new ResponseEntity<>(farmacia.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Farmacia> crear(@RequestBody Farmacia farmacia) {
        try {
            // Creamos una nueva si no existe
            Farmacia nuevoFarmacia = farmaciaService.crear(farmacia);
            nuevoFarmacia.setPassword(farmacia.getNumColegiado());
            URI uri = crearURIFarmacia(nuevoFarmacia);
            farmaciaService.modificar(nuevoFarmacia);

            return ResponseEntity.created(uri).body(nuevoFarmacia);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
        try {
            Optional<Farmacia> farmacia = farmaciaService.buscarPorId(id);
            if (farmacia.isPresent()) {
                Farmacia nuevoFarmacia = farmacia.get();
                nuevoFarmacia.setEstado(EstadoFarmaceutico.INACTIVO);

                // Fijar farmacia inactivo
                farmaciaService.modificar(nuevoFarmacia);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Farmacia> modificar(@PathVariable("id") Long id, @RequestBody Farmacia farmacia) {
        Optional<Farmacia> farmaciaOptional = farmaciaService.buscarPorId(id);

        if (farmaciaOptional.isPresent()) {

            Farmacia nuevoFarmacia = farmaciaOptional.get();

            nuevoFarmacia.setNombreEstablecimiento(farmacia.getNombreEstablecimiento());
            nuevoFarmacia.setApellidosFarmaceutico(farmacia.getApellidosFarmaceutico());
            nuevoFarmacia.setDNI(farmacia.getDNI());
            nuevoFarmacia.setNumColegiado(farmacia.getNumColegiado());
            nuevoFarmacia.setDireccion(farmacia.getDireccion());
            nuevoFarmacia.setTelefono(farmacia.getTelefono());
            nuevoFarmacia.setEmail(farmacia.getEmail());
            nuevoFarmacia.setEstado(farmacia.getEstado());

            farmaciaService.modificar(nuevoFarmacia);

            return new ResponseEntity<>(nuevoFarmacia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Construye la URI del nuevo recurso creado con POST
    private URI crearURIFarmacia(Farmacia farmacia) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(farmacia.getId()).toUri();
    }
}
