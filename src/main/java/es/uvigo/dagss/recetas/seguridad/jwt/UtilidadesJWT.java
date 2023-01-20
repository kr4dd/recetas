package es.uvigo.dagss.recetas.seguridad.jwt;

import java.util.Date;

import es.uvigo.dagss.recetas.seguridad.autenticacion.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class UtilidadesJWT {
    private static final Logger logger = LoggerFactory.getLogger(UtilidadesJWT.class);

    @Value("${ejemlojwt.clave}")
    private String clave;

    @Value("${ejemplojwt.caducidad}")
    private int caducidad;

    public String crearTokenJWT(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return this.crearTokenJWT(userPrincipal.getUsername());
    }

    public String crearTokenJWT(String userName) {

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + caducidad))
                .signWith(SignatureAlgorithm.HS256, clave)
                .compact();
    }

    public String extraerLogin(String token) {
        return Jwts.parser().setSigningKey(clave).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(clave).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}