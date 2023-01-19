package es.uvigo.dagss.recetas;

import es.uvigo.dagss.recetas.daos.*;
import es.uvigo.dagss.recetas.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
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
	MedicamentoDAO medicamentoDAO;

	@Autowired
	RecetaDAO recetaDAO;

	@Autowired
	PrescripcionDAO prescripcionDAO;

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
		Direccion d2 = new Direccion("222", "Ourense", "33333", "Ourense");
		Direccion d3 = new Direccion("333", "Vigo", "34521", "Pontevedra");
		Direccion d4 = new Direccion("444", "Coruña", "23332", "Coruña");
		Direccion d5 = new Direccion("555", "Cangas", "55555", "Pontevedra");

		CentroDeSalud centroDeSalud1 = new CentroDeSalud("A valenza saude", "avalenzasaude@gmail.es",
				EstadoCentroSalud.ACTIVO, "999999999", d1);

		CentroDeSalud centroDeSalud2 = new CentroDeSalud("Alvaro Cunqueiro", "cunqueiroxeral@sergas.gal",
				EstadoCentroSalud.ACTIVO, "986112233", d3);

		centroDeSalud1 = centroDeSaludDAO.save(centroDeSalud1);
		centroDeSalud2 = centroDeSaludDAO.save(centroDeSalud2);

		Medico medico1 = new Medico(TipoUsuario.MEDICO, "jose", "jose123", "77758585L", "jose", "fernan", "7aa1xl",
				"987878787", "josemedico@gmail.es",
				EstadoMedico.ACTIVO, centroDeSalud1, null);

		Medico medico2 = new Medico(TipoUsuario.MEDICO, "laura", "laura123", "84738928M", "laura", "perez", "9vj5gs",
				"982356678", "lauramedico@gmail.es",
				EstadoMedico.ACTIVO, centroDeSalud2, null);

		medico1 = medicoDAO.save(medico1);
		medico2 = medicoDAO.save(medico2);

		Date fecha1 = new Date();

		Paciente paciente1 = new Paciente(TipoUsuario.PACIENTE, "maria", "maria123", "44565968K", "maria", "orlon",
				"987978787", "75986374", "8569785", "maria@gmail.es", d2,
				fecha1, EstadoPaciente.ACTIVO, centroDeSalud1, medico1,
				null);

		Paciente paciente2 = new Paciente(TipoUsuario.PACIENTE, "luis", "luis123", "34526784K", "luis", "fernandez",
				"983432984", "48509245", "9834367", "luisito@hotmail.com", d3,
				fecha1, EstadoPaciente.ACTIVO, centroDeSalud2, medico2,
				null);
		Paciente paciente3 = new Paciente(TipoUsuario.PACIENTE, "manuela", "manuela123", "74546774Z", "manuela", "paez",
				"885618290", "76289356", "8730982", "manuela@hotmail.com", d4,
				fecha1, EstadoPaciente.ACTIVO, centroDeSalud2, medico1,
				null);

		Paciente paciente4 = new Paciente(TipoUsuario.PACIENTE, "Juan", "juan123", "44526576L", "juan", "gonzalez",
				"776589201", "89653876", "9876354", "juan@hotmail.com", d5,
				fecha1, EstadoPaciente.ACTIVO, centroDeSalud2, medico2,
				null);

		paciente1 = pacienteDAO.save(paciente1);
		paciente2 = pacienteDAO.save(paciente2);
		paciente3 = pacienteDAO.save(paciente3);
		paciente4 = pacienteDAO.save(paciente4);

		Farmacia farmacia1 = new Farmacia(TipoUsuario.FARMACIA, "farma", "farma1", "87574657P", "2983923",
				"farma@gmail.es",
				"98874635", "gregory", "Marcialo",
				"farmaGuarda", EstadoFarmaceutico.ACTIVO, d3);

		Farmacia farmacia2 = new Farmacia(TipoUsuario.FARMACIA, "farma2", "farma2", "17244246F", "8735628",
				"farmacia2@gmail.es",
				"986117820", "Moncho", "Fernandez de la cruz santiago de santa maria",
				"FarmaciaLuces", EstadoFarmaceutico.ACTIVO, d4);

		Farmacia farmacia3 = new Farmacia(TipoUsuario.FARMACIA, "farma3", "farma3", "97243236D", "7830289",
				"farma3@gmail.es",
				"889625378", "Juana", "Perez Fernandez",
				"FarmaciaJuana", EstadoFarmaceutico.ACTIVO, d1);

		Farmacia farmacia4 = new Farmacia(TipoUsuario.FARMACIA, "farma4", "farma4", "12389765F", "7659867",
				"farma4@gmail.es",
				"663892678", "Jose", "Borrego da Silva",
				"SilvaFarma", EstadoFarmaceutico.ACTIVO, d5);

		farmacia1 = farmaciaDAO.save(farmacia1);
		farmacia2 = farmaciaDAO.save(farmacia2);
		farmacia3 = farmaciaDAO.save(farmacia3);
		farmacia4 = farmaciaDAO.save(farmacia4);

		FechaYhora fechaYhora = new FechaYhora();
		FechaYhora fechaYhora2 = new FechaYhora("2022-12-23", "14:31:15:209");
		FechaYhora fechaYhora3 = new FechaYhora("2022-11-17", "16:15:05:209");
		FechaYhora fechaYhora4 = new FechaYhora("2022-06-03", "18:11:23:209");

		System.out.println(
				"\n\n\n\n\nFecha 1: " + fechaYhora.toString() + " Fecha 2: " + fechaYhora2.toString() + "\n\n\n\n\n");

		Cita cita1 = new Cita(EstadoCita.PLANIFICADA, 20, fechaYhora, medico1, paciente1);
		Cita cita2 = new Cita(EstadoCita.PLANIFICADA, 35, fechaYhora2, medico2, paciente2);
		Cita cita3 = new Cita(EstadoCita.PLANIFICADA, 15, fechaYhora3, medico1, paciente3);
		Cita cita4 = new Cita(EstadoCita.PLANIFICADA, 55, fechaYhora4, medico2, paciente1);

		cita1 = citaDAO.save(cita1);
		cita2 = citaDAO.save(cita2);
		cita3 = citaDAO.save(cita3);
		cita4 = citaDAO.save(cita4);

		// MIRI --------------------------
		List<Receta> recetas = new ArrayList<>();
		Prescripcion prescripcion1 = new Prescripcion(new Date(), new Date(), 5.5, "agua", EstadoPrescripcion.ACTIVO,
				medico1, paciente1, recetas);
		prescripcion1 = prescripcionDAO.save(prescripcion1);

		Receta receta1 = new Receta(EstadoReceta.PLANIFICADA, 1, new Date(), new Date(), d2, farmacia1, prescripcion1);
		receta1 = recetaDAO.save(receta1);

		Medicamento medicamento1 = new Medicamento("pepe", "cocaina", "familia1", 1, "España SL",
				EstadoMedicamento.ACTIVO, prescripcion1);
		medicamento1 = medicamentoDAO.save(medicamento1);

		// -------------------------------------------------------------
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

		// List<Medico> medicos = medicoDAO.findByPatronNombre("jo");
		// List<Medico> medicos = medicoDAO.findByCentroDeSaludId(1L);
		// List<Medico> medicos = medicoDAO.findByPatronDireccionLocalidad("Ou");
		List<Medico> medicos = medicoDAO.findByPatronDireccionProvincia("Ou");
		System.out.println("[+]Todos los medicos:");
		for (Medico m : medicos) {
			System.out.println("\t" + m.getNombre());
		}
		System.out.println("-----------");

		// List<Medicamento> medicamentos =
		// medicamentoDAO.findByNombreComercial("pepe");
		// List<Medicamento> medicamentos =
		// medicamentoDAO.findByPrincipioActivo("aminiacido");
		List<Medicamento> medicamentos = medicamentoDAO.findByFabricante("España SL");
		System.out.println("[+]Todos los medicamentos con ese nombre:");
		for (Medicamento m : medicamentos) {
			System.out.println("\t" + m.getNombreComercial());
		}
		System.out.println("-----------");

		// ----------------------------------------------------------------------------------------
		List<Receta> recetas = recetaDAO.findByPrescripcionId(1L);
		System.out.println("[+]Todos las recetas:");
		for (Receta r : recetas) {
			System.out.println("\t" + r.getNumReceta());
		}
		System.out.println("-----------");

		List<Prescripcion> prescripciones = prescripcionDAO.findByFechaInicioPrescripcionBetween(new Date(),
				new Date());
		System.out.println("[+]Todos las prescripcion:");
		for (Prescripcion p : prescripciones) {
			System.out.println("\t" + p.getId());
		}
		System.out.println("-----------");

		// Pero con exception de LAZY, para evitarlo se debe establecer un servicio
		// indicando transactional, ya que citas recibe multiples accesos
		// List<Cita> citas = citaDAO.findByMedicoDni("77758585L");
		// System.out.println("[+]Todos las citas:");
		// for (Cita c : citas) {
		// System.out.println("\t" + c.toString());
		// }
		// System.out.println("-----------");

		List<Paciente> pacientesNombre = pacienteDAO.findByPatronNombre("pepe");
		List<Paciente> pacientesLocalidad = pacienteDAO.findByDireccionLocalidad("Ourense");
		List<Paciente> pacientesProvincia = pacienteDAO.findByDireccionProvincia("Pontevedra");
		List<Paciente> pacientesCentroSalud = pacienteDAO.findByCentroDeSaludId(2L);
		List<Paciente> pacientesMedico = pacienteDAO.findByMedico("77758585L");

		System.out.println("[+]Todos los pacientes por nombre: luis");
		for (Paciente p : pacientesNombre) {
			System.out.println("\t" + p.getNombre());
		}
		System.out.println("-----------");

		System.out.println("[+]Todos los pacientes por localidad: Ourense");
		for (Paciente p : pacientesLocalidad) {
			System.out.println("\t" + p.getNombre());
		}
		System.out.println("-----------");

		System.out.println("[+]Todos los pacientes por provincia: Pontevedra");
		for (Paciente p : pacientesProvincia) {
			System.out.println("\t" + p.getNombre());
		}
		System.out.println("-----------");

		System.out.println("[+]Todos los pacientes por centro de salud: 2");
		for (Paciente p : pacientesCentroSalud) {
			System.out.println("\t" + p.getNombre());
		}
		System.out.println("-----------");

		System.out.println("[+]Todos los pacientes por medico: 77758585L");
		for (Paciente p : pacientesMedico) {
			System.out.println("\t" + p.getNombre());
		}
		System.out.println("-----------");

		// FarmaciaLuces FarmaciaJuana
		List<Farmacia> farmaciaNombre = farmaciaDAO.findByNombreEstablecimientoContaining("FarmaciaLuces");

		List<Farmacia> farmaciasLocalidad = farmaciaDAO.findByDireccionLocalidad("Coruña");

		List<Farmacia> farmaciasProvincia = farmaciaDAO.findByDireccionProvincia("Pontevedra");

		System.out.println("[+]Todos las farmacias por nombre de establecimientos: FarmaciaLuces");
		for (Farmacia f : farmaciaNombre) {
			System.out.println("\t" + f.getDNI());
		}
		System.out.println("-----------");

		System.out.println("[+]Todos las farmacias por localidad: Coruña");
		for (Farmacia f : farmaciasLocalidad) {
			System.out.println("\t" + f.getDNI());
		}
		System.out.println("-----------");

		System.out.println("[+]Todos las farmacias por provincia: Pontevedra");
		for (Farmacia f : farmaciasProvincia) {
			System.out.println("\t" + f.getDNI());
		}
		System.out.println("-----------");

		List<Cita> citasPaciente = citaDAO.findByFechaAndPacienteDni("2022-12-23", "34526784K"); // 44565968K 34526784K 74546774Z 44526576L
		List<Cita> citasMedico = citaDAO.findByFechaAndMedicoDni("2022-11-17", "77758585L"); //77758585L 84738928M 


/* 		FechaYhora fechaYhora2 = new FechaYhora("2022-12-23", "14:31:15:209");
		FechaYhora fechaYhora3 = new FechaYhora("2022-11-17", "16:15:05:209");
		FechaYhora fechaYhora4 = new FechaYhora("2022-06-03", "18:11:23:209");

		Cita cita1 = new Cita(EstadoCita.PLANIFICADA, 20, fechaYhora, medico1, paciente1);
		Cita cita2 = new Cita(EstadoCita.PLANIFICADA, 35, fechaYhora2, medico2, paciente2);
		Cita cita3 = new Cita(EstadoCita.PLANIFICADA, 15, fechaYhora3, medico1, paciente3);
		Cita cita4 = new Cita(EstadoCita.PLANIFICADA, 55, fechaYhora4, medico2, paciente1); */

		System.out.println("[+]Todos las citas por paciente y fecha:");
		for (Cita c : citasPaciente) {
			System.out.println("\t" + c.toString());
		}
		System.out.println("-----------");
	
		System.out.println("[+]Todos las citas por medico y fecha:");
		for (Cita c : citasMedico) {
			System.out.println("\t" + c.toString());
		}
		System.out.println("-----------");
	}

}
