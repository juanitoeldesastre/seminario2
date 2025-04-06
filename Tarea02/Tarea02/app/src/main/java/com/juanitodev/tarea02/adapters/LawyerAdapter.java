package com.juanitodev.tarea02.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.juanitodev.tarea02.R;
import com.juanitodev.tarea02.model.Lawyer;

import java.util.List;

public class LawyerAdapter extends ArrayAdapter<Lawyer> {
    private Context context;
    private List<Lawyer> lawyers;

    public LawyerAdapter(Context context, List<Lawyer> lawyers) {
        super(context, 0, lawyers);
        this.context = context;
        this.lawyers = lawyers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.lawyer_item, parent, false);
        }

        Lawyer currentLawyer = lawyers.get(position);

        TextView nameTextView = listItemView.findViewById(R.id.lawyer_name);
        TextView specialtyTextView = listItemView.findViewById(R.id.lawyer_specialty);
        ImageView avatarImageView = listItemView.findViewById(R.id.lawyer_avatar);

        nameTextView.setText(currentLawyer.getName());
        specialtyTextView.setText(currentLawyer.getSpecialty());

        // Cargar avatar si existe
        if (currentLawyer.getAvatarUri() != null && !currentLawyer.getAvatarUri().isEmpty()) {
            avatarImageView.setImageURI(Uri.parse(currentLawyer.getAvatarUri()));
        } else {
            // Usar avatar predeterminado
            avatarImageView.setImageResource(R.drawable.default_avatar);
        }

        return listItemView;
    }
}