package es.uvigo.dagss.recetas;

import es.uvigo.dagss.recetas.daos.*;
import es.uvigo.dagss.recetas.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class RecetasApplication implements CommandLineRunner {
	@Autowired
	AdministradorDAO administradorDAO;

	@Autowired
	CentroDeSaludDAO centroDeSaludDAO;

	@Autowired
	MedicoDAO medicoDAO;

	@Autowired
	PacienteDAO pacienteDAO;

	@Autowired
	FarmaciaDAO farmaciaDAO;

	@Autowired
	CitaDAO citaDAO;

	public static void main(String[] args) {
		SpringApplication.run(RecetasApplication.class, args);
	}

	@Override
	public void run(String... args) {
		crearEntidades();
		consultarEntidades();
	}

	private void crearEntidades() {
		Administrador administrador1 = new Administrador(TipoUsuario.ADMINISTRADOR, "pepe", "pepe123",
				"pepe@gmail.es", EstadoAdministrador.ACTIVO, "pepe");

		administrador1 = administradorDAO.save(administrador1);


		Direccion d1 = new Direccion("123", "Ourense", "32332", "Ourense");
		CentroDeSalud centroDeSalud1 = new CentroDeSalud("A valenza saude", "avalenzasaude@gmail.es",
				EstadoCentroSalud.ACTIVO, "999999999", d1);

		centroDeSalud1 = centroDeSaludDAO.save(centroDeSalud1);


		Medico medico1 = new Medico(TipoUsuario.MEDICO, "jose", "jose123", "77758585L", "jose"
				, "fernan", "7aa1xl", "987878787", "josemedico@gmail.es",
				EstadoMedico.ACTIVO, centroDeSalud1, null);

		medico1 = medicoDAO.save(medico1);


		Direccion d2 = new Direccion("222", "Ourense", "33333", "Ourense");
		Date fecha1 = new Date();
		Paciente paciente1 = new Paciente(TipoUsuario.PACIENTE, "maria", "maria123", "44565968K", "maria", "orlon",
				"987978787", "75986374", "8569785", "maria@gmail.es", d2,
				fecha1, EstadoPaciente.ACTIVO, centroDeSalud1, medico1,
				null);

		paciente1 = pacienteDAO.save(paciente1);

		Direccion d3 = new Direccion("333", "Ourense", "44444", "Ourense");
		Farmacia farmacia1 = new Farmacia(TipoUsuario.FARMACIA, "farma", "farma1", "87574657P", "2983923", "farma@gmail.es",
				"98874635", "gregory", "Marcialo",
				"farmaGuarda", EstadoFarmaceutico.ACTIVO, d3);

		farmacia1 = farmaciaDAO.save(farmacia1);


		Date fecha2 = new Date();
		Cita cita1 = new Cita(EstadoCita.PLANIFICADA, 20, fecha2, medico1, paciente1);

		cita1 = citaDAO.save(cita1);
	}

	private void consultarEntidades() {
		List<Administrador> administradores = administradorDAO.findByNombreContaining("pepe");
		System.out.println("[+]Todos los Administradores:");
		for (Administrador a : administradores) {
			System.out.println("\t" + a);
		}
		System.out.println("-----------");


		List<CentroDeSalud> centrosDeSalud = centroDeSaludDAO.findByDireccionProvincia("Ourense");
		System.out.println("[+]Todos los centros de salud:");
		for (CentroDeSalud c : centrosDeSalud) {
			System.out.println("\t" + c);
		}
		System.out.println("-----------");


		//List<Medico> medicos = medicoDAO.findByPatronNombre("jo");
		//List<Medico> medicos = medicoDAO.findByCentroDeSaludId(1L);
		//List<Medico> medicos = medicoDAO.findByPatronDireccionLocalidad("Ou");
		List<Medico> medicos = medicoDAO.findByPatronDireccionProvincia("Ou");
		System.out.println("[+]Todos los medicos:");
		for (Medico m : medicos) {
			System.out.println("\t" + m.getNombre());
		}
		System.out.println("-----------");

	}

}
