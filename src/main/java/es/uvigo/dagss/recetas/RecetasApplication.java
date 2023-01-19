package es.uvigo.dagss.recetas;

import es.uvigo.dagss.recetas.daos.*;
import es.uvigo.dagss.recetas.entidades.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
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
	@JsonIgnore
	MedicamentoDAO medicamentoDAO;

	@Autowired
	RecetaDAO recetaDAO;

	@Autowired
	@JsonIgnore
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
		//-----------------MIRI------------------------------
		Paciente paciente2 = new Paciente(TipoUsuario.PACIENTE, "pepe", "maria123", "44565968K", "pepe", "orlon",
				"987978787", "75986374", "000000", "maria@gmail.es", d2,
				fecha1, EstadoPaciente.ACTIVO, centroDeSalud1, medico1,
				null);

		paciente2 = pacienteDAO.save(paciente2);
		//-----------------MIRI------------------------------
		Direccion d3 = new Direccion("333", "Ourense", "44444", "Ourense");
		Farmacia farmacia1 = new Farmacia(TipoUsuario.FARMACIA, "farma", "farma1", "87574657P", "2983923", "farma@gmail.es",
				"98874635", "gregory", "Marcialo",
				"farmaGuarda", EstadoFarmaceutico.ACTIVO, d3);

		farmacia1 = farmaciaDAO.save(farmacia1);


		FechaYhora fechaYhora = new FechaYhora();
		Cita cita1 = new Cita(EstadoCita.PLANIFICADA, 20, fechaYhora, medico1, paciente1);

		cita1 = citaDAO.save(cita1);

		//MIRI --------------------------
		List<Receta> recetas = new ArrayList<>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date fechasImposible3 = sdf.parse("2016-01-02");
			Date fechasImposible4 = sdf.parse("2017-01-02");
			Prescripcion prescripcion1 =  new Prescripcion(fechasImposible3, fechasImposible4, 5.5, "agua", EstadoPrescripcion.ACTIVO, medico1, paciente1, recetas);
			prescripcion1 = prescripcionDAO.save(prescripcion1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		Prescripcion prescripcion1 =  new Prescripcion(new Date(), new Date(), 5.5, "agua", EstadoPrescripcion.ACTIVO, medico1, paciente1, recetas);
		prescripcion1 = prescripcionDAO.save(prescripcion1);

		Prescripcion prescripcion2 =  new Prescripcion(new Date(), new Date(), 5.5, "agua", EstadoPrescripcion.ACTIVO, medico1, paciente2, recetas);
		prescripcion2 = prescripcionDAO.save(prescripcion2);

		Receta receta1 =  new Receta(EstadoReceta.PLANIFACADA, 1, new Date(), new Date(), d2, farmacia1, prescripcion1);
		receta1 = recetaDAO.save(receta1);
		Receta receta2 =  new Receta(EstadoReceta.PLANIFACADA, 1, new Date(), new Date(), d2, farmacia1, prescripcion2);
		receta2 = recetaDAO.save(receta2);

		Medicamento medicamento1 = new Medicamento("pepe", "cocaina", "familia1", 1, "España SL", EstadoMedicamento.ACTIVO, prescripcion1);
		medicamento1 = medicamentoDAO.save(medicamento1);

		//-------------------------------------------------------------
	}

	private void consultarEntidades()  {
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
		//List<Medicamento> medicamentos = medicamentoDAO.findByPrincipioActivo("cocaina");
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

		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date fechasImposible1 = sdf.parse("2024-01-01");
			Date fechasImposible2 = sdf.parse("2024-01-02");
			Date fechasImposible3 = sdf.parse("2016-01-02");
			Date fechasImposible4 = sdf.parse("2017-01-02");
			List<Prescripcion> prescripciones = prescripcionDAO.findByStartDateBetween(fechasImposible1, fechasImposible2);
			System.out.println("[+]Todos las prescripcion con fecha imposible:");
			for (Prescripcion p : prescripciones) {
				System.out.println("\t" + p.getId());
			}
			List<Prescripcion> prescripciones2 = prescripcionDAO.findByStartDateBetween(new Date(), fechasImposible2);
			System.out.println("-----------");
			System.out.println("[+]Todos las prescripcion desde hoy al 2024:");
			for (Prescripcion p : prescripciones2) {
				System.out.println("\t" + p.getId());
			}
			System.out.println("-----------");
			List<Prescripcion> prescripciones3 = prescripcionDAO.findByStartDateBetween(fechasImposible3, fechasImposible4);
			System.out.println("-----------");
			System.out.println("[+]Todos las prescripcion entre 2016 y 2017 (deberia salir 1):");
			for (Prescripcion p : prescripciones3) {
				System.out.println("\t" + p.getId());
			}
			System.out.println("-----------");
			List<Prescripcion> prescripciones4 = prescripcionDAO.findByStartDateBetween(fechasImposible3, fechasImposible1);
			System.out.println("-----------");
			System.out.println("[+]Todos las prescripcion entre 2016 y 2024 deberian salir todas:");
			for (Prescripcion p : prescripciones4) {
				System.out.println("\t" + p.getId());
			}
			System.out.println("-----------");
		} catch (Exception e) {
			
		}
		List<Receta> recetas2 = recetaDAO.findByNumTarjetaSanitaria("75986374");
		System.out.println("[+]Todos las recetas con ese numero de trajeta sanitaria:");
		for (Receta r : recetas2) {
			System.out.println("\t" + r.getNumReceta());
		}
		System.out.println("-----------");
		

	}

}
