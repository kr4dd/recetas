package es.uvigo.dagss.recetas.seguridad;

import es.uvigo.dagss.recetas.seguridad.autenticacion.UserDetailsServiceImpl;
import es.uvigo.dagss.recetas.seguridad.jwt.FiltroAutenticacionJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Passwordencoder a usar
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FiltroAutenticacionJWT filtroAutenticacionJWT() {
        // Filtro de autenticación de peticiones basado en JWT
        return new FiltroAutenticacionJWT();
    }

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        // Configuracion de autenticacion
        http.userDetailsService(userDetailsService);

        // Configuracion de autorizacion
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // desactiva uso de cookies y sesiones (peticiones Stateless)
                .cors().and() // habilita CORS
                .csrf().disable() // deshabilita CSRF
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()    // Permite acceso total a /api/auth

                // Permite acceso total a /api/administradores (despues sera limitado con @PreAuthorize)
                .antMatchers("/api/administradores/**").permitAll()

                // Permite acceso total a /api/pacientes (despues sera limitado con @PreAuthorize)
                .antMatchers("/api/pacientes/**").permitAll()

                // Permite acceso total a /api/medicos (despues sera limitado con @PreAuthorize)
                .antMatchers("/api/medicos/**").permitAll()

                // Permite acceso total a /api/farmacias (despues sera limitado con @PreAuthorize)
                .antMatchers("/api/farmacias/**").permitAll()

                // Permite acceso total a /api/centrosdesalud (despues sera limitado con @PreAuthorize)
                .antMatchers("/api/centrosdesalud/**").permitAll()

                // Permite acceso total a /api/citas (despues sera limitado con @PreAuthorize)
                .antMatchers("/api/citas/**").permitAll()

                // Permite acceso total a /api/medicamentos (despues sera limitado con @PreAuthorize)
                .antMatchers("/api/medicamentos/**").permitAll()

                // Permite acceso total a /api/recetas (despues sera limitado con @PreAuthorize)
                .antMatchers("/api/recetas/**").permitAll()

                // Permite acceso total a /api/prescripciones (despues sera limitado con @PreAuthorize)
                .antMatchers("/api/prescripciones/**").permitAll();



        // Filtro JWT
        // Establece filtros por los que pasan las peticiones (y su orden)
        // - filtroAutenticacionJWT: comprueba que petición incluye un token JWT, lo valida
        //  y extrae info. de autenticacion de usuarios de la BD
        // - UsernamePasswordAuthenticationFilter: filtro general que procesa info. de
        // autenticacion de usuarios
        http.addFilterBefore(filtroAutenticacionJWT(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}