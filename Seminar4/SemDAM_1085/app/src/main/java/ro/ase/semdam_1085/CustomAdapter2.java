package ro.ase.semdam_1085;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter2 extends ArrayAdapter<CursValutar> {

    private Context context;
    private int resource;
    private List<CursValutar> lista;
    private LayoutInflater inflater;

    public CustomAdapter2(@NonNull Context context, int resource,
                          @NonNull List<CursValutar> lista, LayoutInflater inflater) {
        super(context, resource, lista);
        this.context = context;
        this.resource = resource;
        this.lista = lista;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);

        CursValutar curs = lista.get(position);

        if (curs != null) {
            TextView tvData = view.findViewById(R.id.tvData);
            tvData.setText(curs.getDataCurs());

            TextView tvEUR = view.findViewById(R.id.tvEUR);
            tvEUR.setText(curs.getCursEUR());

            TextView tvUSD = view.findViewById(R.id.tvUSD);
            tvUSD.setText(curs.getCursUSD());

            TextView tvGBP = view.findViewById(R.id.tvGBP);
            tvGBP.setText(curs.getCursGBP());

            TextView tvXAU = view.findViewById(R.id.tvXAU);
            tvXAU.setText(curs.getCursXAU());
        }

        return view;
    }
}