package ro.ase.semdam_1085;

import android.app.AlertDialog;
import android.app.PictureInPictureParams;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ListView listView;
    List<Student> listaStud = new ArrayList<>();

    public static final int REQUEST_CODE_ADD = 100;
    public static final int REQUEST_CODE_EDIT = 200;

    public static final String EDIT_STUDENT = "editStudent";
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

        listView = findViewById(R.id.listView);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                //startActivity(intent); -> asa intent e gol
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Student student = listaStud.get(position);

                ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
                // vreau sa sterg deci l am gasit din lista si ii dau remove din lista
                // si vreau si refresh in listView -> adaptorul face asta
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmare stergere")
                        .setMessage("Doriti sa stergeti?")
                        .setNegativeButton("NU", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("DA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listaStud.remove(student);
                                adapter.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        })
                        .create();

                dialog.show();

                return true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                poz = position;
                intent.putExtra(EDIT_STUDENT, listaStud.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data!=null)
        {
            Student student =(Student) data.getSerializableExtra(AddActivity.ADD_STUDENT);
            if (student!=null)
            {
                listaStud.add(student);
                Toast.makeText(getApplicationContext(),
                        student.toString(),
                        Toast.LENGTH_LONG).show();
                ArrayAdapter<Student> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, listaStud);
                listView.setAdapter(adapter);
            }
        }
        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null)
        {
            Student student =(Student) data.getSerializableExtra(AddActivity.ADD_STUDENT);
            if (student!=null)
            {
                listaStud.get(poz).setNume(student.getNume());
                listaStud.get(poz).setDataNasterii(student.getDataNasterii());
                listaStud.get(poz).setMedie(student.getMedie());
                listaStud.get(poz).setAreBursa(student.isAreBursa());
                listaStud.get(poz).setFacultate(student.getFacultate());

                ArrayAdapter<Student> adapter = (ArrayAdapter<Student>) listView.getAdapter();
                adapter.notifyDataSetChanged();
            }
        }
    }

    // si la proba practiva o sa avem meniu -> butoane din main in second activity punem un buton
    // la celalalt numar un meniu cu optiuni
    // custom adapter
    // sau array adapter cu meniu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.optiune1)
        {
            Intent intent = new Intent(this, BNRActivity.class);
            startActivity(intent);

            return true;
        }
        else if (item.getItemId() == R.id.optiune2)
        {
            //TODO
            return true;
        }
        else if (item.getItemId() == R.id.optiune3)
        {
            //TODO
            return true;
        }
        else if (item.getItemId() == R.id.optiune4)
        {
            //TODO
            return true;
        }

        // Modificam din add activity avem butonul de adauga care trimite mai departe intentul cu put extre
        // inpoi catre main activity si finish inchide activitatea
        // trb sa trimitem studentul asta ADD_STUDENT intr o activitate urmand sa fie afisat acolo
        // intr un listView (third activity achivalent activitate goala cu listview in care se afiseaza
        // studentii)
        return false;
    }
}