package es.uvigo.dagss.recetas.servicios;
import es.uvigo.dagss.recetas.entidades.Receta;

import java.util.List;
import java.util.Optional;

public interface RecetaService {
    
    Receta crear(Receta receta);
    Receta modificar(Receta receta);
    void eliminar(Receta receta);
    Optional<Receta> buscarPorId(Long id);
    List<Receta> buscarTodos();

    List<Receta> buscarPorNumTarjetaSanit(String numero);
    List<Receta> buscarPorPrescripcionId(Long id);
}
