package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.entidades.EstadoAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministradorDAO extends JpaRepository<Administrador, Long> {
    List<Administrador> findByNombreContaining(String nombre);
    List<Administrador> findByEmailContaining(String email);
    List<Administrador> findByEstadoContaining(EstadoAdministrador estado);
}
