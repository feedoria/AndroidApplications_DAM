package ro.ase.semdam_1085;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class ExtractJSON extends AsyncTask<URL, Void, InputStream> {
    public List<Student> listaJSONStud;
    InputStream ist=null;
    @Override
    protected InputStream doInBackground(URL... urls) {
        HttpsURLConnection connection = null;
        try {
            //deschid conexiunea
            connection = (HttpsURLConnection) urls[0].openConnection();

            //aleg metoda de citire din site
            connection.setRequestMethod("GET");

            ist = connection.getInputStream();

            // aleg varianta 2 de la seminar pentru json nu se poate cu ist direct
            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String rezultat = "";
            String linie = null;
            while((linie = br.readLine())!=null)
            {
                rezultat += linie;
            }

            // parsez textul de pe site in json
            listaJSONStud = parseJSON(rezultat);
        }
        catch (Exception e)
        {
            Log.e("JSON", e.getMessage());
        }
        return null;
    }

    List<Student> parseJSON(String text)
    {
        List<Student> lista = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(text);

            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = new JSONObject();

                obj = array.getJSONObject(i);

                String nume = obj.getString("nume");
                float media = (float) obj.getDouble("medie");
                String facultate = obj.getString("facultate");
                boolean areBursa = obj.getBoolean("areBursa");
                Date data = new Date(obj.getString("dataNasterii"));

                lista.add(new Student(nume, data, media, facultate, areBursa));
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return lista;
    }
}
