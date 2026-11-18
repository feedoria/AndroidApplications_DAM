package ro.ase.ex1_masini;

import java.io.Serializable;
import java.util.Date;

public class Masina implements Serializable {
    private String marca;
    private Date dataFabricatiei;
    private float pret;
    private String combustibil;
    private boolean esteNoua;

    public Masina(String marca, Date dataFabricatiei, float pret, String combustibil, boolean esteNoua) {
        this.marca = marca;
        this.dataFabricatiei = dataFabricatiei;
        this.pret = pret;
        this.combustibil = combustibil;
        this.esteNoua = esteNoua;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getDataFabricatiei() {
        return dataFabricatiei;
    }

    public void setDataFabricatiei(Date dataFabricatiei) {
        this.dataFabricatiei = dataFabricatiei;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getCombustibil() {
        return combustibil;
    }

    public void setCombustibil(String combustibil) {
        this.combustibil = combustibil;
    }

    public boolean isEsteNoua() {
        return esteNoua;
    }

    public void setEsteNoua(boolean esteNoua) {
        this.esteNoua = esteNoua;
    }

    @Override
    public String toString() {
        return "Masina{" +
                "marca='" + marca + '\'' +
                ", dataFabricatiei=" + dataFabricatiei +
                ", pret=" + pret +
                ", combustibil='" + combustibil + '\'' +
                ", esteNoua=" + esteNoua +
                '}';
    }
}
