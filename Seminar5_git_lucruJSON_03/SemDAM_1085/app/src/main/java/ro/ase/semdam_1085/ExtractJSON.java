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

public class ExtractJSON extends AsyncTask<URL, Void, InputStream> {
    public List<Student> listaJSONStud;
    InputStream ist=null;

    @Override
    protected InputStream doInBackground(URL... urls) {
        HttpsURLConnection connection = null;
        try {
            connection = (HttpsURLConnection) urls[0].openConnection();

            connection.setRequestMethod("GET");

            ist = connection.getInputStream();

            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String rezultat = "";
            String linie = null;
            while ((linie = br.readLine())!=null)
            {
                rezultat += linie;
            }

            listaJSONStud = parseazaJSON(rezultat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    List<Student> parseazaJSON(String text)
    {
        List<Student> lista = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(text);

            for (int i =0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);

                String nume = obj.getString("nume");
                Date data = new Date(obj.getString("data"));
                float medie = (float) obj.getDouble("medie");
                String facultate = obj.getString("facultate");
                boolean areBursa = obj.getBoolean("areBursa");

                lista.add(new Student(nume, data, medie,facultate, areBursa));
            }
        }
        catch (Exception e)
        {
            Log.e("json", "nu s a parsar json");
        }
        return lista;
    }
}
