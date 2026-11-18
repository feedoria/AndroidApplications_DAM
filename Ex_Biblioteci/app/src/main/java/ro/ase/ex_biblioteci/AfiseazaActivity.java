package ro.ase.ex_biblioteci;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class AfiseazaActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Biblioteca> listaCarti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_afiseaza);
        setTitle("Lista carti");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
       // intent.getSerializableExtra("lista"); GRESIT!!!!!
        listView = findViewById(R.id.listView);
        listaCarti = (ArrayList<Biblioteca>) intent.getSerializableExtra("lista");


        // custom adapter la lista
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),
                R.layout.elem_list, listaCarti, getLayoutInflater()){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                Biblioteca carte = getItem(position);

                TextView tvNrPagini = view.findViewById(R.id.tvNrPagini);

                if (carte!=null)
                {
                    if (carte.getNrPagini() > 300)
                        tvNrPagini.setTextColor(Color.GREEN);
                    else
                        tvNrPagini.setTextColor(Color.BLUE);
                }

                return view;
            }
        };
        // se adapter la lista
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), listaCarti.get(position).toString(),
                        Toast.LENGTH_LONG).show();

                return true;
            }
        });
    }

}