package ro.ase.ex_filme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Film> {
    Context context;
    int resource;
    LayoutInflater layoutInflater;
    List<Film> listFilm;
    public CustomAdapter(@NonNull Context context, int resource,
                        List<Film> listFilme, LayoutInflater layoutInflater) {
        super(context, resource, listFilme);
        this.context = context;
        this.resource = resource;
        this.listFilm = listFilme;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);

        Film film = getItem(position);

        if (film != null)
        {
            TextView tvTitlu = view.findViewById(R.id.tvTitlu);
            tvTitlu.setText(film.getTitlu());

            TextView tvRegizor = view.findViewById(R.id.tvRegizor);
            tvRegizor.setText(film.getRegizor());


            TextView tvData = view.findViewById(R.id.tvData);
            tvData.setText(film.getDataLansarii().toString());

            TextView tvDurata = view.findViewById(R.id.tvDurata);
            tvDurata.setText(String.valueOf(film.getDurata()));

            TextView tvGen = view.findViewById(R.id.tvGen);
            tvGen.setText(film.getGen());

            TextView tvVazut = view.findViewById(R.id.tvVazut);
            tvVazut.setText(String.valueOf(film.isVazut()));
        }


        return view;
    }
}
