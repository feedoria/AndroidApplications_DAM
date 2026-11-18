package ro.ase.ex_clubsportiv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Sportiv> {
    Context context;
    int resource;

    LayoutInflater layoutInflater;

    ArrayList<Sportiv> listaSportivi;
    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Sportiv>
                         listaSportivi, LayoutInflater layoutInflater) {
        super(context, resource, listaSportivi);
        this.context = context;
        this.resource = resource;
        this.listaSportivi = listaSportivi;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =  layoutInflater.inflate(resource, parent, false);

        Sportiv sportiv = getItem(position);

        if (sportiv != null)
        {
            TextView tvNume = view.findViewById(R.id.tvNume);
            tvNume.setText(sportiv.getNumeSportiv());

            TextView tvData = view.findViewById(R.id.tvData);
            tvData.setText(String.valueOf(sportiv.getDataInscrierii().toString()));

            TextView tvVarsta = view.findViewById(R.id.tvVarsta);
            tvVarsta.setText(String.valueOf(sportiv.getVarsta()));

            TextView tvTipSport = view.findViewById(R.id.tvTipSport);
            tvTipSport.setText(String.valueOf(sportiv.getTipSport()));

            TextView tvAreOchelari = view.findViewById(R.id.tvAreOchelariElem);
            tvAreOchelari.setText(String.valueOf(sportiv.isAreOchelari()));
        }

        return view;
    }
}
