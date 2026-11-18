package ro.ase.ex_biblioteci;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Biblioteca> {
    int resource;
    Context context;
    List<Biblioteca> lista;
    LayoutInflater layoutInflater;
    public CustomAdapter(@NonNull Context context, int resource, List<Biblioteca> lista,
                         LayoutInflater layoutInflater) {
        super(context, resource, lista);
        this.context = context;
        this.resource = resource;
        this.lista = lista;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);

        Biblioteca carte = getItem(position);

        if(carte != null)
        {
            TextView tvTitlu = view.findViewById(R.id.tvTitlu);
            tvTitlu.setText(carte.getTitlu());

            TextView tvAutor = view.findViewById(R.id.tvAutor);
            tvAutor.setText(carte.getAutor());

            TextView tvData = view.findViewById(R.id.tvData);
            tvData.setText(carte.getDataPublicarii().toString());

            TextView tvNrPagini = view.findViewById(R.id.tvNrPagini);
            tvNrPagini.setText(String.valueOf(carte.getNrPagini()));

            TextView tvGen = view.findViewById(R.id.tvGen);
            tvGen.setText(String.valueOf(carte.getGen()));
        }

        return view;
    }
}
