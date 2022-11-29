package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.daos.AdministradorDAO;
import es.uvigo.dagss.recetas.entidades.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorServiceImpl implements AdministradorService {
    @Autowired
    AdministradorDAO dao;

    @Override
    @Transactional
    public Administrador crear(Administrador administrador) {
        return dao.save(administrador);
    }

    @Override
    @Transactional
    public Administrador modificar(Administrador administrador) {
        return dao.save(administrador);
    }

    @Override
    @Transactional
    public void eliminar(Administrador administrador) {
        dao.delete(administrador);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Administrador> buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Administrador> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Administrador> buscarPorNombre(String nombre) {
        return dao.findByNombreContaining(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Administrador> buscarPorEmail(String email) {
        return dao.findByEmailContaining(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Administrador> buscarPorEstado(String estado) {
        return dao.findByEstadoContaining(estado);
    }
}
