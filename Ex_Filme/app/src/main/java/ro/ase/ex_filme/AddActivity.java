package ro.ase.ex_filme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
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
    public static final String ADD_FILM = "add_film";
    public static final String EDIT_FILM ="edit_film";

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

        String[] genuri = {"Actiune", "Horror", "Drama"};

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                genuri);

        spinner.setAdapter(adapter);

        EditText etTitlu = findViewById(R.id.etTitlu);
        EditText etRegizor = findViewById(R.id.etRegizor);
        EditText etData = findViewById(R.id.etData);
        EditText etDurata = findViewById(R.id.etDurata);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnAdauga = findViewById(R.id.btnAdauga);

        Intent intent = getIntent();
        intent.getSerializableExtra(EDIT_FILM);

        if(intent.hasExtra(EDIT_FILM))
        {
            Film f = (Film) intent.getSerializableExtra(EDIT_FILM);

            if (f!=null)
            {
                etTitlu.setText(f.getTitlu());
                etRegizor.setText(f.getRegizor());
                etData.setText(new SimpleDateFormat("MM/dd/yyyy", Locale.US).format(f.getDataLansarii()));
                etDurata.setText(String.valueOf(f.getDurata()));
                if (f.isVazut())
                {
                    radioGroup.check(R.id.rbDa);
                }
                else
                    radioGroup.check(R.id.rbNu);
            }
        }

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) verific daca campurile sunt goale si setez eroare
                if (etTitlu.getText().toString().isEmpty())
                {
                    etTitlu.setError("Introdu titlul");
                    return;
                }
                else if (etRegizor.getText().toString().isEmpty())
                {
                    etRegizor.setError("Introdu regizor");
                    return ;
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

                // b) citesc datele introduse in campuri
                String titlu = etTitlu.getText().toString();
                String regizor = etRegizor.getText().toString();
                int durata = Integer.parseInt(etDurata.getText().toString());
                String gen = spinner.getSelectedItem().toString();
                boolean vazut = radioGroup.getCheckedRadioButtonId() == R.id.rbDa;

                // c) verific data separat sa nu fie alt format sa nu dea crash
                Date data;
                try {
                    data =  new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etData.getText().toString());
                } catch (ParseException e) {
                    etData.setError("Formatul corect al datei: MM/dd/yyyy");
                    return;
                }
                // d) creez si adaug obiectu in lista cu putextra si resultok
                Film film = new Film(titlu,regizor,data,durata,gen,vazut);
                Intent intent = getIntent();
                intent.putExtra(ADD_FILM, film);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}