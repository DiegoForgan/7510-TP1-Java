package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class DBParser extends Parser{
    private boolean correctDatabase;
    private Vector<Hecho> hechosParseados;
    private Vector<Regla> reglasParseadas;


    public DBParser(){
        this.hechosParseados = new Vector<Hecho>();
        this.reglasParseadas = new Vector<Regla>();
        this.correctDatabase = true;
    }



    public void parsearBaseDeDatos (String archivo){
        String linea = null;
        try{

            FileReader fileReader = new FileReader(archivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((linea = bufferedReader.readLine()) != null) {
                if (this.verificarEntradaDeLaBase(linea)){
                    this.parsearLinea(linea.trim());
                }
                else{
                    this.correctDatabase = false;
                    break;
                }
            }

            bufferedReader.close();
        }

        catch(FileNotFoundException ex){
            System.out.println("No se pudo abrir el archivo "+ archivo);
        }

        catch(IOException ex){
            System.out.println("No se pudo leer el archivo!");
        }

    }

    private void parsearLinea(String linea) {
        if (linea.contains(":-")) this.parsearRegla(linea);
        else this.parsearHecho(linea);
    }

    private void parsearRegla(String linea) {
        String nombreDeLaRegla = this.obtenerNombre(linea);
        String[] variablesDeLaRegla = this.obtenerValores(linea);
        Vector<Hecho> hechosDeLaRegla = this.obtenerHechosDeLaRegla(linea);
        this.reglasParseadas.add(new Regla(nombreDeLaRegla,variablesDeLaRegla,hechosDeLaRegla));
    }

    private Vector<Hecho> obtenerHechosDeLaRegla(String linea) {
        Vector<Hecho> hechosDeLaRegla = new Vector<Hecho>();
        linea = linea.replaceAll(" ","");
        int posInicial = linea.indexOf("-");
        linea = linea.substring(posInicial+1);
        String[] listaDeHechos = linea.split("\\),");
        for (int i=0; i < (listaDeHechos.length-1) ;i++){
            listaDeHechos[i] = listaDeHechos[i].trim().concat(")");
        }
        for (int j=0; j<listaDeHechos.length;j++){
            Hecho hechoActual = new Hecho(this.obtenerNombre(listaDeHechos[j]),this.obtenerValores(listaDeHechos[j]));
            hechosDeLaRegla.add(hechoActual);
        }
        return hechosDeLaRegla;
    }

    private void parsearHecho(String linea) {
        Hecho nuevoHecho = new Hecho(this.obtenerNombre(linea),this.obtenerValores(linea));
        this.hechosParseados.add(nuevoHecho);
    }

    private boolean verificarEntradaDeLaBase(String linea) {
        return (this.verificarPuntoFinal(linea) && this.verificarParentesis(linea));
    }

    public boolean isCorrectDatabase() {
        return correctDatabase;
    }

    public Vector<Hecho> getHechosParseados() {
        return this.hechosParseados;
    }

    public Vector<Regla> getReglasParseadas() {
        return reglasParseadas;
    }
}


