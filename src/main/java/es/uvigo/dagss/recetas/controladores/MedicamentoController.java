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

import es.uvigo.dagss.recetas.servicios.MedicamentoService;
import es.uvigo.dagss.recetas.entidades.EstadoMedicamento;
import es.uvigo.dagss.recetas.entidades.Medicamento;

@RestController
@RequestMapping(path = "/api/medicamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicamentoController {

    @Autowired
    MedicamentoService medicamentosService;
    
    @GetMapping()
    public ResponseEntity<List<Medicamento>> buscarTodos(
            @RequestParam(name = "nombreComercial", required = false) String nombreComercial,
            @RequestParam(name = "principioActivo", required = false) String principioActivo,
            @RequestParam(name = "fabricante", required = false) String fabricante)
    {
        try {
            List<Medicamento> resultado = new ArrayList<>();

            if (nombreComercial != null) {
                resultado = medicamentosService.buscarPorNombreComercial(nombreComercial);
            } else if (principioActivo != null) {
                resultado = medicamentosService.buscarPorPrincipioActivo(principioActivo);
            } else if (fabricante != null) {
                resultado = medicamentosService.buscarPorFabricante(fabricante);
            } else {
                resultado = medicamentosService.buscarTodos();
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
    public ResponseEntity<Medicamento> buscarPorId(@PathVariable("id") Long id) {
        Optional<Medicamento> medicamento = medicamentosService.buscarPorId(id);

        if (medicamento.isPresent()) {
            return new ResponseEntity<>(medicamento.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medicamento> crear(@RequestBody Medicamento medicamento) {
        try {
            //Creamos uno nuevo si no existe
            Medicamento nuevoMedicamento = medicamentosService.crear(medicamento);
            URI uri = crearURIMedicamento(nuevoMedicamento);

            return ResponseEntity.created(uri).body(nuevoMedicamento);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
        try {
            Optional<Medicamento> mediacamento = medicamentosService.buscarPorId(id);
            if (mediacamento.isPresent()) {
                Medicamento nuevoMedicamento = mediacamento.get();

                nuevoMedicamento.setEstado(EstadoMedicamento.INACTIVO);
                medicamentosService.modificar(nuevoMedicamento);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Medicamento> modificar(@PathVariable("id") Long id, @RequestBody Medicamento medicamento) {
        Optional<Medicamento> medicamentoOptional = medicamentosService.buscarPorId(id);

        if (medicamentoOptional.isPresent()) {

            Medicamento nuevoMedicamento = medicamentoOptional.get();

            nuevoMedicamento.setNombreComercial(medicamento.getNombreComercial());
            nuevoMedicamento.setPrincipioActivo(medicamento.getPrincipioActivo());
            nuevoMedicamento.setFamilia(medicamento.getFamilia());
            nuevoMedicamento.setNumDosis(medicamento.getNumDosis());
            nuevoMedicamento.setFabricante(medicamento.getFabricante());
            nuevoMedicamento.setEstado(medicamento.getEstado());
            nuevoMedicamento.setPrescripcion(medicamento.getPrescripcion());

            medicamentosService.modificar(nuevoMedicamento);

            return new ResponseEntity<>(nuevoMedicamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    
    // Construye la URI del nuevo recurso creado con POST
    private URI crearURIMedicamento(Medicamento medicamento) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(medicamento.getId()).toUri();
    }
}