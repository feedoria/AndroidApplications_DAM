package ro.ase.sem2_remake_exbiblioteca;

import java.util.Date;

public class Carte {
    private String titlu;
    private Date dataPublicarii;
    private float pret;
    private String gen;
    private boolean disponibila;

    public Carte(String titlu, Date dataPublicarii, float pret, String gen, boolean disponibila) {
        this.titlu = titlu;
        this.dataPublicarii = dataPublicarii;
        this.pret = pret;
        this.gen = gen;
        this.disponibila = disponibila;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public Date getDataPublicarii() {
        return dataPublicarii;
    }

    public void setDataPublicarii(Date dataPublicarii) {
        this.dataPublicarii = dataPublicarii;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public boolean isDisponibila() {
        return disponibila;
    }

    public void setDisponibila(boolean disponibila) {
        this.disponibila = disponibila;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "titlu='" + titlu + '\'' +
                ", dataPublicarii=" + dataPublicarii +
                ", pret=" + pret +
                ", gen='" + gen + '\'' +
                ", disponibila=" + disponibila +
                '}';
    }
}
