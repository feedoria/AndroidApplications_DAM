package ro.ase.ex_filme;

import java.io.Serializable;
import java.util.Date;

public class Film implements Serializable {
    String titlu;
    String regizor;
    Date dataLansarii;
    int durata;
    String gen;
    boolean vazut;

    public Film(String titlu, String regizor, Date dataLansarii, int durata, String gen, boolean vazut) {
        this.titlu = titlu;
        this.regizor = regizor;
        this.dataLansarii = dataLansarii;
        this.durata = durata;
        this.gen = gen;
        this.vazut = vazut;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getRegizor() {
        return regizor;
    }

    public void setRegizor(String regizor) {
        this.regizor = regizor;
    }

    public Date getDataLansarii() {
        return dataLansarii;
    }

    public void setDataLansarii(Date dataLansarii) {
        this.dataLansarii = dataLansarii;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public boolean isVazut() {
        return vazut;
    }

    public void setVazut(boolean vazut) {
        this.vazut = vazut;
    }

    @Override
    public String toString() {
        return "Film{" +
                "titlu='" + titlu + '\'' +
                ", regizor='" + regizor + '\'' +
                ", dataLansarii=" + dataLansarii +
                ", durata=" + durata +
                ", gen='" + gen + '\'' +
                ", vazut=" + vazut +
                '}';
    }
}
