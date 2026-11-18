package ro.ase.ex5_zboruri;

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
    public static final String ADD_ZBOR = "add_zbor";

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

        String[] tipuriClase = {"Economic", "Business", "Vip"};

        Spinner spinner = findViewById(R.id.spinnerClasa);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                tipuriClase);

        EditText etDestinatie = findViewById(R.id.etDestinatie);
        EditText etPretZbor = findViewById(R.id.etPretZbor);
        EditText etData = findViewById(R.id.etData);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnAdauga = findViewById(R.id.btnAdauga);

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) verific sa nu fie goale spatiile
                if (etDestinatie.getText().toString().isEmpty())
                {
                    etDestinatie.setError("Introdu destinatia");
                    return;
                }
                else if (etData.getText().toString().isEmpty())
                {
                    etData.setError("Introdu data");
                    return;
                }
                else if (etPretZbor.getText().toString().isEmpty())
                {
                    etPretZbor.setError("Intordu pretul zborului");
                    return;
                }

                // b)
                String destinatie = etDestinatie.getText().toString();
                float pretZvor = Float.parseFloat(etPretZbor.getText().toString());
                String clasa = spinner.getSelectedItem().toString();
                boolean areBagajCala = radioGroup.getCheckedRadioButtonId() == R.id.rbDa;

                // c) verific data
                Date data;
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etData.getText().toString());
                }
                catch (Exception e)
                {
                    etData.setError("Formatul introdus nu este corect");
                    return;
                }

                // d) creez obiect si adaug in lista
                Zbor zbor = new Zbor(destinatie,data,pretZvor,clasa,areBagajCala);
                Intent intent = getIntent();
                intent.putExtra(ADD_ZBOR, intent);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}