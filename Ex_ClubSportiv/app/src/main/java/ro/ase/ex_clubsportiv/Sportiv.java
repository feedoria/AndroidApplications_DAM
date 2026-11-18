package ro.ase.ex_clubsportiv;

import java.io.Serializable;
import java.security.SecureRandomParameters;
import java.util.Date;

public class Sportiv implements Serializable {
    String numeSportiv;
    Date dataInscrierii;
    int varsta;
    String tipSport;
    boolean areOchelari;

    public Sportiv(String numeSportiv, Date dataInscrierii, int varsta, String tipSport, boolean areOchelari) {
        this.numeSportiv = numeSportiv;
        this.dataInscrierii = dataInscrierii;
        this.varsta = varsta;
        this.tipSport = tipSport;
        this.areOchelari = areOchelari;
    }

    public String getNumeSportiv() {
        return numeSportiv;
    }

    public void setNumeSportiv(String numeSportiv) {
        this.numeSportiv = numeSportiv;
    }

    public Date getDataInscrierii() {
        return dataInscrierii;
    }

    public void setDataInscrierii(Date dataInscrierii) {
        this.dataInscrierii = dataInscrierii;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getTipSport() {
        return tipSport;
    }

    public void setTipSport(String tipSport) {
        this.tipSport = tipSport;
    }

    public boolean isAreOchelari() {
        return areOchelari;
    }

    public void setAreOchelari(boolean areOchelari) {
        this.areOchelari = areOchelari;
    }

    @Override
    public String toString() {
        return "Sportiv{" +
                "numeSportiv='" + numeSportiv + '\'' +
                ", dataInscrierii=" + dataInscrierii +
                ", varsta=" + varsta +
                ", tipSport='" + tipSport + '\'' +
                ", areOchelari=" + areOchelari +
                '}';
    }
}
