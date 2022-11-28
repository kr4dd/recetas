package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.daos.AdministradorDAO;
import es.uvigo.dagss.recetas.entidades.Administrador;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministradorService {
    private final AdministradorDAO administradorDAO;

    public AdministradorService(AdministradorDAO administradorDAO) {
        this.administradorDAO = administradorDAO;
    }

    public Administrador create(Administrador administrador) {
        return administradorDAO.save(administrador);
    }

    public Administrador update(Administrador administrador) {
        return administradorDAO.save(administrador);
    }

    public Administrador change(Administrador administrador) {
        return administradorDAO.save(administrador);
    }

    public boolean remove(Long id) {
        if (administradorDAO.findById(id).isPresent()) {
            administradorDAO.deleteById(id);
            return true;
        }

        return false;
    }

    public Optional<Administrador> find(Long id) {
        return administradorDAO.findById(id);
    }
}
