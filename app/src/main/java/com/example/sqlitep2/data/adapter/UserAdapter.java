package com.example.sqlitep2.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sqlitep2.R;
import com.example.sqlitep2.data.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.userName.setText(user.getUsername());
        holder.userApellido.setText(user.getApellido());
        holder.userEmail.setText(user.getEmail());
        holder.userEdad.setText(user.getEdad());
        holder.userBarrio.setText(user.getBarrio());
        holder.userIdCargo.setText(user.getId_cargo());
        Glide.with(holder.itemView.getContext())
                .load(user.getImageUrl())
                .centerCrop()
                .into(holder.userImage);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView userApellido;
        public TextView userEdad;
        public TextView userEmail;
        public TextView userBarrio;
        public TextView userIdCargo;
        public ImageView userImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            userApellido = itemView.findViewById(R.id.userApellido);
            userEdad = itemView.findViewById(R.id.userEdad);
            userEmail = itemView.findViewById(R.id.userEmail);
            userBarrio = itemView.findViewById(R.id.userBarrio);
            userIdCargo = itemView.findViewById(R.id.userIdCargo);
            userImage = itemView.findViewById(R.id.userImage);

        }
    }
}
