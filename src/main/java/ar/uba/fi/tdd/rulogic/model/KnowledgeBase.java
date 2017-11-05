package ar.uba.fi.tdd.rulogic.model;

import java.util.Arrays;
import java.util.Vector;



public class KnowledgeBase {
	private static final String archivoBaseDeDatos = "ruta del archivo";
	private DBParser parser;
	private QueryParser queryParser;

	private Vector<Hecho> hechos;
	private Vector<Regla> reglas;


	public KnowledgeBase() {
		this.parser = new DBParser();
		this.parser.parsearBaseDeDatos(archivoBaseDeDatos);
		this.hechos = this.parser.getHechosParseados();
		this.reglas = this.parser.getReglasParseadas();
	}


	public boolean answer(String query) {
		//Database Incorrecta
		if (!this.parser.isCorrectDatabase()) return false;

		//Query Incorrecta
		this.queryParser = new QueryParser(query);
		if (!this.queryParser.isCorrectQuery()) return false;

		this.queryParser.parsearQuery(query);
		Hecho queryParseada = this.queryParser.getQueryParseado();

		//Intenta resolver el Hecho
		if (this.resolverHecho(queryParseada)) return true;

		//Si no puede resolver el hecho, intenta resolverlo como regla
		Regla reglaAtraducir = this.buscarRegla(queryParseada.getNombre());

		if (reglaAtraducir != null){
			Regla reglaTraducida = this.traducirRegla(reglaAtraducir,queryParseada.getValor());
			return this.resolverRegla(reglaTraducida);
		}

		//Si falla hecho y regla devuelve false porque no se encuentra en la base.
		return false;
	}

	private Regla buscarRegla(String nombre) {
		for (int i=0;i<this.reglas.size();i++){
			if(nombre.equals(this.reglas.get(i).getNombre())) return this.reglas.get(i);
		}
		return null;
	}

	private boolean resolverHecho(Hecho queryParseada) {
		for (int i=0; i<this.hechos.size();i++){
			Hecho hechoActual = this.hechos.get(i);
			if (hechoActual.getNombre().equals(queryParseada.getNombre()))
			{ if (Arrays.equals(hechoActual.getValor(), queryParseada.getValor()))
				{return true;}}
		}
		return false;
	}

	private boolean resolverRegla(Regla reglaTraducida){
		for (int i=0; i<reglaTraducida.getHechos().size();i++){
			if(!this.resolverHecho(reglaTraducida.getHechos().get(i))) return false;
		}
		return true;
	}

	private Regla traducirRegla(Regla reglaAtraducir, String[] valores){
		for (int i=0; i<reglaAtraducir.getVariables().length;i++){
			for (int j=0;j<reglaAtraducir.getHechos().size();j++){
				for (int k=0; k<reglaAtraducir.getHechos().get(j).getValor().length;k++){
					if (reglaAtraducir.getHechos().get(j).getValor()[k].equals(reglaAtraducir.getVariables()[i])){
						reglaAtraducir.getHechos().get(j).getValor()[k] = valores[i];
					}
				}
			}
		}
		return reglaAtraducir;
	}
}
