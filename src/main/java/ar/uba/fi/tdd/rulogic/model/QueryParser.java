package ar.uba.fi.tdd.rulogic.model;

public class QueryParser extends Parser {

    private boolean correctQuery;
    private Hecho queryParseado;

    public QueryParser(String query){
        if (this.verificarQuery(query)) this.correctQuery = true;
        else this.correctQuery = false;
    }

    public void parsearQuery(String query){
        this.queryParseado = new Hecho(this.obtenerNombre(query),this.obtenerValores(query));
    }

    private boolean verificarQuery(String query) {
        return (this.noFinalizaConPunto(query) && (this.verificarParentesis(query)));
    }

    private boolean noFinalizaConPunto(String query) {
        return (!(this.verificarPuntoFinal(query)));
    }

    public boolean isCorrectQuery() {
        return correctQuery;
    }


    public Hecho getQueryParseado() {
        return queryParseado;
    }
}
