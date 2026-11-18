package ro.ase.ex_cabinetstomatologic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

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
    public static final String ADD_PROGRAMARE = "add_programare";
    public static final String EDIT_PROGRAMARE = "edit_programare";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        setTitle("Adauga programare");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNume = findViewById(R.id.etNume);
        EditText etData = findViewById(R.id.etData);
        EditText etPret = findViewById(R.id.etPret);
        Button btnAdauga = findViewById(R.id.btnAdauga);
        String[] tipuriInterventii = {"plomba", "extractie"};
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                tipuriInterventii);

        spinner.setAdapter(adapter);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        Intent intent = getIntent();
        Programare programare = (Programare) intent.getSerializableExtra("edit");

        if (intent.hasExtra("edit"))
        {
            if (programare!=null)
            {
                etNume.setText(programare.getNumePacient());
                etData.setText(new SimpleDateFormat("MM/dd/yyyy", Locale.US).format(programare.getDataProgramarii()));
                etPret.setText(String.valueOf(programare.getPret()));
                spinner.setSelection(adapter.getPosition(programare.getTipInterventie()));
                if (programare.isEsteUrgenta())
                {
                    radioGroup.check(R.id.rbDa);
                }
                else
                {
                    radioGroup.check(R.id.rbNu);
                }
            }
        }

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) verific sa nu fie campuri goale
                if (etNume.getText().toString().isEmpty())
                {
                    etNume.setError("introdu numele");
                    return;
                }
                else if (etData.getText().toString().isEmpty())
                {
                    etData.setError("introdu data");
                    return;
                }
                else if (etPret.getText().toString().isEmpty())
                {
                    etPret.setError("intordu pretul");
                    return;
                }

                // b) citesc
                String nume = etNume.getText().toString();
                float pret = Float.parseFloat(etPret.getText().toString());
                String tipInterventie = spinner.getSelectedItem().toString();
                boolean esteUrgent = radioGroup.getCheckedRadioButtonId() == R.id.rbDa;
                // c) validez
                Date data;
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etData.getText().toString());
                } catch (ParseException e) {
                    etData.setError("format incorect");
                    return;
                }

                if (pret <= 0)
                    etPret.setError("pret negativ");

                if (data.before(new Date()))
                    etData.setError("nu poti face programare in trecut ba");
                // d) fac obiectul si l pun cu putextra
                Programare programare = new Programare(nume,data,pret,tipInterventie,esteUrgent);
                Intent intent = getIntent();
                intent.putExtra(ADD_PROGRAMARE, programare);
                setResult(RESULT_OK, intent);

                finish();
            }
        });
    }
}