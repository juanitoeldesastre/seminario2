package com.juanitodev.tarea02.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.juanitodev.tarea02.model.Lawyer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LawyerDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Lawyers.db";
    private static final int DATABASE_VERSION = 1;

    // Definición de tabla
    private static final String TABLE_LAWYERS = "lawyer";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_UUID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SPECIALTY = "specialty";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_BIO = "bio";
    private static final String COLUMN_AVATAR_URI = "avatarUri";

    public LawyerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_LAWYER_TABLE = "CREATE TABLE " + TABLE_LAWYERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_UUID + " TEXT NOT NULL," +
                COLUMN_NAME + " TEXT NOT NULL," +
                COLUMN_SPECIALTY + " TEXT NOT NULL," +
                COLUMN_PHONE + " TEXT NOT NULL," +
                COLUMN_BIO + " TEXT," +
                COLUMN_AVATAR_URI + " TEXT);";
        db.execSQL(SQL_CREATE_LAWYER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LAWYERS);
        onCreate(db);
    }

    // CRUD: Create - Crear nuevo abogado
    public long addLawyer(Lawyer lawyer) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        // Generar un UUID si no existe
        if (lawyer.getId() == null || lawyer.getId().isEmpty()) {
            lawyer.setId(UUID.randomUUID().toString());
        }

        values.put(COLUMN_UUID, lawyer.getId());
        values.put(COLUMN_NAME, lawyer.getName());
        values.put(COLUMN_SPECIALTY, lawyer.getSpecialty());
        values.put(COLUMN_PHONE, lawyer.getPhone());
        values.put(COLUMN_BIO, lawyer.getBio());
        values.put(COLUMN_AVATAR_URI, lawyer.getAvatarUri());

        return db.insert(TABLE_LAWYERS, null, values);
    }

    // CRUD: Read - Leer todos los abogados
    public List<Lawyer> getAllLawyers() {
        List<Lawyer> lawyerList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_LAWYERS,
                null,
                null,
                null,
                null,
                null,
                COLUMN_NAME + " ASC"
        );

        try {
            int idColumnIndex = cursor.getColumnIndex(COLUMN_UUID);
            int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME);
            int specialtyColumnIndex = cursor.getColumnIndex(COLUMN_SPECIALTY);
            int phoneColumnIndex = cursor.getColumnIndex(COLUMN_PHONE);
            int bioColumnIndex = cursor.getColumnIndex(COLUMN_BIO);
            int avatarUriColumnIndex = cursor.getColumnIndex(COLUMN_AVATAR_URI);

            while (cursor.moveToNext()) {
                Lawyer lawyer = new Lawyer();
                lawyer.setId(cursor.getString(idColumnIndex));
                lawyer.setName(cursor.getString(nameColumnIndex));
                lawyer.setSpecialty(cursor.getString(specialtyColumnIndex));
                lawyer.setPhone(cursor.getString(phoneColumnIndex));
                lawyer.setBio(cursor.getString(bioColumnIndex));
                lawyer.setAvatarUri(cursor.getString(avatarUriColumnIndex));

                lawyerList.add(lawyer);
            }
        } finally {
            cursor.close();
        }

        return lawyerList;
    }

    // CRUD: Read - Obtener un abogado por ID
    public Lawyer getLawyerById(String lawyerId) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_LAWYERS,
                null,
                COLUMN_UUID + " = ?",
                new String[]{lawyerId},
                null,
                null,
                null
        );

        Lawyer lawyer = null;

        try {
            if (cursor.moveToFirst()) {
                int idColumnIndex = cursor.getColumnIndex(COLUMN_UUID);
                int nameColumnIndex = cursor.getColumnIndex(COLUMN_NAME);
                int specialtyColumnIndex = cursor.getColumnIndex(COLUMN_SPECIALTY);
                int phoneColumnIndex = cursor.getColumnIndex(COLUMN_PHONE);
                int bioColumnIndex = cursor.getColumnIndex(COLUMN_BIO);
                int avatarUriColumnIndex = cursor.getColumnIndex(COLUMN_AVATAR_URI);

                lawyer = new Lawyer();
                lawyer.setId(cursor.getString(idColumnIndex));
                lawyer.setName(cursor.getString(nameColumnIndex));
                lawyer.setSpecialty(cursor.getString(specialtyColumnIndex));
                lawyer.setPhone(cursor.getString(phoneColumnIndex));
                lawyer.setBio(cursor.getString(bioColumnIndex));
                lawyer.setAvatarUri(cursor.getString(avatarUriColumnIndex));
            }
        } finally {
            cursor.close();
        }

        return lawyer;
    }

    // CRUD: Update - Actualizar datos de un abogado
    public int updateLawyer(Lawyer lawyer) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, lawyer.getName());
        values.put(COLUMN_SPECIALTY, lawyer.getSpecialty());
        values.put(COLUMN_PHONE, lawyer.getPhone());
        values.put(COLUMN_BIO, lawyer.getBio());
        values.put(COLUMN_AVATAR_URI, lawyer.getAvatarUri());

        return db.update(
                TABLE_LAWYERS,
                values,
                COLUMN_UUID + " = ?",
                new String[]{lawyer.getId()}
        );
    }

    // CRUD: Delete - Eliminar un abogado
    public int deleteLawyer(String lawyerId) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(
                TABLE_LAWYERS,
                COLUMN_UUID + " = ?",
                new String[]{lawyerId}
        );
    }
}