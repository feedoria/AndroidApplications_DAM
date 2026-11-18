package ro.ase.ex_florarie;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class VizualizeazaActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Comanda> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizualizeaza);
        setTitle("Vizualizare comenzi");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);

        Intent intent = getIntent();
        lista = (ArrayList<Comanda>) intent.getSerializableExtra("lista");

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),
                R.layout.elem_lista, lista, getLayoutInflater()){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Comanda comanda = getItem(position);

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
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomAdapter adapter1 =(CustomAdapter) listView.getAdapter();

                Comanda comanda = lista.get(position);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Comanda comanda = lista.get(position);

                CustomAdapter adapter1 = (CustomAdapter) listView.getAdapter();

                AlertDialog alertDialog =new AlertDialog.Builder(VizualizeazaActivity.this)
                        .setTitle("confirmare stergere")
                        .setMessage("doriti sa stergeti?")
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                lista.remove(comanda);
//                                Intent intent = getIntent();
//                                int nr = (int) intent.getSerializableExtra("nrComenzi");
//                                nr--;
                                adapter1.notifyDataSetChanged();
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