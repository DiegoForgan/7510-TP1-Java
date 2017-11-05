package ar.uba.fi.tdd.rulogic.model;

public class Hecho {
    private String nombre;
    private String[] valor;

    public Hecho (String nom, String[] valores){
        this.nombre = nom;
        this.valor = valores;
    }

    public String getNombre() {
        return nombre;
    }

    public String[] getValor() {
        return valor;
    }
}
