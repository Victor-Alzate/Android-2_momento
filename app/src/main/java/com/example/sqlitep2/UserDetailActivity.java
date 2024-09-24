package com.example.sqlitep2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sqlitep2.data.dao.ProductDao;
import com.example.sqlitep2.data.dao.UserDao;
import com.example.sqlitep2.data.db.DatabaseManager;
import com.example.sqlitep2.data.model.User;

public class UserDetailActivity extends AppCompatActivity {
    private static final String TAG = "OUT_USER_EDIT";
    DatabaseManager dbManager;
    private UserDao userDao;
    private ProductDao productDao;

    TextView textViewUsername, textViewEmail, TextViewApellido, TextViewEdad, TextViewBarrio, TextViewIdCargo;
    ImageView imageViewUser;
    Button buttonUpdateUser, buttonDeleteUser;

    private String userId;
    private String username;
    private String apellido;
    private int edad;
    private String email;
    private String barrio;
    private String id_cargo;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseManager dbManager = DatabaseManager.getInstance(this);
        userDao = new UserDao(dbManager.openDatabase());

        textViewUsername = findViewById(R.id.textViewUsername);
        TextViewApellido= findViewById(R.id.textViewApellido);
        TextViewEdad= findViewById(R.id.textViewEdad);
        TextViewBarrio= findViewById(R.id.textViewBarrio);
        textViewEmail = findViewById(R.id.textViewEmail);
        TextViewIdCargo = findViewById(R.id.textViewId_cargo);
        imageViewUser = findViewById(R.id.imageViewUser);
        buttonUpdateUser = findViewById(R.id.buttonUpdateUser);
        buttonDeleteUser = findViewById(R.id.buttonDeleteUser);

        Intent intent = getIntent();
        userId = intent.getStringExtra("USER_ID");
        username = intent.getStringExtra("USERNAME");
        email = intent.getStringExtra("EMAIL");
        apellido = intent.getStringExtra("APELLIDO");
        edad = Integer.parseInt(intent.getStringExtra("EDAD"));
        barrio = intent.getStringExtra("BARRIO");
        id_cargo = intent.getStringExtra("IDCARGO");
        imageUrl = intent.getStringExtra("IMAGE_URL");

        textViewUsername.setText(username);
        TextViewApellido.setText(apellido);
        TextViewEdad.setText(edad);
        textViewEmail.setText(email);
        TextViewBarrio.setText(barrio);
        TextViewIdCargo.setText(id_cargo);

        loadUserImage(imageUrl);

        buttonUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetailActivity.this, UserEditActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("USERNAME", username);
                intent.putExtra("EMAIL", email);
                intent.putExtra("IMAGE_URL", imageUrl);
                intent.putExtra("EDAD", edad);
                intent.putExtra("IDCARGO", id_cargo);
                intent.putExtra("APELLIDO", apellido);
                intent.putExtra("BARRIO", barrio);
                startActivity(intent);
            }
        });

        buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });
    }

    private void loadUserImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewUser);
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmación")
                .setMessage("¿Estás seguro de que quieres realizar esta acción?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        userDao.deleteAllUsers();
                        Intent intent = new Intent(UserDetailActivity.this, UserListActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Acción a realizar si el usuario cancela
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}