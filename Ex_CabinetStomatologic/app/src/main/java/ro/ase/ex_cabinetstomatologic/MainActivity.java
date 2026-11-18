package ro.ase.ex_cabinetstomatologic;

import android.app.sdksandbox.AppOwnedSdkSandboxInterface;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.graphics.PorterDuffKt;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Programare> listaProgramari = new ArrayList<>();
    public int REQUEST_CODE_ADD = 100;

    public int REQUEST_CODE_EDIT = 200;

    float costTotal = 0;
    int nrProgramari = 0;
    int nrProgramariUrgente = 0;
    TextView tvNrProgramariTotale;
    int poz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setTitle("Programari");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvDataCurenta = findViewById(R.id.tvDataCurenta);
        tvDataCurenta.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US).format(new Date()));

        listView = findViewById(R.id.listView);
        tvNrProgramariTotale = findViewById(R.id.tvNrProgramariTotale);


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<Programare> adapter = (ArrayAdapter<Programare>) listView.getAdapter();

                Programare programare = listaProgramari.get(position);

                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmare stergere")
                        .setMessage("Doresti sa stergi?")
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listaProgramari.remove(programare);
                                nrProgramari--;
                                tvNrProgramariTotale.setText("Numar programari: " + nrProgramari);
                                adapter.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        })
                        .create();
                alertDialog.show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                poz = position;
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra("edit", listaProgramari.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
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
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);
        }
        else if (item.getItemId() == R.id.itemStatistici)
        {
            Toast.makeText(getApplicationContext(), "Numarul programarilor urgente: " + nrProgramariUrgente +
                    "\n" + "Costul total al programarilor: " + costTotal, Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null)
        {
            Programare programare = (Programare) data.getSerializableExtra(AddActivity.ADD_PROGRAMARE);

            if (programare!=null)
            {
                listaProgramari.add(programare);

                Toast.makeText(MainActivity.this, "adaugare reusita", Toast.LENGTH_LONG).show();

                costTotal += programare.getPret();
                nrProgramari = listaProgramari.size();

                if (programare.isEsteUrgenta())
                    nrProgramariUrgente++;

                ArrayAdapter<Programare> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1 ,listaProgramari){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        return super.getView(position, convertView, parent);

                    }
                };

                listView.setAdapter(adapter);
                tvNrProgramariTotale.setText("Numar programari: " + nrProgramari);
            }
        }
        else if (REQUEST_CODE_EDIT ==requestCode && resultCode == RESULT_OK && data!=null)
        {
            Programare programare = (Programare) data.getSerializableExtra(AddActivity.ADD_PROGRAMARE);

            ArrayAdapter<Programare> adapter =(ArrayAdapter<Programare>) listView.getAdapter();
            if (programare!=null)
            {
                listaProgramari.set(poz,programare);
                adapter.notifyDataSetChanged();
            }
        }

    }
}