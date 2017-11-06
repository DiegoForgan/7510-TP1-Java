package ar.uba.fi.tdd.rulogic;


import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;

import java.util.Scanner;




/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) throws InterruptedException {

		System.out.println("I shall answer all your questions!");
		String query;
		KnowledgeBase baseDeDatos = new KnowledgeBase();
		boolean keepAsking = true;
		Scanner in = new Scanner(System.in);
		while (keepAsking){
			System.out.println("Enter your query:");
			query = in.nextLine();
			System.out.println(baseDeDatos.answer(query));
			System.out.println("Do you wish to keep asking? (s/n)");
			if (in.nextLine().equals("s")) keepAsking= true;
			else {keepAsking = false;}
		}
	}
}
