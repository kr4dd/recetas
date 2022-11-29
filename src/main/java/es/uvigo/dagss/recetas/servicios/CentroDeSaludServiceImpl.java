package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.daos.CentroDeSaludDAO;
import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CentroDeSaludServiceImpl implements CentroDeSaludService {
    @Autowired
    CentroDeSaludDAO dao;

    @Override
    @Transactional
    public CentroDeSalud crear(CentroDeSalud centroDeSalud) {
        return dao.save(centroDeSalud);
    }

    @Override
    @Transactional
    public CentroDeSalud modificar(CentroDeSalud centroDeSalud) {
        return dao.save(centroDeSalud);
    }

    @Override
    @Transactional
    public void eliminar(CentroDeSalud centroDeSalud) {
        dao.delete(centroDeSalud);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CentroDeSalud> buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeSalud> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeSalud> buscarPorNombre(String nombre) {
        return dao.findByPatronNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeSalud> buscarPorEmail(String email) {
        return dao.findByEmailContaining(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeSalud> buscarPorTelefono(String telefono) {
        return dao.findByTelefonoContaining(telefono);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeSalud> buscarPorEstado(String estado) {
        return dao.findByEstado(estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeSalud> buscarPorDomicilio(String domicilio) {
        return dao.findByDireccionDomicilio(domicilio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeSalud> buscarPorCodigoPostal(String codigoPostal) {
        return dao.findByDireccionCodigoPostal(codigoPostal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeSalud> buscarPorLocalidad(String localidad) {
        return dao.findByDireccionLocalidad(localidad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CentroDeSalud> buscarPorProvincia(String provincia) {
        return dao.findByDireccionProvincia(provincia);
    }


}
