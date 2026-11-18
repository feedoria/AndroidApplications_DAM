package ro.ase.ex_clubsportiv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    public static final String ADD_SPORTIV = "add_sportiv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        setTitle("Adauga inregistrare");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNume = findViewById(R.id.etNume);
        EditText etData = findViewById(R.id.etDataInscrierii);
        EditText etVarsta = findViewById(R.id.etVarsta);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        Spinner spinner = findViewById(R.id.spinner);

        String[] tipuriSport = {"volei", "baschet", "inot"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                tipuriSport);

        spinner.setAdapter(adapter);

        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) verific daca sunt campurile goale si setez eroare
                if (etNume.getText().toString().isEmpty())
                {
                    etNume.setError("introdu numele");
                    return;
                }
                else if (etData.getText().toString().isEmpty())
                {
                    etData.setError("Introdu data");
                    return;
                }
                else if (etVarsta.getText().toString().isEmpty())
                {
                    etVarsta.setError("introdu varsta");
                    return;
                }

                // b) citesc datele
                String nume = etNume.getText().toString();
                int varsta = Integer.parseInt(etVarsta.getText().toString());
                String tipSport = spinner.getSelectedItem().toString();
                boolean areOchelari = radioGroup.getCheckedRadioButtonId() == R.id.rbDa;

                // c) tratez data separat
                Date data;
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etData.getText().toString());
                } catch (ParseException e) {
                    etData.setError("formatul datei este gresit");
                    return;
                }
                // d) creez obiectul si il trimit catre main activity cu putextra
                Sportiv sportiv = new Sportiv(nume,data,varsta,tipSport,areOchelari);
                Intent intent = getIntent();
                intent.putExtra(ADD_SPORTIV, sportiv);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}