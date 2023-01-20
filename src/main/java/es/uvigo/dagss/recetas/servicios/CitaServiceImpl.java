package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.dagss.recetas.daos.CitaDAO;
import es.uvigo.dagss.recetas.entidades.Cita;
@Service
public class CitaServiceImpl implements CitaService{

    @Autowired
    CitaDAO dao;

    @Override
    public Cita crear(Cita cita) {
        return dao.save(cita);
    }

    @Override
    public Cita modificar(Cita cita) {
        return dao.save(cita);
    }

    @Override
    public void eliminar(Cita cita) {
        dao.delete(cita);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cita> buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cita> buscarFechaMedico(String date, String dni) {
        return dao.findByFechaAndMedicoDni(date, dni);
    }

    @Override
    public List<Cita> buscarFechaPaciente(String date, String dni) {
        return dao.findByFechaAndPacienteDni(date, dni);
    }

    @Override
    public List<Cita> buscarMedico(String dni) {
        return dao.findByMedicoDni(dni);
    }

    @Override
    public List<Cita> buscarPaciente(String dni) {
        return dao.findByPacienteDni(dni);
    }

    @Override
    public List<Cita> buscarFecha(String fecha) {
        return dao.findByFechaYHoraFecha(fecha);
    }

    @Override
    public List<Cita> buscarDuracion(String duracion) {
        return dao.findByDuracion(duracion);
    }

    @Override
    public List<Cita> buscarEstado(String estado) {
        return dao.findByEstado(estado);
    }

    @Override
    public List<Cita> buscarHora(String hora) {
        return dao.findByFechaYHoraHora(hora);
    }
    
}
