package ro.ase.ex_agentieturism;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
    public static final String ADD_REZERVARE = "add_rezervare";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        setTitle("Adauga rezervare");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etDestinatie = findViewById(R.id.etDestinatie);
        EditText etData = findViewById(R.id.etDataRezervarii);
        EditText etPret = findViewById(R.id.etPret);
        CheckBox cbAreMicDejun = findViewById(R.id.cbAreMicDejun);

        Spinner spinner = findViewById(R.id.spinner);
        String[] tipRezervare = {"online", "fizic"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                tipRezervare);
        spinner.setAdapter(adapter);

        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) verific sa nu fie camourile goale
                if (etDestinatie.getText().toString().isEmpty())
                {
                    etDestinatie.setError("introdu destinatia");
                    return;
                }
                else if(etData.getText().toString().isEmpty())
                {
                    etData.setError("introdu data");
                    return;
                }
                else if (etPret.getText().toString().isEmpty())
                {
                    etPret.setError("introdu pretul");
                    return;
                }


                // b) citesc valorile
                String dest = etDestinatie.getText().toString();
                float pret = Float.parseFloat(etPret.getText().toString());
                String tipRezervare = spinner.getSelectedItem().toString();
                boolean areMicDejun = cbAreMicDejun.isChecked();

                if (pret < 0) {
                    etPret.setError("pretul nu poate fi negativ");
                    return;
                }

                // c) trtaez data separat
                Date data;
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etData.getText().toString());
                } catch (ParseException e) {
                    etData.setError("format incorect");
                    return;
                }

                // d) construiesv obiectul si il trimit in mainactivity cu putextra
                Rezervare rezervare = new Rezervare(dest, data, pret, tipRezervare, areMicDejun);
                Intent intent = getIntent();
                intent.putExtra(ADD_REZERVARE, rezervare);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}