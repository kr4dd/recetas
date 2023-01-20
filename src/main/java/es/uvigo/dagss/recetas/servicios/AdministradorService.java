package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Administrador;

import java.util.List;
import java.util.Optional;

public interface AdministradorService {
    Administrador crear(Administrador administrador);
    Administrador modificar(Administrador administrador);
    void eliminar(Administrador administrador);
    Optional<Administrador> buscarPorId(Long id);
    List<Administrador> buscarTodos();

    List<Administrador> buscarPorNombre(String nombre);
    List<Administrador> buscarPorEmail(String email);
    List<Administrador> buscarPorEstado(String estado);

}
