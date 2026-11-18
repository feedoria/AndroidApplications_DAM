package ro.ase.ex_agentieturism;

import java.io.Serializable;
import java.util.Date;

public class Rezervare implements Serializable {
    String destinatie;
    Date dataRezervarii;
    float pretRezervare;
    String tipRezervare;
    boolean areMicDejun;

    public Rezervare(String destinatie, Date dataRezervarii, float pretRezervare, String tipRezervare, boolean areMicDejun) {
        this.destinatie = destinatie;
        this.dataRezervarii = dataRezervarii;
        this.pretRezervare = pretRezervare;
        this.tipRezervare = tipRezervare;
        this.areMicDejun = areMicDejun;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public Date getDataRezervarii() {
        return dataRezervarii;
    }

    public void setDataRezervarii(Date dataRezervarii) {
        this.dataRezervarii = dataRezervarii;
    }

    public float getPretRezervare() {
        return pretRezervare;
    }

    public void setPretRezervare(float pretRezervare) {
        this.pretRezervare = pretRezervare;
    }

    public String getTipRezervare() {
        return tipRezervare;
    }

    public void setTipRezervare(String tipRezervare) {
        this.tipRezervare = tipRezervare;
    }

    public boolean isAreMicDejun() {
        return areMicDejun;
    }

    public void setAreMicDejun(boolean areMicDejun) {
        this.areMicDejun = areMicDejun;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "destinatie='" + destinatie + '\'' +
                ", dataRezervarii=" + dataRezervarii +
                ", pretRezervare=" + pretRezervare +
                ", tipRezervare='" + tipRezervare + '\'' +
                ", areMicDejun=" + areMicDejun +
                '}';
    }
}
