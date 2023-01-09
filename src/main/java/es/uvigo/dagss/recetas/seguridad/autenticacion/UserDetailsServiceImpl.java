package es.uvigo.dagss.recetas.seguridad.autenticacion;

import es.uvigo.dagss.recetas.daos.UsuarioDAO;
import es.uvigo.dagss.recetas.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuarioDAO usuarioDAO;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioDAO.findFirstByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("No se encuentra un usuario con el login " + login));

        return new UserDetailsImpl(usuario);
    }
}
