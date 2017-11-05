package ar.uba.fi.tdd.rulogic.model;

abstract class Parser {

    public String obtenerNombre(String linea){
        linea = linea.replaceAll(" ","");
        int posFinal = linea.indexOf("(");
        return linea.trim().substring(0, posFinal);
    }

    public String[] obtenerValores(String linea){
        linea = linea.replaceAll(" ","");
        int posInicial = linea.indexOf("(");
        int posicionFinal = linea.indexOf(")");
        String valores = linea.substring(posInicial+1,posicionFinal);
        return valores.split(",");
    }

    public boolean verificarParentesis(String linea) {
        return linea.matches("^.*\\(.*\\).*$");
    }

    public boolean verificarPuntoFinal(String linea) {
        return linea.endsWith(".");
    }

}
