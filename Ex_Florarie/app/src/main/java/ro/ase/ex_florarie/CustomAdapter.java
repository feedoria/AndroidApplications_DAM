package ro.ase.ex_florarie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Comanda> {
    Context context;
    int resource;

    ArrayList<Comanda> listaComenzi;
    LayoutInflater layoutInflater;
    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Comanda> listaComenzi,
                         LayoutInflater layoutInflater) {
        super(context, resource, listaComenzi);
        this.context= context;
        this.resource = resource;
        this.listaComenzi = listaComenzi;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);

        Comanda comanda = getItem(position);

        if (comanda!= null)
        {
            TextView tvNume = view.findViewById(R.id.tvNume);
            tvNume.setText(comanda.getNumeClient());

            TextView tvNrFire = view.findViewById(R.id.tvNrFire);
            tvNrFire.setText(String.valueOf(comanda.getNrFire()));
        }

        return view;
    }
}
