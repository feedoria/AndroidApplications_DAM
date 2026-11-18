package ro.ase.ex_agentieturism;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ArrayList<Rezervare> listaRezervari = new ArrayList<>();
    public int REQUEST_CODE_ADD = 100;
    TextView tvNrRez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvDataCurenta = findViewById(R.id.tvDataCurenta);
        tvDataCurenta.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US).format(new Date()));

        tvNrRez= findViewById(R.id.tvNrRezervari);
        tvNrRez.setText("Rezervari: 0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //TODO
        getMenuInflater().inflate(R.menu.meniu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemAdauga)
        {
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);
        }
        else if (item.getItemId() == R.id.itemVizualizare)
        {
            Intent intent = new Intent(getApplicationContext(), VizualizareActivity.class);
            intent.putExtra("lista", listaRezervari);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null)
        {
            Rezervare rezervare = (Rezervare) data.getSerializableExtra(AddActivity.ADD_REZERVARE);

            if (rezervare!=null) {
                listaRezervari.add(rezervare);
                tvNrRez.setText("Rezervari: " + listaRezervari.size());
                Toast.makeText(getApplicationContext(), "Rezervare salvata", Toast.LENGTH_LONG).show();
            }
        }
    }
}