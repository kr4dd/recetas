package es.uvigo.dagss.recetas.servicios;
import es.uvigo.dagss.recetas.entidades.Medicamento;

import java.util.List;
import java.util.Optional;

public interface MedicamentoService {
    Medicamento crear(Medicamento medicamento);
    Medicamento modificar(Medicamento medicamento);
    void eliminar(Medicamento medicamento);
    Optional<Medicamento> buscarPorId(Long id);
    List<Medicamento> buscarTodos();

    List<Medicamento> buscarPorNombreComercial(String nombre);
    List<Medicamento> buscarPorPrincipioActivo(String principio);
    List<Medicamento> buscarPorFabricante(String fabricante);
}
