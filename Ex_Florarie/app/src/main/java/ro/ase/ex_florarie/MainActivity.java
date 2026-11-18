package ro.ase.ex_florarie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public TextView tvNrComenzi;
    public int nrComenzi=0;
    public int REQUEST_CODE_ADD = 100;
    public int REQUEST_CODE_EDIT = 200;
    ArrayList<Comanda> listaCom = new ArrayList<>();
    public int poz;

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
        tvDataCurenta.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.US).format(new Date()));

        tvNrComenzi = findViewById(R.id.tvNrComenzi);

        Button btnAdauga = findViewById(R.id.btnAdauga);
        Button btnVizualizeaza = findViewById(R.id.btnVizualizeaza);

        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        btnVizualizeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VizualizeazaActivity.class);
                intent.putExtra("lista", listaCom);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meniu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemAdauga)
        {

        }
        else if (item.getItemId() == R.id.itemVizualizeaza)
        {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (REQUEST_CODE_ADD == requestCode && resultCode == RESULT_OK && data != null)
        {
            Comanda comanda  = (Comanda) data.getSerializableExtra(AddActivity.ADD_COMANDA);

            listaCom.add(comanda);

            nrComenzi++;
            Intent intent = new Intent(getApplicationContext(), VizualizeazaActivity.class);
            intent.putExtra("nrComenzi", nrComenzi);


            tvNrComenzi.setText("Numar comenzi: " + nrComenzi);

            CustomAdapter adapter = new CustomAdapter(getApplicationContext(),
                    R.layout.elem_lista, listaCom, getLayoutInflater()){
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    TextView tvNrFire = view.findViewById(R.id.tvNrFire);

                    if (comanda!=null)
                    {
                        if (comanda.getNrFire() > 50)
                        {
                            tvNrFire.setTextColor(Color.GREEN);
                        }
                        else
                            tvNrFire.setTextColor(Color.BLACK);
                    }

                    return view;
                }
            };

        }
        else if (REQUEST_CODE_EDIT==requestCode && resultCode == RESULT_OK && data!=null)
        {
            Comanda comanda = (Comanda) data.getSerializableExtra(AddActivity.ADD_COMANDA);

            if (comanda!=null)
            {
                listaCom.set(poz, comanda);
            }
        }
    }
}