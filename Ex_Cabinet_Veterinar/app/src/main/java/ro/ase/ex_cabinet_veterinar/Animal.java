package ro.ase.ex_cabinet_veterinar;

import java.io.Serializable;
import java.util.Date;

public class Animal implements Serializable {
    String nume;
    String specie;
    Date dataNasterii;
    float greutate;
    boolean esteVaccinat;

    public Animal(String nume, String specie, Date dataNasterii, float greutate, boolean esteVaccinat) {
        this.nume = nume;
        this.specie = specie;
        this.dataNasterii = dataNasterii;
        this.greutate = greutate;
        this.esteVaccinat = esteVaccinat;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "nume='" + nume + '\'' +
                ", specie='" + specie + '\'' +
                ", dataNasterii=" + dataNasterii +
                ", greutate=" + greutate +
                ", esteVaccinat=" + esteVaccinat +
                '}';
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public float getGreutate() {
        return greutate;
    }

    public void setGreutate(float greutate) {
        this.greutate = greutate;
    }

    public boolean isEsteVaccinat() {
        return esteVaccinat;
    }

    public void setEsteVaccinat(boolean esteVaccinat) {
        this.esteVaccinat = esteVaccinat;
    }
}
