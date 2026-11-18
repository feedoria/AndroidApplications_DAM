package ro.ase.ex3_rezervari;

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
    public static final String ADD_REZERVARE = "add_rezervare";

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

        String[] zone = {"Interior", "Terasa", "Bar"};
        Spinner spinner = findViewById(R.id.spinnerZona);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                zone);
        spinner.setAdapter(adapter);

        EditText etNumeClient = findViewById(R.id.etNumeClient);
        EditText etData = findViewById(R.id.etData);
        EditText etNrPersoane = findViewById(R.id.etNrPersoane);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnAdauga = findViewById(R.id.btnAdauga);

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) verific daca sunt campurile goale si dau eroare
                if (etNumeClient.getText().toString().isEmpty())
                {
                    etNumeClient.setError("Introduceti numele clientului");
                    return;
                }
                else if (etData.getText().toString().isEmpty())
                {
                    etData.setError("Introduceti data");
                    return;
                }
                else if (etNrPersoane.getText().toString().isEmpty())
                {
                    etNrPersoane.setError("Introduceti numarul de persoane");
                    return;
                }

                // b) citim datele din campuri
                String numeClient = etNumeClient.getText().toString();
                int numarPers = Integer.parseInt(etNrPersoane.getText().toString());
                String zona = spinner.getSelectedItem().toString();
                boolean areCopii = radioGroup.getCheckedRadioButtonId() == R.id.rbDa;

                // c) am grija sa nu crape la formatul datei
                Date data;
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etData.getText().toString());
                }
                catch (Exception e)
                {
                    etData.setError("Introdu formatul corect");
                    return;
                }

                // d) construim obiectul
                Rezervare rezervare = new Rezervare(numeClient, data, numarPers, zona, areCopii);
                Intent intent = getIntent();
                intent.putExtra(ADD_REZERVARE, rezervare);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}