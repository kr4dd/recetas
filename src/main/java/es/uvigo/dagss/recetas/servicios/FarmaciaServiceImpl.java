package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import es.uvigo.dagss.recetas.entidades.EstadoFarmaceutico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.dagss.recetas.daos.FarmaciaDAO;
import es.uvigo.dagss.recetas.entidades.Farmacia;

@Service
public class FarmaciaServiceImpl implements FarmaciaService{

    @Autowired
    FarmaciaDAO dao;

    @Override
    @Transactional
    public Farmacia crear(Farmacia farmacia) {
        return dao.save(farmacia);
    }

    @Override
    @Transactional
    public Farmacia modificar(Farmacia farmacia) {
        return dao.save(farmacia);
    }

    @Override
    @Transactional
    public void eliminar(Farmacia farmacia) {
        dao.delete(farmacia);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Farmacia> buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Farmacia> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Farmacia> buscarPorNombreEstablecimiento(String nombreEstablecimiento) {
        return dao.findByNombreEstablecimientoContaining(nombreEstablecimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Farmacia> buscarPorLocalidad(String localidad) {
        
        return dao.findByDireccionLocalidad(localidad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Farmacia> buscarPorProvincia(String provincia) {
        
        return dao.findByDireccionProvincia(provincia);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Farmacia> buscarPorEstado(String estado) {
        
        return dao.findByEstado(EstadoFarmaceutico.valueOf(estado));
    }
    
}
