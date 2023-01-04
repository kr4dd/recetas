package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.dagss.recetas.daos.RecetaDAO;
import es.uvigo.dagss.recetas.entidades.Receta;

@Service
public class RecetaServiceImpl implements RecetaService{
    @Autowired
    RecetaDAO dao;

    @Override
    @Transactional
    public Receta crear(Receta receta) {
        return dao.save(receta);
    }

    @Override
    @Transactional
    public Receta modificar(Receta receta) {
        return dao.save(receta);
    }

    @Override
    @Transactional
    public void eliminar(Receta receta) {
        dao.delete(receta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Receta> buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public List<Receta> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional
    public List<Receta> buscarPorNumTarjetaSanit(String numero) {
        return dao.findByNumTarjetaSanit(numero);
    }

    @Override
    @Transactional
    public List<Receta> buscarPorPrescripcionId(Long id) {
        return dao.findByPrescripcionId(id);
    }
    
}
