package ro.ase.semdam_1085;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ExtractJSON extends AsyncTask<URL,Void, InputStream> {
    InputStream ist = null;
    public List<Student> listaJSONStudenti;
    @Override
    protected InputStream doInBackground(URL... urls) {
        try {
            // deschid conexiunea
            HttpsURLConnection connection = (HttpsURLConnection) urls[0].openConnection();

            // setez metoda -> citire
            connection.setRequestMethod("GET");

            // iau datele
            ist = connection.getInputStream();

            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String linie = null;
            String rezultat = "";
            while((linie = br.readLine())!=null)
            {
                rezultat += linie;
            }


            listaJSONStudenti = parseazaJSON(rezultat); // dau textul catre parsare
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Student> parseazaJSON(String text) {
        List<Student> listaStud = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(text);

            for (int i = 0 ;i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                String nume = obj.getString("nume");
                float medie = (float) obj.getDouble("medie");
                String facultate = obj.getString("facultate");
                boolean areBursa = obj.getBoolean("areBursa");
                Date data = new Date(obj.getString("dataNasterii"));

                listaStud.add(new Student(nume,data,medie,facultate,areBursa));
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return listaStud;
    }
}
