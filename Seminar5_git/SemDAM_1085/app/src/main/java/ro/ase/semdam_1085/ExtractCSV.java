package ro.ase.semdam_1085;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ExtractCSV extends AsyncTask<URL, Void, String> {
    public List<Student> studentListCSV = new ArrayList<>();
    @Override
    protected String doInBackground(URL... urls) {
        HttpsURLConnection connection=null;
        try {
            connection = (HttpsURLConnection) urls[0].openConnection();
            connection.setRequestMethod("GET");
            InputStream ist = connection.getInputStream();

            // 1) varianta 1: cand se parseaza -> parsare xml


            // 2) varianta 2: conversie stream
            // (se poate prelucra o singura data)
            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String linie = null;
            String rezultat = "";
            while((linie = br.readLine())!=null)
            {
                rezultat += linie + "\n";
            }
            //parsare JSON
            parsareCSV(rezultat);
            return rezultat;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void parsareCSV(String text)
    {
        String[] linii = text.split("\n");

        for (int i = 0; i < linii.length; i++)
        {
            try{
                String[] campuri = linii[i].split(";");

                String nume = campuri[0].trim();
                Date data = new Date(campuri[1].trim());
                float medie = Float.parseFloat(campuri[2].trim());
                String facultate = campuri[3].trim();
                boolean areBursa = Boolean.parseBoolean(campuri[4].trim());

                studentListCSV.add(new Student(nume, data, medie, facultate, areBursa));
            }
            catch (Exception e)
            {
                Log.e("CSV", "rand gresit");
            }
        }
    }

}

