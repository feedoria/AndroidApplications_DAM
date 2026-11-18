package ro.ase.ex_cabinet_veterinar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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
import java.util.List;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity {
    public int REQUEST_CODE_ADD = 100;
    ArrayList<Animal> animale = new ArrayList<>();

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemAdauga)
        {
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD);
        }
        else if (item.getItemId() == R.id.itemVizualizeaza)
        {
            Intent intent = new Intent(getApplicationContext(), VizualizareActivity.class);
            intent.putExtra("lista", animale);
            startActivity(intent);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null )
        {
            Animal animal =(Animal) data.getSerializableExtra(AddActivity.ADD_ANIMAL);

            if (animal!=null)
            {
                animale.add(animal);
            }
        }
    }
}