package es.uvigo.dagss.recetas.controladores;

import es.uvigo.dagss.recetas.controladores.dtos.DatosLogin;
import es.uvigo.dagss.recetas.controladores.dtos.DatosRegistro;
import es.uvigo.dagss.recetas.controladores.dtos.MensajeRespuesta;
import es.uvigo.dagss.recetas.controladores.dtos.RespuestaJWT;
import es.uvigo.dagss.recetas.daos.UsuarioDAO;
import es.uvigo.dagss.recetas.entidades.TipoUsuario;
import es.uvigo.dagss.recetas.entidades.Usuario;
import es.uvigo.dagss.recetas.seguridad.jwt.UtilidadesJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/auth")
@CrossOrigin(origins = "*")
public class AutenticacionController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioDAO usuarioDAO;

    @Autowired
    UtilidadesJWT utilidadesJWT;

    @PostMapping(path = "/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid DatosRegistro datosRegistro) {
        if (usuarioDAO.existsByLogin(datosRegistro.getLogin())) {
            return ResponseEntity.badRequest().body(new MensajeRespuesta("Error: El login ya existe"));
        }

        // Crear el usuario
        Usuario usuario = new Usuario(datosRegistro.getLogin(), passwordEncoder.encode(datosRegistro.getPassword()));

        Set<String> roles = datosRegistro.getRoles();
        for (String rol : roles) {
            usuario.anadirRol(TipoUsuario.valueOf(rol));

        }
        usuarioDAO.save(usuario);
        return ResponseEntity.ok(new MensajeRespuesta("Usuario registrado"));
    }

    // Autenticacion "manual", busca usuario con el login indicado y compara passwords recibido con el password hasheado almacenando
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid DatosLogin datosLogin) {
        String login = datosLogin.getLogin();
        String password = datosLogin.getPassword();
        Optional<Usuario> usuarioOptional = usuarioDAO.findFirstByLogin(login);
        if (usuarioOptional.isPresent()) {
            // usuario existe
            Usuario usuario = usuarioOptional.get();
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                // password correcto -> devuelve token JWT
                String token = utilidadesJWT.crearTokenJWT(login);
                return ResponseEntity.ok(new RespuestaJWT(token, usuario.getId(), usuario.getLogin(), Collections.emptyList()));
            }
        }
        return ResponseEntity.badRequest()
                .body(new MensajeRespuesta("Error en autenticacion"));
    }
}