package com.example.sqlitep2.data.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlitep2.data.model.User;
import com.example.sqlitep2.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private SQLiteDatabase database;

    public UserDao(SQLiteDatabase database) {
        this.database = database;
    }

    // Create
    public long insert(User user) {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_ID, user.getId());
        values.put(Constants.COLUMN_USERNAME, user.getUsername());
        values.put(Constants.COLUMN_LASTNAME, user.getApellido());
        values.put(Constants.COLUMN_EDAD, user.getEdad());
        values.put(Constants.COLUMN_EMAIL, user.getEmail());
        values.put(Constants.COLUMN_BARRIO, user.getBarrio());
        values.put(Constants.COLUMN_IDCARGO, user.getId_cargo());
        values.put(Constants.COLUMN_IMAGE, user.getImageUrl());

        return database.insert(Constants.TABLE_USER, null, values);
    }

    // Read
    public User getUserById(long id) {
        Cursor cursor = database.query(Constants.TABLE_USER, null,
                Constants.COLUMN_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);
        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = new User();
            int idIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_ID);
            int usernameIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_USERNAME);
            int userapellidoIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_LASTNAME);
            int edad = cursor.getColumnIndexOrThrow(Constants.COLUMN_EDAD);
            int emailIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_EMAIL);
            int barrio = cursor.getColumnIndexOrThrow(Constants.COLUMN_BARRIO);
            int idcargo = cursor.getColumnIndexOrThrow(Constants.COLUMN_IDCARGO);
            int imageIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_IMAGE);

            user.setId(cursor.getLong(idIndex));
            user.setUsername(cursor.getString(usernameIndex));
            user.setApellido(cursor.getString(userapellidoIndex));
            user.setEdad(Integer.parseInt(cursor.getString(edad)));
            user.setEmail(cursor.getString(emailIndex));
            user.setBarrio(cursor.getString(barrio));
            user.setId_cargo(cursor.getString(idcargo));
            user.setImageUrl(cursor.getString(imageIndex));
            cursor.close();
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query(Constants.TABLE_USER, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_ID);
            int usernameIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_USERNAME);
            int userapellidoIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_LASTNAME);
            int edad = cursor.getColumnIndexOrThrow(Constants.COLUMN_USERNAME);
            int emailIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_EMAIL);
            int barrio = cursor.getColumnIndexOrThrow(Constants.COLUMN_BARRIO);
            int idcargo = cursor.getColumnIndexOrThrow(Constants.COLUMN_IDCARGO);
            int imageIndex = cursor.getColumnIndexOrThrow(Constants.COLUMN_IMAGE);


            do {
                User user = new User();
                user.setId(cursor.getLong(idIndex));
                user.setUsername(cursor.getString(usernameIndex));
                user.setApellido(cursor.getString(userapellidoIndex));
                user.setEdad(Integer.parseInt(cursor.getString(edad)));
                user.setEmail(cursor.getString(emailIndex));
                user.setBarrio(cursor.getString(barrio));
                user.setId_cargo(cursor.getString(idcargo));
                user.setImageUrl(cursor.getString(imageIndex));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return users;
    }

    // Update
    public int update(User user) {
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_ID, user.getId());
        values.put(Constants.COLUMN_USERNAME, user.getUsername());
        values.put(Constants.COLUMN_LASTNAME, user.getApellido());
        values.put(Constants.COLUMN_EDAD, user.getEdad());
        values.put(Constants.COLUMN_EMAIL, user.getEmail());
        values.put(Constants.COLUMN_BARRIO, user.getBarrio());
        values.put(Constants.COLUMN_IDCARGO, user.getId_cargo());
        values.put(Constants.COLUMN_IMAGE, user.getImageUrl());
        return database.update(Constants.TABLE_USER, values,
                Constants.COLUMN_ID + "=?", new String[]{String.valueOf(user.getId())});
    }

    // Delete
    public int delete(long id) {
        return database.delete(Constants.TABLE_USER,
                Constants.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public void deleteAllUsers() {
        database.delete(Constants.TABLE_USER, null, null);
    }
}
