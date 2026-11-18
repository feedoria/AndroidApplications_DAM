package ro.ase.ex1_masini;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    public static final String ADD_MASINA = "add_masina";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. datele
        String[] combustibili = {"Benzina", "Motorina", "Gaz", "Hibrid", "Electric"};

        // 2. adapterul
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                combustibili);

        // 3. legatura
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        EditText etMarca = findViewById(R.id.etMarca);
        EditText etDate = findViewById(R.id.etDate);
        EditText etPret = findViewById(R.id.etPret);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnAdauga = findViewById(R.id.btnAdauga);

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) daca un camp e gol -> pun eroare
                if(etMarca.getText().toString().isEmpty())
                {
                    etMarca.setError("Introdu marca");
                    return;
                }
                else if (etDate.getText().toString().isEmpty())
                {
                    etDate.setError("Introdu data!");
                    return;
                }
                else if (etPret.getText().toString().isEmpty())
                {
                    etPret.setError("Introdu pretul!");
                    return;
                }

                // b) citesc valorile
                String marca = etMarca.getText().toString();
                float pret = Float.parseFloat(etPret.getText().toString());
                String combustibil = spinner.getSelectedItem().toString();
                boolean esteNoua = radioGroup.getCheckedRadioButtonId() == R.id.rbDa;

                Date data;
                // c) daca data este gresita: fara crash -> arat eroare
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US)
                            .parse(etDate.getText().toString());
                }
                catch (Exception e)
                {
                    etDate.setError("Format corect: \"MM/dd/yyyy\"");
                    e.getMessage();
                    return;
                }

                // d) construiesc masina si o trimit inapoi
                Masina masina = new Masina(marca, data, pret, combustibil, esteNoua);
                Intent intent = getIntent();
                intent.putExtra(ADD_MASINA, masina);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}