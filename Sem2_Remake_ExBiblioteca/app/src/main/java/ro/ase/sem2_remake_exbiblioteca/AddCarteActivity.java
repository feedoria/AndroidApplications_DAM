package ro.ase.sem2_remake_exbiblioteca;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class AddCarteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_carte);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. DATELE
        String[] genuri = {"Roman", "Poezie", "SF", "Istorie"};

        // 2. ADAPTERUL: (context, cum arată un rând, datele)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                genuri);

        // 3. LEGĂTURA cu Spinner-ul din XML
        Spinner spinnerGen = findViewById(R.id.spinnerGen);
        spinnerGen.setAdapter(adapter);

        // EditText-urile (le pregătim pentru seminarul următor)
        EditText etTitlu = findViewById(R.id.etTitlu);
        EditText etData = findViewById(R.id.etData);
        EditText etPret = findViewById(R.id.etPret);

    }


}