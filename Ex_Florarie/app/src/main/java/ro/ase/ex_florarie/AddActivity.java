package ro.ase.ex_florarie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

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
    public static final String ADD_COMANDA = "add_comanda";
    public static final String EDIT_COMANDA = "edit_comanda";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        setTitle("Adauga comanda");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etNume = findViewById(R.id.etNume);
        EditText etData  = findViewById(R.id.etData);
        EditText etNrFire = findViewById(R.id.etNrFire);
        Switch switchAreFelicitare = findViewById(R.id.switchAreFelicitare);

        String[] tipBuchet = {"lalele", "trandafiri", "bujori"};
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                tipBuchet);
        spinner.setAdapter(adapter);

        Button btnAdaugaComanda = findViewById(R.id.btnAdaugaComanda);

        Intent intent = getIntent();
        intent.getSerializableExtra("listViz");
        if(intent.hasExtra(EDIT_COMANDA))
        {

        }

        btnAdaugaComanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verific sa nu fie campurile goale si setez eroare
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
                else if (etNrFire.getText().toString().isEmpty())
                {
                    etNrFire.setError("introdu numarul firelor");
                    return;
                }

                // citesc datele si validari
                String nume = etNume.getText().toString();
                int nrFire = Integer.parseInt(etNrFire.getText().toString());
                String tipBuchet = spinner.getSelectedItem().toString();
                boolean areFelicitare = switchAreFelicitare.isActivated();

                if (nrFire < 1 || nrFire > 101)
                    etNrFire.setError("Nr minim 1 nr maxim 101");

                // tratez data separat
                Date data;
                try {
                    data = new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(etData.getText().toString());
                } catch (ParseException e) {
                    etData.setError("formatul datei este gresit");
                    return;
                }

                if (data.before(new Date()))
                    etData.setError("Nu puteti comanda pe o data din trecut");
                // construiesc obiectul si l trimit catre main cu put extra
                Comanda comanda = new Comanda(nume,data,nrFire,tipBuchet,areFelicitare);
                Intent intent = getIntent();
                intent.putExtra(ADD_COMANDA, comanda );
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}