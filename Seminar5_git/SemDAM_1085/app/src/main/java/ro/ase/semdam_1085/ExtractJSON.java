package ro.ase.semdam_1085;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ExtractJSON extends AsyncTask<URL, Void, String> {
    public List<Student> studentListJSON = new ArrayList<>();
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
                rezultat += linie;
            }
            //parsare JSON
            parsareJSON(rezultat);
            return rezultat;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void parsareJSON(String jsonStr)
    {
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray studenti = jsonObject.getJSONArray("studenti");

            for (int i = 0; i < studenti.length(); i++)
            {
                JSONObject obj = studenti.getJSONObject(i);

                String nume = obj.getString("Nume");
                Date data = new Date(obj.getString("DataNasterii"));
                float medie = (float) obj.getDouble("Medie");
                String facultater = obj.getString("Facultate");
                boolean areBursa = Boolean.parseBoolean(obj.getString("AreBursa"));

                studentListJSON.add(new Student(nume,data,medie,facultater,areBursa));
            }
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }

    // ExtractJSON.java
    List<Student> parseazaJSON(String text) {
        List<Student> lista = new ArrayList<>();
        try {
            JSONObject radacina = new JSONObject(text);                  // 1. obiectul { ... }
            JSONArray array = radacina.getJSONArray("studenti");         //    lista din el

            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                String nume = obj.getString("Nume");                     // 2. cheile cu literă mare
                Date data = new Date(obj.getString("DataNasterii"));
                float medie = Float.parseFloat(obj.getString("Medie"));  // 3. text → float
                String facultate = obj.getString("Facultate");
                boolean areBursa = Boolean.parseBoolean(obj.getString("AreBursa"));  // text → boolean

                lista.add(new Student(nume, data, medie, facultate, areBursa));
            }
        } catch (Exception e) {
            Log.e("JSON", e.getMessage());
        }
        return lista;
    }

}

//
//// ExtractCSV.java
//public void parsareCSV(String text) {
//    String[] linii = text.split("\n");
//    for (int i = 1; i < linii.length; i++) {           // sar peste antet
//        try {
//            String[] campuri = linii[i].split(",");
//            String nume = campuri[0].trim();
//            Date data = new Date(campuri[1].trim());
//            float medie = Float.parseFloat(campuri[2].trim());
//            String facultate = campuri[3].trim();
//            boolean areBursa = Boolean.parseBoolean(campuri[4].trim());
//            studentListCSV.add(new Student(nume, data, medie, facultate, areBursa));
//        } catch (Exception e) {
//            Log.e("CSV", "Rand gresit: " + linii[i]);
//        }
//    }
//}
