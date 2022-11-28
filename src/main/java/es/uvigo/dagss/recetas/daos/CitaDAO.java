public interface CitaDAO extends JpaRepository<Cita, String> {
    List<Cita> findById(long id);
    List<Cita> findByMedico(String dni);
    List<Cita> findByPaciente(String dni);

}
