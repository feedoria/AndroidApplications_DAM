package ro.ase.ex_florarie;

import java.io.Serializable;
import java.util.Date;

public class Comanda implements Serializable {
    String numeClient;
    Date dataLivrarii;
    int nrFire;
    String tipBuchet;
    boolean areFelicitare;

    public Comanda(String numeClient, Date dataLivrarii, int nrFire, String tipBuchet, boolean areFelicitare) {
        this.numeClient = numeClient;
        this.dataLivrarii = dataLivrarii;
        this.nrFire = nrFire;
        this.tipBuchet = tipBuchet;
        this.areFelicitare = areFelicitare;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "numeClient='" + numeClient + '\'' +
                ", dataLivrarii=" + dataLivrarii +
                ", nrFire=" + nrFire +
                ", tipBuchet='" + tipBuchet + '\'' +
                ", areFelicitare=" + areFelicitare +
                '}';
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public Date getDataLivrarii() {
        return dataLivrarii;
    }

    public void setDataLivrarii(Date dataLivrarii) {
        this.dataLivrarii = dataLivrarii;
    }

    public int getNrFire() {
        return nrFire;
    }

    public void setNrFire(int nrFire) {
        this.nrFire = nrFire;
    }

    public String getTipBuchet() {
        return tipBuchet;
    }

    public void setTipBuchet(String tipBuchet) {
        this.tipBuchet = tipBuchet;
    }

    public boolean isAreFelicitare() {
        return areFelicitare;
    }

    public void setAreFelicitare(boolean areFelicitare) {
        this.areFelicitare = areFelicitare;
    }
}
