package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import es.uvigo.dagss.recetas.entidades.EstadoCentroSalud;
import es.uvigo.dagss.recetas.servicios.CentroDeSaludService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/centrodesalud", produces = MediaType.APPLICATION_JSON_VALUE)
public class CentroDeSaludController {

    @Autowired
    CentroDeSaludService centroDeSaludService;

    @GetMapping()
    public ResponseEntity<List<CentroDeSalud>> buscarTodos(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "localidad", required = false) String localidad,
            @RequestParam(name = "provincia", required = false) String provincia,

            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "telefono", required = false) String telefono,
            @RequestParam(name = "estado", required = false) String estado,
            @RequestParam(name = "domicilio", required = false) String domicilio,
            @RequestParam(name = "codigoPostal", required = false) String codigoPostal)
    {

        try {
            List<CentroDeSalud> resultado = new ArrayList<>();

            if (nombre != null) {
                resultado = centroDeSaludService.buscarPorNombre(nombre);
            } else if (localidad != null) {
                resultado = centroDeSaludService.buscarPorLocalidad(localidad);
            } else if (provincia != null) {
                resultado = centroDeSaludService.buscarPorProvincia(provincia);
            }else if (email != null) {
                resultado = centroDeSaludService.buscarPorEmail(email);
            }else if (telefono != null) {
                resultado = centroDeSaludService.buscarPorTelefono(telefono);
            }else if (estado != null) {
                resultado = centroDeSaludService.buscarPorEstado(estado);
            }else if (domicilio != null) {
                resultado = centroDeSaludService.buscarPorDomicilio(domicilio);
            } else if (codigoPostal != null) {
                resultado = centroDeSaludService.buscarPorCodigoPostal(codigoPostal);
            } else {
                resultado = centroDeSaludService.buscarTodos();
            }

            if (resultado.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CentroDeSalud> buscarPorId(@PathVariable("id") Long id) {
        Optional<CentroDeSalud> centroDeSalud = centroDeSaludService.buscarPorId(id);

        if (centroDeSalud.isPresent()) {
            return new ResponseEntity<>(centroDeSalud.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
        try {
            Optional<CentroDeSalud> centroDeSalud = centroDeSaludService.buscarPorId(id);
            if (centroDeSalud.isPresent()) {
                CentroDeSalud nuevoCentroDeSalud = centroDeSalud.get();

                nuevoCentroDeSalud.setEstado(EstadoCentroSalud.INACTIVO);

                //Fijar centro de salud inactivo
                centroDeSaludService.modificar(nuevoCentroDeSalud);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CentroDeSalud> modificar(@PathVariable("id") Long id, @RequestBody CentroDeSalud centroDeSalud) {
        Optional<CentroDeSalud> centroDeSaludOptional = centroDeSaludService.buscarPorId(id);

        if (centroDeSaludOptional.isPresent()) {

            CentroDeSalud nuevoCentroDeSalud = centroDeSaludOptional.get();

            nuevoCentroDeSalud.setNombre(centroDeSalud.getNombre());
            nuevoCentroDeSalud.setDireccion(centroDeSalud.getDireccion());
            nuevoCentroDeSalud.setTelefono(centroDeSalud.getTelefono());
            nuevoCentroDeSalud.setEmail(centroDeSalud.getEmail());
            nuevoCentroDeSalud.setEstado(centroDeSalud.getEstado());

            centroDeSaludService.modificar(nuevoCentroDeSalud);

            return new ResponseEntity<>(nuevoCentroDeSalud, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
