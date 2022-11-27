package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.daos.CentroDeSaludDAO;
import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CentroDeSaludService {
    private final CentroDeSaludDAO centroDeSaludDAO;

    public CentroDeSaludService(CentroDeSaludDAO centroDeSaludDAO) {
        this.centroDeSaludDAO = centroDeSaludDAO;
    }

    public CentroDeSalud create(CentroDeSalud centroDeSalud) {
        return centroDeSaludDAO.save(centroDeSalud);
    }

    public CentroDeSalud update(CentroDeSalud centroDeSalud) {
        return centroDeSaludDAO.save(centroDeSalud);
    }

    public CentroDeSalud change(CentroDeSalud centroDeSalud) {
        return centroDeSaludDAO.save(centroDeSalud);
    }

    public boolean remove(Long id) {
        if (centroDeSaludDAO.findById(id).isPresent()) {
            centroDeSaludDAO.deleteById(id);
            return true;
        }

        return false;
    }

    public Optional<CentroDeSalud> find(Long id) {
        return centroDeSaludDAO.findById(id);
    }
}
