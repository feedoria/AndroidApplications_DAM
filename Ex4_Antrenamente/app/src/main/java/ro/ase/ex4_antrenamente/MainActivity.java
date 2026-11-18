package ro.ase.ex4_antrenamente;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Antrenament> listaAntrenamente = new ArrayList<>();

    public int REQUEST_CODE_ADD = 100;

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
                Intent intent = new Intent(getApplicationContext(),
                        AddActivity.class);

                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE_ADD==requestCode && resultCode==RESULT_OK && data!=null)
        {
            Antrenament antrenament = (Antrenament) data.getSerializableExtra(AddActivity.ADD_ANTRENAMENT);

            listaAntrenamente.add(antrenament);

            CustomAdapter adapter = new CustomAdapter(
                    getApplicationContext(),
                    R.layout.elem_antrenament,
                    listaAntrenamente,
                    getLayoutInflater()){

                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                    View view = super.getView(position, convertView, parent);

                    Antrenament antrenament = listaAntrenamente.get(position);

                    TextView tvDurata = view.findViewById(R.id.tvDurata);

                    if(antrenament.getDurata() > 60)
                    {
                            tvDurata.setTextColor(Color.GREEN);
                    }
                    else {
                        tvDurata.setTextColor(Color.rgb(255, 140, 0)); // PORTOCALIU
                    }
                    return view;
                };
            };

            listView.setAdapter(adapter);
        }
    }
}