package ar.uba.fi.tdd.rulogic.model;

import java.util.Vector;

public class Regla {
    private String nombre;
    private String[] variables;
    private Vector<Hecho> hechos;

    public Regla(String nombre,String[] variables, Vector<Hecho> hechos) {
        this.hechos = hechos;
        this.variables = variables;
        this.nombre = nombre;
    }

    public Vector<Hecho> getHechos() {
        return hechos;
    }
    public String getNombre(){ return nombre; }
    public String[] getVariables() { return variables; }
}
