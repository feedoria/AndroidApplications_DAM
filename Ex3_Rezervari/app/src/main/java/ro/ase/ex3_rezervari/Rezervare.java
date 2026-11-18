package ro.ase.ex3_rezervari;

import java.io.Serializable;
import java.util.Date;

public class Rezervare implements Serializable {
    String numeClient;
    Date dataRezervarii;
    int nrPersoane;
    String zona;
    boolean areCopii;

    public Rezervare(String numeClient, Date dataRezervarii, int nrPersoane, String zona, boolean areCopii) {
        this.numeClient = numeClient;
        this.dataRezervarii = dataRezervarii;
        this.nrPersoane = nrPersoane;
        this.zona = zona;
        this.areCopii = areCopii;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public Date getDataRezervarii() {
        return dataRezervarii;
    }

    public void setDataRezervarii(Date dataRezervarii) {
        this.dataRezervarii = dataRezervarii;
    }

    public int getNrPersoane() {
        return nrPersoane;
    }

    public void setNrPersoane(int nrPersoane) {
        this.nrPersoane = nrPersoane;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public boolean isAreCopii() {
        return areCopii;
    }

    public void setAreCopii(boolean areCopii) {
        this.areCopii = areCopii;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "numeClient='" + numeClient + '\'' +
                ", dataRezervarii=" + dataRezervarii +
                ", nrPersoane=" + nrPersoane +
                ", zona='" + zona + '\'' +
                ", areCopii=" + areCopii +
                '}';
    }
}
