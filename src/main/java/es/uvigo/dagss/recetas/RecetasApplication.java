package es.uvigo.dagss.recetas;

import es.uvigo.dagss.recetas.daos.AdministradorDAO;
import es.uvigo.dagss.recetas.daos.CentroDeSaludDAO;
import es.uvigo.dagss.recetas.daos.MedicoDAO;
import es.uvigo.dagss.recetas.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class RecetasApplication implements CommandLineRunner {
	@Autowired
	AdministradorDAO administradorDAO;

	@Autowired
	CentroDeSaludDAO centroDeSaludDAO;

	@Autowired
	MedicoDAO medicoDAO;

	public static void main(String[] args) {
		SpringApplication.run(RecetasApplication.class, args);
	}

	@Override
	public void run(String... args) {
		crearEntidades();
		consultarEntidades();
	}

	private void crearEntidades() {
		Administrador administrador = new Administrador(TipoUsuario.ADMINISTRADOR, "pepe", "pepe123",
				"pepe@gmail.es", EstadoAdministrador.ACTIVO, "pepe");

		administrador = administradorDAO.save(administrador);


		Direccion d1 = new Direccion("123", "Ourense", "32332", "Ourense");
		CentroDeSalud centroDeSalud = new CentroDeSalud("A valenza saude", "avalenzasaude@gmail.es",
				EstadoCentroSalud.ACTIVO, "999999999", d1);

		centroDeSalud = centroDeSaludDAO.save(centroDeSalud);

		//Medico medico = new Medico();

	}

	private void consultarEntidades() {
		List<Administrador> administradores = administradorDAO.findByNombreContaining("pepe");

		System.out.println("Todos los Administradores:");
		for (Administrador a : administradores) {
			System.out.println("\t" + a);
		}
		System.out.println("-----------");


		List<CentroDeSalud> centrosDeSalud = centroDeSaludDAO.findByDireccionProvincia("Ourense");

		System.out.println("Todos los centros de salud:");
		for (CentroDeSalud c : centrosDeSalud) {
			System.out.println("\t" + c);
		}
		System.out.println("-----------");

	}

}
