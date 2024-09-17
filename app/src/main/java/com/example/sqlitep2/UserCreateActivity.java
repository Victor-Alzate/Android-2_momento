package com.example.sqlitep2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqlitep2.data.dao.ProductDao;
import com.example.sqlitep2.data.dao.UserDao;
import com.example.sqlitep2.data.db.DatabaseManager;
import com.example.sqlitep2.data.model.User;

public class UserCreateActivity extends AppCompatActivity {

    private static final String TAG = "OUT_USER_CREATE";
    DatabaseManager dbManager;
    private UserDao userDao;
    private ProductDao productDao;

    TextView editTextUsername, editTextEmail, editTextImageUrl,editTextBarrio, editTextApellido, editTextEdad, editTextIdCargo, editTextIdUser;
    Button buttonUpdateUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseManager dbManager = DatabaseManager.getInstance(this);
        userDao = new UserDao(dbManager.openDatabase());

        editTextIdUser = findViewById(R.id.editTextCedula);
        editTextUsername = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextBarrio = findViewById(R.id.editTextBarrio);
        editTextIdCargo = findViewById(R.id.editTextIdCargo);
        editTextImageUrl = findViewById(R.id.editTextImageUrl);
        buttonUpdateUser = findViewById(R.id.buttonCreateUser);
        editTextEdad = findViewById(R.id.editTextEdad);

        buttonUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setId(Long.parseLong(editTextIdUser.getText().toString()));
                user.setUsername(editTextUsername.getText().toString());
                user.setApellido(editTextApellido.getText().toString());
                user.setEdad(Integer.parseInt(editTextEdad.getText().toString()));
                user.setEmail(editTextEmail.getText().toString());
                user.setBarrio(editTextBarrio.getText().toString());
                user.setId_cargo(editTextIdCargo.getText().toString());
                user.setImageUrl(editTextImageUrl.getText().toString());
                userDao.insert(user);
                Intent intent = new Intent(UserCreateActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });
    }
}