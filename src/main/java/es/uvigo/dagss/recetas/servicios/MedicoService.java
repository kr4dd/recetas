package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.daos.MedicoDAO;
import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoService {
    private final MedicoDAO medicoDAO;

    public MedicoService(MedicoDAO medicoDAO) {
        this.medicoDAO = medicoDAO;
    }

    public Medico create(Medico medico) {
        return medicoDAO.save(medico);
    }

    public Medico update(Medico medico) {
        return medicoDAO.save(medico);
    }

    public Medico change(Medico medico) {
        return medicoDAO.save(medico);
    }

    public boolean remove(String id) {
        if (medicoDAO.findById(id).isPresent()) {
            medicoDAO.deleteById(id);
            return true;
        }

        return false;
    }

    public Optional<Medico> find(String id) {
        return medicoDAO.findById(id);
    }
}
