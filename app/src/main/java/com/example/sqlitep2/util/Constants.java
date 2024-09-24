package com.example.sqlitep2.util;

public class Constants {
    public static final String DATABASE_NAME = "myapp.db";
    public static final int DATABASE_VERSION = 8;

    public static final String TABLE_USER = "users";
    public static final String TABLE_PRODUCT = "products";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_BARRIO = "barrio";
    public static final String COLUMN_LASTNAME = "apellido";
    public static final String COLUMN_EDAD = "edad";
    public static final String COLUMN_IDCARGO = "idcargo";



    public static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_ID + " TEXT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_LASTNAME + " TEXT,"
            + COLUMN_BARRIO + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_EDAD + " INTEGER"
            + COLUMN_IDCARGO + " TEXT"
            + COLUMN_IMAGE + " TEXT"
            + ")";

    public static final String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_PRICE + " REAL"
            + ")";
}
