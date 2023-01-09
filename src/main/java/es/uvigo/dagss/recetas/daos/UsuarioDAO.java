package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findFirstByLogin(String login);
    public Boolean existsByLogin(String login);
}