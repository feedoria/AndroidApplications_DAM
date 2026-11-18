package ro.ase.ex5_zboruri;

import java.io.Serializable;
import java.util.Date;

public class Zbor implements Serializable {
    String destinatia;
    Date dataZborului;
    float pretBilet;
    String clasa;
    boolean areBagajCala;

    public Zbor(String destinatia, Date dataZborului, float pretBilet, String clasa, boolean areBagajCala) {
        this.destinatia = destinatia;
        this.dataZborului = dataZborului;
        this.pretBilet = pretBilet;
        this.clasa = clasa;
        this.areBagajCala = areBagajCala;
    }

    public String getDestinatia() {
        return destinatia;
    }

    public void setDestinatia(String destinatia) {
        this.destinatia = destinatia;
    }

    public Date getDataZborului() {
        return dataZborului;
    }

    public void setDataZborului(Date dataZborului) {
        this.dataZborului = dataZborului;
    }

    public float getPretBilet() {
        return pretBilet;
    }

    public void setPretBilet(float pretBilet) {
        this.pretBilet = pretBilet;
    }

    public String getClasa() {
        return clasa;
    }

    public void setClasa(String clasa) {
        this.clasa = clasa;
    }

    public boolean isAreBagajCala() {
        return areBagajCala;
    }

    public void setAreBagajCala(boolean areBagajCala) {
        this.areBagajCala = areBagajCala;
    }

    @Override
    public String toString() {
        return "Zbor{" +
                "destinatia='" + destinatia + '\'' +
                ", dataZborului=" + dataZborului +
                ", pretBilet=" + pretBilet +
                ", clasa='" + clasa + '\'' +
                ", areBagajCala=" + areBagajCala +
                '}';
    }
}
