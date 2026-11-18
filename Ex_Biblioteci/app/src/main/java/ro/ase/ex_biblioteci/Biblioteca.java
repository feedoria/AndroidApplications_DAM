package ro.ase.ex_biblioteci;

import java.io.Serializable;
import java.util.Date;

public class Biblioteca implements Serializable {
    String titlu;
    String autor;
    Date dataPublicarii;
    int nrPagini;
    String gen;

    public Biblioteca(String titlu, String autor, Date dataPublicarii, int nrPagini, String gen) {
        this.titlu = titlu;
        this.autor = autor;
        this.dataPublicarii = dataPublicarii;
        this.nrPagini = nrPagini;
        this.gen = gen;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataPublicarii() {
        return dataPublicarii;
    }

    public void setDataPublicarii(Date dataPublicarii) {
        this.dataPublicarii = dataPublicarii;
    }

    public int getNrPagini() {
        return nrPagini;
    }

    public void setNrPagini(int nrPagini) {
        this.nrPagini = nrPagini;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    @Override
    public String toString() {
        return "Biblioteca{" +
                "titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", dataPublicarii=" + dataPublicarii +
                ", nrPagini=" + nrPagini +
                ", gen='" + gen + '\'' +
                '}';
    }
}
