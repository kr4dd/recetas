package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.MedicamentoDAO;
import es.uvigo.dagss.recetas.entidades.Medicamento;

@Service
public class MedicamentoServiceImpl implements MedicamentoService{
    
    @Autowired
    MedicamentoDAO dao;

    @Override
    @Transactional
    public Medicamento crear(Medicamento medicamento) {
        return dao.save(medicamento);
    }

    @Override
    @Transactional
    public Medicamento modificar(Medicamento medicamento) {
        return dao.save(medicamento);
    }

    @Override
    @Transactional
    public void eliminar(Medicamento medicamento) {
         dao.delete(medicamento); 
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Medicamento> buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicamento> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicamento> buscarPorNombreComercial(String nombre) {
        return dao.findByNombreComercial(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicamento> buscarPorPrincipioActivo(String principio) {
        return dao.findByPrincipioActivo(principio);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Medicamento> buscarPorFabricante(String fabricante) {
        return dao.findByFabricante(fabricante);
    }
    
}
