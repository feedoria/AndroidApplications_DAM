package ro.ase.ex_agentieturism;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class VizualizareActivity extends AppCompatActivity {
    ArrayList<Rezervare> listaRezervari;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizualizare);
        setTitle("Vizualizare inregistrari");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);

        Intent intent = getIntent();
        listaRezervari = (ArrayList<Rezervare>) intent.getSerializableExtra("lista");

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),
                R.layout.elem_lista, listaRezervari, getLayoutInflater())
        {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Rezervare rezervare = getItem(position);

                TextView tvPret = view.findViewById(R.id.tvPret);

                if (rezervare!=null)
                {
                    if (rezervare.getPretRezervare() > 300)
                        tvPret.setTextColor(Color.RED);
                    else
                        tvPret.setTextColor(Color.GREEN);
                }

                return  view;
            }
        };

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Rezervare rezervare = adapter.getItem(position);


                AlertDialog alertDialog = new AlertDialog.Builder(VizualizareActivity.this)
                        .setTitle("Confirmare stergere")
                        .setMessage("Doresti sa stergi obiectul: \n" + rezervare.toString())
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listaRezervari.remove(rezervare);
                                adapter.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        })
                        .create();
                alertDialog.show();

                return true;
            }
        });
    }
}