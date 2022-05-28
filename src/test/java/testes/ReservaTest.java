package testes;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import core.DriveFactory;
import pageObjects.HomePO;
import pageObjects.LoginPO;
import pageObjects.ReservaPO;

//@TestMethodOrder(OrderAnnotation.class)

@FixMethodOrder(MethodSorters.DEFAULT)
public class ReservaTest {

	LoginPO loginPO = new LoginPO();
	HomePO homePO = new HomePO();
	ReservaPO reservaPO = new ReservaPO();
	
	
	final String USERNAME_SOLICITANTE = "client";
	final String PASSWORD_SOLICITANTE = "client";
	
	final String USERNAME_GESTOR = "admin";
	final String PASSWORD_GESTOR = "admin";
	
	final static String DESCRICAO = "Descricao teste " + System.currentTimeMillis();
	final String LABORATORIO = "Laboratorio";
	
	
	
	//@Order(1) 
	@Test
	public void testcase_1() {
		
		loginPO.acessarPaginaLogin();
		loginPO.escreveUsername(USERNAME_SOLICITANTE);
		loginPO.escreveSenha(PASSWORD_SOLICITANTE);
		loginPO.clicaLogin();
		homePO.clicaCadastrarReserva();
		reservaPO.escreveDescricao(DESCRICAO);
		reservaPO.escreveData("02-05-2022", "0230PM");;
		reservaPO.escreveDataLimite("02-05-2022", "1500AM");
		reservaPO.selecionaLaboratorio(LABORATORIO);
		reservaPO.clicaEnviar();
		reservaPO.clicaOkAlert();
	
	}
	
	
	//@Order(2) 
	@Test
	public void testcase_2() {
		loginPO.acessarPaginaLogin();
		loginPO.escreveUsername(USERNAME_GESTOR);
		loginPO.escreveSenha(PASSWORD_GESTOR);
		loginPO.clicaLogin();
		homePO.clicaReservas();
		reservaPO.editaReservaPeloNome(DESCRICAO);
		reservaPO.selecionaStatus("APROVADO");
		reservaPO.clicaEnviarGestor();
		reservaPO.clicaOkAlert();
		homePO.clicaReservas();
		assertTrue(reservaPO.verificaStatusLinhaTabelaReservaPorDescricao(DESCRICAO).contains("APROVADO"));
	}
	
	@After
	public void afterTest() {
		homePO.clicaSair();
		DriveFactory.killSeleniumDriver();;
	}
	
}
