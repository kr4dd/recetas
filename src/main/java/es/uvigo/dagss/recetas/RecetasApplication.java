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

		Direccion d4 = new Direccion("444", "Vigo", "55555", "Pontevedra");

		Farmacia farmacia1 = new Farmacia(TipoUsuario.FARMACIA, "farma", "farma1", "87574657P", "2983923", "farma@gmail.es",
				"98874635", "gregory", "Marcialo",
				"farmaGuarda", EstadoFarmaceutico.ACTIVO, d3);

		Farmacia farmacia2 = new Farmacia(TipoUsuario.FARMACIA, "farmacia", "farmacia2", "17244246F", "8735628", "farmacia2@gmail.es",
				"986117820", "Moncho", "Fernandez de la cruz santiago de santa maria",
				"FarmaciaLuces", EstadoFarmaceutico.ACTIVO, d4);


		farmacia1 = farmaciaDAO.save(farmacia1);
		farmacia2 = farmaciaDAO.save(farmacia2);

		FechaYhora fechaYhora = new FechaYhora();
		Cita cita1 = new Cita(EstadoCita.PLANIFICADA, 20, fechaYhora, medico1, paciente1);

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

		List<Paciente> pacientes = pacienteDAO.findByPatronNombre("maria");
/* 		List<Paciente> pacientes = pacienteDAO.findByDireccionAndLocalidad(@Param("localidad") String localidad);
		List<Paciente> pacientes = pacienteDAO.findByDireccionAndProvincia(@Param("provincia") String provincia);
		List<Paciente> pacientes = pacienteDAO.findByCentroDeSaludId(Long id);
		List<Paciente> pacientes = pacienteDAO.findByMedico(String dni); */

		System.out.println("[+]Todos los pacientes:");
		for (Paciente p : pacientes) {
			System.out.println("\t" + p.getNombre());
		}
		System.out.println("-----------");


		List<Farmacia> farmacias = farmaciaDAO.findByDireccionProvincia("Ourense");
		System.out.println("[+]Todos las farmacias:");
		for (Farmacia f : farmacias) {
			System.out.println("\t" + f.getDNI());
		}
		System.out.println("-----------");


		//Pero con exception de LAZY, para evitarlo se debe establecer un servicio
		// indicando transactional, ya que citas recibe multiples accesos
//		List<Cita> citas = citaDAO.findByMedicoDni("77758585L");
//		System.out.println("[+]Todos las citas:");
//		for (Cita c : citas) {
//			System.out.println("\t" + c.toString());
//		}
//		System.out.println("-----------");

	}

}
