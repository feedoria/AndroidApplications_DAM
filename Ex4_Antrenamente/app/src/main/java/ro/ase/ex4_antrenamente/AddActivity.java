package ro.ase.ex4_antrenamente;

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
    public static final String ADD_ANTRENAMENT = "add_antrenament";
;
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
        Intent intent = getIntent();

        String[] tipuriExercitiu = {"Yoga", "Forta", "Cardio"};

        Spinner spinner = findViewById(R.id.spinnerTip);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                tipuriExercitiu
        );

        spinner.setAdapter(adapter);

        EditText etNumeEx = findViewById(R.id.etNumeExercitiu);
        EditText etData = findViewById(R.id.etData);
        EditText etDurata = findViewById(R.id.etDurata);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) verific daca s goale campurile
                if (etNumeEx.getText().toString().isEmpty())
                {
                    etNumeEx.setError("Introdu numele exercitiului");
                    return;
                }
                else if (etData.getText().toString().isEmpty())
                {
                    etData.setError("Introdu data");
                    return;
                }
                else if (etDurata.getText().toString().isEmpty())
                {
                    etDurata.setError("Introdu durata");
                    return;
                }

                // b) citesc datele
                String numeEx = etNumeEx.getText().toString();
                int durata = Integer.parseInt(etDurata.getText().toString());
                String tipEx = spinner.getSelectedItem().toString();
                boolean areAntrenor = radioGroup.getCheckedRadioButtonId() == R.id.rbNu;

                Date data;
                // c) daca formatul datei e diferit nu da crash
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etData.getText().toString());
                }
                catch (Exception e)
                {
                    etData.setError("Format corect \"MM/dd/yyyy\"");
                    return;
                }

                // d) formez obiectul si il pun in intent cu putextra
                Antrenament antrenament = new Antrenament(numeEx,data,durata,tipEx,areAntrenor);
                Intent intent = getIntent();
                intent.putExtra(ADD_ANTRENAMENT, antrenament);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}