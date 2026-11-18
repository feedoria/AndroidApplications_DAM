package ro.ase.ex_cabinet_veterinar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class AddActivity extends AppCompatActivity {
    public static final String ADD_ANIMAL = "add_animal";

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

        EditText etNume = findViewById(R.id.etNume);
        EditText etSpecie = findViewById(R.id.etRasa);
        EditText etData = findViewById(R.id.etData);
        EditText etGreutate = findViewById(R.id.etGreutate);
        CheckBox checkBox = findViewById(R.id.cbVaccinat);

        Button btnAdauga = findViewById(R.id.btnAdauga);

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) verific daca campurile sunt goale
                if (etNume.getText().toString().isEmpty())
                {
                    etNume.setError("introdu numele animalului");
                    return;
                }
                else if (etSpecie.getText().toString().isEmpty())
                {
                    etSpecie.setError("Introdu specia animalului");
                    return;
                }
                else if (etData.getText().toString().isEmpty())
                {
                    etData.setError("introdu data");
                    return;
                }
                else if (etGreutate.getText().toString().isEmpty())
                {
                    etGreutate.setError("introdu greutatea");
                    return;
                }

                // b) citesc datele
                String nume = etNume.getText().toString();
                String specie = etSpecie.getText().toString();
                float greutate = Float.parseFloat(etGreutate.getText().toString());
                boolean esteVaccinat = checkBox.isChecked();

                // c) verific data sa nu de acrash formatul
                Date data;
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etData.getText().toString());
                } catch (ParseException e) {
                    etData.setError("format gresit");
                    return;
                }
                // d) construiesc obiectul si il adaug in intnt cu putextra
                Animal animal = new Animal(nume,specie,data,greutate,esteVaccinat);
                Intent intent = getIntent();
                intent.putExtra(ADD_ANIMAL, animal);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}