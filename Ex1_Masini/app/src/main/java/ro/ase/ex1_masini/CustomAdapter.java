package ro.ase.ex1_masini;

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
import java.util.zip.Inflater;

public class CustomAdapter extends ArrayAdapter<Masina> {

    Context context;
    int resource;
    List<Masina> masini = new ArrayList<>();
    LayoutInflater layoutInflater;
    public CustomAdapter(@NonNull Context context, int resource, List<Masina> masini, LayoutInflater layoutInflater) {
        super(context, resource, masini);
        this.context = context;
        this.resource = resource;
        this.masini = masini;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);

        Masina masina = masini.get(position);

        if (masina!=null)
        {
            TextView tvMarca = view.findViewById(R.id.tvMarca);
            tvMarca.setText(masina.getMarca());

            TextView tvData = view.findViewById(R.id.tvData);
            tvData.setText(masina.getDataFabricatiei().toString());

            TextView tvPret = view.findViewById(R.id.tvPret);
            tvPret.setText(String.valueOf(masina.getPret()));

            TextView tvCombustibil_02 = view.findViewById(R.id.tvCombustibil_02);
            tvCombustibil_02.setText(masina.getCombustibil());

            TextView tvNoua = view.findViewById(R.id.tvNoua);
            tvNoua.setText(String.valueOf(masina.isEsteNoua()));

        }

        return view;
    }
}
