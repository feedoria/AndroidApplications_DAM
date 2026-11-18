package ro.ase.ex_biblioteci;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ArrayList<Biblioteca> listaCarti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setTitle("Proba practica 1");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvDataCurenta = findViewById(R.id.tvDataCurenta);
        tvDataCurenta.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(new Date()));

        EditText etTitlu = findViewById(R.id.etTitlu);
        EditText etAutor = findViewById(R.id.etAutor);
        EditText etData = findViewById(R.id.etData);
        EditText etNrPagini = findViewById(R.id.etNrPagini);

        String[] genuri = {"Horror", "Romance", "Actiune"};
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                genuri);

        spinner.setAdapter(adapter);
        Button btnAdauga = findViewById(R.id.btnAdauga);
        Button btnVizualizeaza = findViewById(R.id.btnVizualizeaza);

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) verific daca campurile sunt goale
                if (etTitlu.getText().toString().isEmpty())
                {
                    etTitlu.setError("introdu titlul");
                    return;
                }
                else if (etAutor.getText().toString().isEmpty())
                {
                    etAutor.setError("introdu autorul. ");
                    return;
                }
                else if (etData.getText().toString().isEmpty())
                {
                    etData.setError("introdu data");
                    return;
                }
                else if (etNrPagini.getText().toString().isEmpty())
                {
                    etNrPagini.setError("introdu numarul de pagini");
                    return;
                }

                // b) citesc datele
                String titlu = etTitlu.getText().toString();
                String autor = etAutor.getText().toString();
                int nrPagini = Integer.parseInt(etNrPagini.getText().toString());
                String gen = spinner.getSelectedItem().toString();
                // c) verific data sa nu dea crash cu alt format
                Date data;
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etData.getText().toString());
                } catch (ParseException e) {
                    etData.setError("formatul datei e gresit");
                    return;
                }
                // d) fac obiectul il adaug in lista si golesc campurile
                Biblioteca carte = new Biblioteca(titlu,autor,data,nrPagini,gen);
                listaCarti.add(carte);
                etData.setText("");
                etTitlu.setText("");
                etAutor.setText("");
                etNrPagini.setText("");
            }
        });

        btnVizualizeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AfiseazaActivity.class);
                intent.putExtra("lista", listaCarti);
                startActivity(intent);
            }
        });
    }
}