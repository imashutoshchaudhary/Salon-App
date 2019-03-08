


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sampat.brillsalon.Adaapter;
import com.example.sampat.brillsalon.R;

public class Adapterdaily extends RecyclerView.Adapter<Adapterdaily.Re> {
    private String[] daata;

    public Adapterdaily(String[] daata) {
        this.daata = daata;
    }
    @Override
    public Adapterdaily.Re onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View view = inf.inflate(R.layout.listdaily, parent, false);
        return new Adapterdaily.Re(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapterdaily.Re holder, int position) {
        String title=daata[position];
        holder.sha.setText(title);

    }

    @Override
    public int getItemCount() {
        return daata.length;
    }
    public static class Re extends RecyclerView.ViewHolder {
        ImageView img;
        TextView sha, nu;

        public Re(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            sha = itemView.findViewById(R.id.shaa);
            nu = itemView.findViewById(R.id.nu);


        }
    }
}
