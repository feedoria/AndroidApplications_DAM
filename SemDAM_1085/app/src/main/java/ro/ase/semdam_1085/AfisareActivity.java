package ro.ase.semdam_1085;

import static ro.ase.semdam_1085.AddActivity.ADD_STUDENT;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AfisareActivity extends AppCompatActivity {
    public ListView listViewAfisare;
    List<Student> listaStudenti = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //listViewThird = findViewById(R.id.listViewAfisare);
        ListView listViewAfisare = findViewById(R.id.listViewAfisare);

        Intent intent = getIntent();

        Student student = (Student) intent.getSerializableExtra(AddActivity.ADD_STUDENT);
        listaStudenti.add(student);

        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listViewAfisare.setAdapter(adapter);
    }

}