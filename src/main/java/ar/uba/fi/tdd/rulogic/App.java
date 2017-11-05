package ar.uba.fi.tdd.rulogic;


import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;


/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) {

		System.out.println("I shall answer all your questions!");
		String query = "hijo(pepe, juan)";
		KnowledgeBase baseDeDatos = new KnowledgeBase();
		System.out.println("Your query for: "+ query);
		System.out.println("results: "+ baseDeDatos.answer(query));
    }
}
