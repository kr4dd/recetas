package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.entidades.EstadoAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdministradorDAO extends JpaRepository<Administrador, Long> {
    List<Administrador> findByNombreContaining(String nombre);
    List<Administrador> findByEmailContaining(String email);
    List<Administrador> findByEstado(EstadoAdministrador estado);
}
