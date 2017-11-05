package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void queryTests(){
		Assert.assertFalse("Mal formada la consulta con punto final", this.knowledgeBase.answer("varon(juan)."));
		Assert.assertFalse("Mal formada la consulta sin parentesis final",this.knowledgeBase.answer("varon(pepe"));
		Assert.assertFalse("Mal formada la consulta sin parentesis inicial",this.knowledgeBase.answer("varonpepe)"));
		Assert.assertFalse("Mal formada la consulta sin parentesis",this.knowledgeBase.answer("varon pepe."));
	}

	@Test
	public void hechoTest(){
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("varon(juan)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("varon(pepe)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("varon(hector)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("varon(roberto)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("varon(nicolas)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("mujer(maria)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("mujer(cecilia)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("padre(juan, pepe)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("padre(juan, pepa)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("padre(hector, maria)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("padre(roberto, alejandro)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("padre(roberto, cecilia)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("hermano(nicolas, roberto)"));
		Assert.assertTrue("Consulta de hecho exitosa",this.knowledgeBase.answer("hermano(roberto, nicolas)"));
		Assert.assertFalse("Hecho inexistente arroja false",this.knowledgeBase.answer("varon(emilio)"));
		Assert.assertFalse("Hecho inexistente arroja false",this.knowledgeBase.answer("esposo(juan)"));
	}

	@Test
	public void reglasTest(){
		Assert.assertTrue("Consulta de regla exitosa",this.knowledgeBase.answer("hijo(pepe, juan)"));
		Assert.assertTrue("Consulta de regla exitosa",this.knowledgeBase.answer("hija(maria, hector)"));
		Assert.assertTrue("Consulta de regla exitosa", this.knowledgeBase.answer("tio(roberto, juan, nicolas)"));


		this.knowledgeBase = new KnowledgeBase();
		Assert.assertFalse("Consulta de regla falsa",this.knowledgeBase.answer("hijo(juan, pepe)"));
		Assert.assertFalse("Consulta de regla falsa", this.knowledgeBase.answer("tio(juan, pepe, nicolas)"));
		Assert.assertFalse("Consulta de regla falsa", this.knowledgeBase.answer("hija(hector, maria)"));

	}

	@Test
	public void agregarHechosTest(){
		Assert.assertFalse("Consulta de hecho inexistente",this.knowledgeBase.answer("varon(julian)"));
		this.knowledgeBase.agregarInformacion("varon(julian).");
		Assert.assertTrue("Consulta exitosa luego de agregar la informacion",this.knowledgeBase.answer("varon(julian)"));
	}

	@Test
	public void agregarReglasTest(){
		Assert.assertFalse("Regla que no existe",this.knowledgeBase.answer("abuelo(alejandro, maria)"));
		this.knowledgeBase.agregarInformacion("abuelo(X, Y) :- varon(X), mujer(Y).");
		Assert.assertTrue(this.knowledgeBase.answer("abuelo(alejandro, maria)"));
	}

	@Test
	public void entradaSinPuntoFinalTest(){
		Assert.assertTrue(this.knowledgeBase.answer("varon(pepe)"));
		this.knowledgeBase.agregarInformacion("mujer(agustina)");
		Assert.assertFalse(this.knowledgeBase.answer("varon(pepe)"));
	}

	@Test
	public void entradaSinParentesisTest(){
		Assert.assertTrue(this.knowledgeBase.answer("mujer(maria)"));
		this.knowledgeBase.agregarInformacion("padre manuel, gaston.");
		Assert.assertFalse(this.knowledgeBase.answer("mujer(maria)"));

		this.knowledgeBase = new KnowledgeBase();
		Assert.assertTrue(this.knowledgeBase.answer("mujer(maria)"));
		this.knowledgeBase.agregarInformacion("padre (manuel, gaston.");
		Assert.assertFalse(this.knowledgeBase.answer("mujer(maria)"));

		this.knowledgeBase = new KnowledgeBase();
		Assert.assertTrue(this.knowledgeBase.answer("mujer(maria)"));
		this.knowledgeBase.agregarInformacion("padre manuel, gaston).");
		Assert.assertFalse(this.knowledgeBase.answer("mujer(maria)"));
	}

	@Test
	public void archivoMalTest(){
		Assert.assertFalse(this.knowledgeBase.procesarArchivo("cualquier cosa.txt"));
	}

}
