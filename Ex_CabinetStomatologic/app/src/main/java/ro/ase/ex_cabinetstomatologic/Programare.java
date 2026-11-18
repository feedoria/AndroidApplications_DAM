package ro.ase.ex_cabinetstomatologic;

import java.io.Serializable;
import java.util.Date;

public class Programare implements Serializable {
    String numePacient;
    Date dataProgramarii;
    float pret;
    String tipInterventie;
    boolean esteUrgenta;

    public Programare(String numePacient, Date dataProgramarii, float pret, String tipInterventie, boolean esteUrgenta) {
        this.numePacient = numePacient;
        this.dataProgramarii = dataProgramarii;
        this.pret = pret;
        this.tipInterventie = tipInterventie;
        this.esteUrgenta = esteUrgenta;
    }

    @Override
    public String toString() {
        return "Programare{" +
                "numePacient='" + numePacient + '\'' +
                ", dataProgramarii=" + dataProgramarii +
                ", pret=" + pret +
                ", tipInterventie='" + tipInterventie + '\'' +
                ", esteUrgenta=" + esteUrgenta +
                '}';
    }

    public String getNumePacient() {
        return numePacient;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    public Date getDataProgramarii() {
        return dataProgramarii;
    }

    public void setDataProgramarii(Date dataProgramarii) {
        this.dataProgramarii = dataProgramarii;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getTipInterventie() {
        return tipInterventie;
    }

    public void setTipInterventie(String tipInterventie) {
        this.tipInterventie = tipInterventie;
    }

    public boolean isEsteUrgenta() {
        return esteUrgenta;
    }

    public void setEsteUrgenta(boolean esteUrgenta) {
        this.esteUrgenta = esteUrgenta;
    }
}
