package ro.ase.ex_agentieturism;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Rezervare> {
    Context context;
    int resource;
    LayoutInflater layoutInflater;
    ArrayList<Rezervare> listaRezervari;
    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Rezervare> listaRezervari, LayoutInflater layoutInflater) {
        super(context, resource, listaRezervari);
        this.context = context;
        this.resource = resource;
        this.listaRezervari = listaRezervari;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);

        Rezervare rezervare = getItem(position);

        if (rezervare!=null)
        {
            TextView tvDestinatie = view.findViewById(R.id.tvDestinatie);
            tvDestinatie.setText(rezervare.getDestinatie());

            TextView tvData = view.findViewById(R.id.tvData);
            tvData.setText(rezervare.getDataRezervarii().toString());

            TextView tvPret = view.findViewById(R.id.tvPret);
            tvPret.setText(String.valueOf(rezervare.getPretRezervare()));

            TextView tvTipRezervare = view.findViewById(R.id.tvTipRezervare);
            tvTipRezervare.setText(rezervare.getTipRezervare());

            TextView tvAreMicDejun = view.findViewById(R.id.tvAreMicDejun);
            tvAreMicDejun.setText(String.valueOf(rezervare.isAreMicDejun()));
        }

        return  view;
    }
}
