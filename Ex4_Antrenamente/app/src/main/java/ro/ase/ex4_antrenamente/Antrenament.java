package ro.ase.ex4_antrenamente;

import java.io.Serializable;
import java.util.Date;

public class Antrenament implements Serializable {

    String numeExercitiu;
    Date dataAntrenament;
    int durata;
    String tipAntrenament;
    boolean cuAntrenor;

    public Antrenament(String numeExercitiu, Date dataAntrenament, int durata, String tipAntrenament, boolean cuAntrenor) {
        this.numeExercitiu = numeExercitiu;
        this.dataAntrenament = dataAntrenament;
        this.durata = durata;
        this.tipAntrenament = tipAntrenament;
        this.cuAntrenor = cuAntrenor;
    }

    public String getNumeExercitiu() {
        return numeExercitiu;
    }

    public void setNumeExercitiu(String numeExercitiu) {
        this.numeExercitiu = numeExercitiu;
    }

    public Date getDataAntrenament() {
        return dataAntrenament;
    }

    public void setDataAntrenament(Date dataAntrenament) {
        this.dataAntrenament = dataAntrenament;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getTipAntrenament() {
        return tipAntrenament;
    }

    public void setTipAntrenament(String tipAntrenament) {
        this.tipAntrenament = tipAntrenament;
    }

    public boolean isCuAntrenor() {
        return cuAntrenor;
    }

    public void setCuAntrenor(boolean cuAntrenor) {
        this.cuAntrenor = cuAntrenor;
    }

    @Override
    public String toString() {
        return "Antrenament{" +
                "numeExercitiu='" + numeExercitiu + '\'' +
                ", dataAntrenament=" + dataAntrenament +
                ", durata=" + durata +
                ", tipAntrenament='" + tipAntrenament + '\'' +
                ", cuAntrenor=" + cuAntrenor +
                '}';
    }
}
