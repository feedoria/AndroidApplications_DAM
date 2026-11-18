package ro.ase.ex2_concerte;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    public static final String ADD_CONCERT = "add_concert";

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

        String[] genuri = {"Rock", "Jazz", "Techno"};

        Spinner spinner = findViewById(R.id.spinnerGen);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                genuri);
        spinner.setAdapter(adapter);

        EditText etArtist = findViewById(R.id.etArtist);
        EditText etData = findViewById(R.id.etData);
        EditText etNrBileteCumparate = findViewById(R.id.etNrBileteCumparate);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a) daca un camp e gol eroare
                if (etArtist.getText().toString().isEmpty())
                {
                    etArtist.setError("Introduceti numele artistului.");
                    return;
                }
                else if (etData.getText().toString().isEmpty())
                {
                    etData.setError("Introduceti data concertului.");
                    return;
                }
                else if (etNrBileteCumparate.getText().toString().isEmpty())
                {
                    etNrBileteCumparate.setError("Introduceti numarul biletelor cumparate.");
                    return;
                }

                // b) citesc datele
                String artist = etArtist.getText().toString();
                int nrBilete = Integer.parseInt(etNrBileteCumparate.getText().toString());
                String gen = spinner.getSelectedItem().toString();
                boolean esteSoldOut = radioGroup.getCheckedRadioButtonId() == R.id.rbNu;

                // c) daca data are format gresit -> nu crash, afisare eroare
                Date data;
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US)
                            .parse(etData.getText().toString());
                }
                catch (Exception e)
                {
                    etData.setError("Data ar trebui sa aiba formatul: \"MM/dd/yyyy\"");
                    return;
                }

                // d) construim obiectul
                Concert concert = new Concert(artist,data,nrBilete,gen,esteSoldOut);
                Intent intent = getIntent();
                intent.putExtra(ADD_CONCERT, concert);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}