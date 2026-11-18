package ro.ase.ex4_antrenamente;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Antrenament> {
    Context context;
    int resource;

    List<Antrenament> antrenamente = new ArrayList<>();
    LayoutInflater layoutInflater;
    public CustomAdapter(@NonNull Context context, int resource, List<Antrenament> antrenamente, LayoutInflater layoutInflater) {
        super(context, resource, antrenamente);
        this.antrenamente =antrenamente;
        this.context = context;
        this.resource = resource;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // return super.getView(position, convertView, parent);
        View view = layoutInflater.inflate(resource, parent, false);

        Antrenament antrenament = antrenamente.get(position);

        if (antrenament!=null)
        {
            TextView tvNumeExercitiu = view.findViewById(R.id.tvNumeExercitiu);
            tvNumeExercitiu.setText(antrenament.getNumeExercitiu().toString());

            TextView tvData = view.findViewById(R.id.tvData);
            tvData.setText(antrenament.getDataAntrenament().toString());

            TextView tvDurata = view.findViewById(R.id.tvDurata);
            tvDurata.setText(String.valueOf(antrenament.getDurata()));

            TextView tvTip_elem = view.findViewById(R.id.tvTip_elem);
            tvTip_elem.setText(antrenament.getTipAntrenament());

            TextView tvAreAntrenor_elem = view.findViewById(R.id.tvAreAntrenor_elem);
            tvAreAntrenor_elem.setText(Boolean.valueOf(antrenament.isCuAntrenor()).toString());
        }
        return view;
    }
}
