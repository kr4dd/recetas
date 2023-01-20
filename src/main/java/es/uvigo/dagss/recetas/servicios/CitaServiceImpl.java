package es.uvigo.dagss.recetas.servicios;

import java.util.List;
import java.util.Optional;

import es.uvigo.dagss.recetas.entidades.Cita;

public class CitaServiceImpl implements CitaService{

    @Override
    public Cita crear(Cita cita) {
        return null;
    }

    @Override
    public Cita modificar(Cita prescripcion) {
        return null;
    }

    @Override
    public void eliminar(Cita prescripcion) {
        
    }

    @Override
    public Optional<Cita> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Cita> buscarTodos() {
        return null;
    }

    @Override
    public List<Cita> buscarFechaMedico() {
        return null;
    }

    @Override
    public List<Cita> buscarFechaPaciente() {
        return null;
    }
    
}
