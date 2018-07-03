package stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.Format;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import entidades.Cliente;
import entidades.Tienda;

public class Steps {
	
	private String nombre;
	private String apellido;
	private Cliente cliente;
	private List<Cliente> clientesRegistrados;
	private Tienda tienda;
	private List<Cliente> clientesRegistradosUltimaNavidad;

	@Given("^(.+) (.+) wants to become a new customer$")
	public void quiere_convertirse_en_nuevo_cliente(String nombre, String apellido) {
	    this.nombre = nombre;
	    this.apellido = apellido;
	}
	
	@Given("^we have the following customers signed up:$")
	public void tenemos_los_siguientes_clientes_registrados(@Format("yyyy-MM-dd") List<Cliente> clientesRegistrados) {
	    this.clientesRegistrados = clientesRegistrados;
	}

	@Given("^I assigned them to my store$")
	public void les_asigno_a_mi_tienda() {
		this.tienda = new Tienda();
	    for (Cliente cliente: clientesRegistrados) {
	    	this.tienda.asignarCliente(cliente);
	    }
	}
	
	@When("^I sign up (?:him|her) in the platform$")
	public void doy_de_alta_sus_datos_en_la_plataforma() {
		this.cliente = new Cliente("123456789", nombre, apellido, new Date());
	}
	
	@When("^I request a list of customers signed up during the last Christmas Day$")
	public void solicito_un_listado_de_clientes_registrados_durante_la_pasada_Navidad() throws ParseException {
		DateFormat formateadorFecha = new SimpleDateFormat("yyyy-MM-dd");
		Date ultimaNavidad = formateadorFecha.parse("2017-12-25");
		this.clientesRegistradosUltimaNavidad = this.tienda.obtenerClientesRegistradosEn(ultimaNavidad);
	}
	
	@Then("^(?:he|she) starts with (\\d+) points$")
	public void empieza_con_puntos(int puntosEsperados) {
	    assertEquals(puntosEsperados, this.cliente.obtenerPuntos());
	}
	
	@Then("^I get the following customers:$")
	public void obtengo_los_siguientes_clientes(@Format("yyyy-MM-dd") DataTable clientesEsperadosConRegistroUltimaNavidad) {
		clientesEsperadosConRegistroUltimaNavidad.diff(this.clientesRegistradosUltimaNavidad);
	}
	
}
