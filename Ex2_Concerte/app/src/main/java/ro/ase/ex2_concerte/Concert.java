package ro.ase.ex2_concerte;

import java.io.Serializable;
import java.util.Date;

public class Concert implements Serializable {
    String artist;
    Date dataConcertului;
    int nrBileteCumparate;
    String genMuzical;
    boolean soldOut;

    public Concert(String artist, Date dataConcertului, int nrBileteCumparate, String genMuzical, boolean soldOut) {
        this.artist = artist;
        this.dataConcertului = dataConcertului;
        this.nrBileteCumparate = nrBileteCumparate;
        this.genMuzical = genMuzical;
        this.soldOut = soldOut;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Date getDataConcertului() {
        return dataConcertului;
    }

    public void setDataConcertului(Date dataConcertului) {
        this.dataConcertului = dataConcertului;
    }

    public int getNrBileteCumparate() {
        return nrBileteCumparate;
    }

    public void setNrBileteCumparate(int nrBileteCumparate) {
        this.nrBileteCumparate = nrBileteCumparate;
    }

    public String getGenMuzical() {
        return genMuzical;
    }

    public void setGenMuzical(String genMuzical) {
        this.genMuzical = genMuzical;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "artist='" + artist + '\'' +
                ", dataConcertului=" + dataConcertului +
                ", nrBileteCumparate=" + nrBileteCumparate +
                ", genMuzical='" + genMuzical + '\'' +
                ", soldOut=" + soldOut +
                '}';
    }
}
