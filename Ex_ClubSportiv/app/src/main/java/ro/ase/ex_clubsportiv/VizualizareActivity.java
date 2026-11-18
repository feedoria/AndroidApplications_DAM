package ro.ase.ex_clubsportiv;

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
    ListView listView;
    ArrayList<Sportiv> lista ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vizualizare);
        setTitle("Lista inregistrari");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);


        Intent intent = getIntent();

        lista = (ArrayList<Sportiv>) getIntent().getSerializableExtra("lista");
//        intent.getSerializableExtra("lista", MainActivity.class);
//
//        ArrayAdapter<Sportiv> adapter = new ArrayAdapter<>(getApplicationContext(),
//                android.R.layout.simple_list_item_1, lista);

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),
                R.layout.elem_sportiv, lista, getLayoutInflater()){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Sportiv sportiv = getItem(position);

                TextView tvVarsta = view.findViewById(R.id.tvVarsta);

                if (sportiv != null)
                {
                    if (sportiv.getVarsta() >= 18)
                        tvVarsta.setTextColor(Color.GREEN);
                    else
                        tvVarsta.setTextColor(Color.RED);
                }

                return view;
            }
        };

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Sportiv sportiv = adapter.getItem(position);

                AlertDialog alertDialog = new AlertDialog.Builder(VizualizareActivity.this)
                        .setTitle("Obiectul selectat")
                        .setMessage(sportiv.toString())
                        .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
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