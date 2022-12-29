package es.uvigo.dagss.recetas.servicios;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.dagss.recetas.daos.PrescripcionDAO;
import es.uvigo.dagss.recetas.entidades.Prescripcion;

public class PrescripcionServiceImpl  implements PrescripcionService{

    @Autowired
    PrescripcionDAO dao;

    @Override
    @Transactional
    public Prescripcion crear(Prescripcion prescripcion) {
        return dao.save(prescripcion);
    }

    @Override
    @Transactional
    public Prescripcion modificar(Prescripcion prescripcion) {
        return dao.save(prescripcion);
    }

    @Override
    @Transactional
    public void eliminar(Prescripcion prescripcion) {
        dao.delete(prescripcion);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Prescripcion> buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prescripcion> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prescripcion> BuscarPorFechaInicioFinalPrescripcion(Date fechaInicioPrescripcion,
            Date fechaFinPrescripcion) {
        return dao.findByFechaInicioPrescripcionBetween(fechaInicioPrescripcion, fechaFinPrescripcion);
    }
    
}
