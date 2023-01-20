package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import es.uvigo.dagss.recetas.entidades.EstadoPaciente;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uvigo.dagss.recetas.daos.PacienteDAO;
import es.uvigo.dagss.recetas.entidades.Paciente;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteDAO dao;

    @Override
    @Transactional
    public Paciente crear(Paciente paciente) {
        return dao.save(paciente);
    }

    @Override
    @Transactional
    public Paciente modificar(Paciente paciente) {
        return dao.save(paciente);
    }

    @Override
    @Transactional
    public void eliminar(Paciente paciente) {
        dao.delete(paciente);        
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paciente> buscarPorId(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarTodos() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorNombre(String nombre) {
        return dao.findByPatronNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorNumTarjetaSanitaria(String numTarjetaSanitaria) {
        return dao.findByNumTarjetaSanitaria(numTarjetaSanitaria);
    }

    @Override
    public List<Paciente> buscarPorEstado(String estado) {
        return dao.findByEstado(EstadoPaciente.valueOf(estado));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorApellidos(String apellidos) {
        return dao.findByApellidosContaining(apellidos);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarCentroDeSaludPorId(Long id) {
        return dao.findByCentroDeSaludId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorLocalidad(String localidad) {
        return dao.findByDireccionLocalidad(localidad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> buscarPorProvincia(String provincia) {
        return dao.findByDireccionProvincia(provincia);
    }
    
}
