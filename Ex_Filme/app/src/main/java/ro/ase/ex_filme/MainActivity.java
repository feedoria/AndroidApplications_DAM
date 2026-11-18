package ro.ase.ex_filme;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Film> listaFilme = new ArrayList<>();

    int REQUEST_CODE_ADD = 100;
    int REQUEST_CODE_EDIT = 200;

    int poz;

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

        listView = findViewById(R.id.listView);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                poz = position;
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra(AddActivity.EDIT_FILM, listaFilme.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_ADD == requestCode && resultCode == RESULT_OK && data != null)
        {
            Film film = (Film) data.getSerializableExtra(AddActivity.ADD_FILM);

            if (film != null)
            {
                listaFilme.add(film);

//                ArrayAdapter<Film> adapter = new ArrayAdapter<>(getApplicationContext(),
//                        android.R.layout.simple_list_item_1, listaFilme);
                CustomAdapter adapter = new CustomAdapter(getApplicationContext(),
                        R.layout.elem_lista, listaFilme, getLayoutInflater()){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        Film film = getItem(position);

                        TextView tvDurata = view.findViewById(R.id.tvDurata);

                        if (film != null)
                        {
                            if (film.getDurata() > 120)
                            {
                                tvDurata.setTextColor(Color.RED);
                            }
                            else {
                                tvDurata.setTextColor(Color.GREEN);
                            }
                        }

                        return view;
                    }
                };

                listView.setAdapter(adapter);
            }
        }
        else if(requestCode ==REQUEST_CODE_EDIT && resultCode ==RESULT_OK && data!=null)
        {
            Film film = (Film) data.getSerializableExtra(AddActivity.ADD_FILM);

            CustomAdapter adapter = (CustomAdapter) listView.getAdapter();

            if(film!=null)
            {
                listaFilme.set(poz, film);
                adapter.notifyDataSetChanged();
            }
        }
    }
}