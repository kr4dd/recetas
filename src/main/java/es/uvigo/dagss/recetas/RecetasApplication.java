package es.uvigo.dagss.recetas;

import es.uvigo.dagss.recetas.daos.*;
import es.uvigo.dagss.recetas.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication
@ComponentScan("es.uvigo.dagss.recetas.*")

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

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(RecetasApplication.class, args);
	}

	@Override
	public void run(String... args) {
		//Rellenar y consultar el resto de entidades
		crearEntidades();
		consultarEntidades();
	}

	private void crearEntidades() {
		Administrador administrador1 = new Administrador("pepe", passwordEncoder.encode("pepe"),
				"pepe@gmail.es", EstadoAdministrador.ACTIVO, "pepe");

		administrador1.setRoles(new HashSet<>(Arrays.asList(TipoUsuario.ADMINISTRADOR)));
		administrador1 = administradorDAO.save(administrador1);


		Direccion d1 = new Direccion("123", "Ourense", "32332", "Ourense");
		CentroDeSalud centroDeSalud1 = new CentroDeSalud("A valenza saude", "avalenzasaude@gmail.es",
				EstadoCentroSalud.ACTIVO, "999999999", d1);

		centroDeSalud1 = centroDeSaludDAO.save(centroDeSalud1);


		Medico medico1 = new Medico("jose", passwordEncoder.encode("jose"), "77758585L", "jose"
				, "fernan", "7aa1xl", "987878787", "josemedico@gmail.es",
				EstadoMedico.ACTIVO, centroDeSalud1, null);

		medico1.setRoles(new HashSet<>(Arrays.asList(TipoUsuario.MEDICO)));
		medico1 = medicoDAO.save(medico1);


		Direccion d2 = new Direccion("222", "Ourense", "33333", "Ourense");
		Date fecha1 = new Date();
		Paciente paciente1 = new Paciente("maria", passwordEncoder.encode("maria"), "44565968K", "maria", "orlon",
				"987978787", "75986374", "8569785", "maria@gmail.es", d2,
				fecha1, EstadoPaciente.ACTIVO, centroDeSalud1, medico1,
				null);

		paciente1.setRoles(new HashSet<>(Arrays.asList(TipoUsuario.PACIENTE)));
		paciente1 = pacienteDAO.save(paciente1);
		
		
		Direccion d3 = new Direccion("333", "Ourense", "44444", "Ourense");
		Farmacia farmacia1 = new Farmacia("farma", passwordEncoder.encode("farma"), "87574657P", "2983923", "farma@gmail.es",
				"98874635", "gregory", "Marcialo",
				"farmaGuarda", EstadoFarmaceutico.ACTIVO, d3);

		farmacia1.setRoles(new HashSet<>(Arrays.asList(TipoUsuario.FARMACIA)));
		farmacia1 = farmaciaDAO.save(farmacia1);


		FechaYhora fechaYhora = new FechaYhora();
		Cita cita1 = new Cita(EstadoCita.PLANIFICADA, 20, fechaYhora, medico1, paciente1);

		cita1 = citaDAO.save(cita1);

		//MIRI --------------------------
		List<Receta> recetas = new ArrayList<>();
		Prescripcion prescripcion1 =  new Prescripcion(new Date(), new Date(), 5.5, "agua", EstadoPrescripcion.ACTIVO, medico1, paciente1, recetas);
		prescripcion1 = prescripcionDAO.save(prescripcion1);

		Receta receta1 =  new Receta(EstadoReceta.PLANIFICADA, 1, new Date(), new Date(), d2, farmacia1, prescripcion1);
		receta1 = recetaDAO.save(receta1);

		Medicamento medicamento1 = new Medicamento("pepe", "cocaina", "familia1", 1, "España SL", EstadoMedicamento.ACTIVO, prescripcion1);
		medicamento1 = medicamentoDAO.save(medicamento1);

		//-------------------------------------------------------------
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


		List<Paciente> pacientes = pacienteDAO.findByDni("maria");
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

		//List<Medicamento> medicamentos = medicamentoDAO.findByNombreComercial("pepe");
		//List<Medicamento> medicamentos = medicamentoDAO.findByPrincipioActivo("aminiacido");
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

		List<Prescripcion> prescripciones = prescripcionDAO.findByFechaInicioPrescripcionBetween(new Date(), new Date());
		System.out.println("[+]Todos las prescripcion:");
		for (Prescripcion p : prescripciones) {
			System.out.println("\t" + p.getId());
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
