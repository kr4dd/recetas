package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.daos.MedicoDAO;
import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoServiceImpl implements MedicoService {
    @Autowired
    MedicoDAO dao;

    @Override
    @Transactional
    public Medico crear(Medico medico) {
        return dao.save(medico);
    }

    @Override
    @Transactional
    public Medico modificar(Medico medico) {
        return dao.save(medico);
    }

    @Override
    @Transactional
    public void eliminar(Medico medico) {
        dao.delete(medico);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Medico> buscarPorDNI(String dni) {
        return dao.findById(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medico> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medico> buscarPorNombre(String nombre) {
        return dao.findByPatronNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medico> buscarPorApellidos(String apellidos) {
        return dao.findByApellidosContaining(apellidos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medico> buscarCentroDeSaludPorId(Long id) {
        return dao.findByCentroDeSaludId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medico> buscarPorLocalidad(String localidad) {
        return dao.findByPatronDireccionLocalidad(localidad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medico> buscarPorProvincia(String provincia) {
        return dao.findByPatronDireccionProvincia(provincia);
    }
}
