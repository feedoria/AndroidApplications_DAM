package ro.ase.semdam_1085;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class BNRActivity extends AppCompatActivity {

    ArrayList<CursValutar> listaCursuri = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bnr);

        Log.e("lifecycle", "Apel metoda onCreate");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // EXTRAGEM referintele celorlalte controale
        TextView tvDate = findViewById(R.id.tvDate);
        EditText etUSD = findViewById(R.id.editTextUSD);
        EditText etEUR = findViewById(R.id.editTextEUR);
        EditText etGBP = findViewById(R.id.editTextGBP);
        EditText etXAU = findViewById(R.id.editTextXAU);

        Button btnShow = findViewById(R.id.btnShow);
        // similar cum am facut data trecuta inca o activitate de afisare cursuri valutare dar folosind un
        // custom adapter deci mai multe text view uri
        // cum era in add activity se ducea in activitatea a treia
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CursValutar curs = new CursValutar(
                        tvDate.getText().toString(),
                        etEUR.getText().toString(),
                        etUSD.getText().toString(),
                        etGBP.getText().toString(),
                        etXAU.getText().toString());

                listaCursuri.add(curs);
                Intent intent = new Intent(BNRActivity.this, AfisareCurs_Activity.class);
                intent.putExtra("listaCursuri", listaCursuri);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("lifecycle", "Apel metoda onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lifecycle", "Apel metoda onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("lifecycle", "Apel metoda onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("lifecycle", "Apel metoda onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("lifecycle", "Apel metoda onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lifecycle", "Apel metoda onDestroy");
    }
}