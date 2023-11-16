package com.example.insertingintosqldatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText userName,userPassword,userID;
    Button insertBtn,fetchBtn,deleteBtn,updateBtn;

    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userID = findViewById(R.id.edtTxtID);
        userName = findViewById(R.id.edtTxtUserName);
        userPassword = findViewById(R.id.edtTxtPassword);

        insertBtn = findViewById(R.id.btnInsert);
        fetchBtn = findViewById(R.id.btnFetch);
        updateBtn = findViewById(R.id.btnUpdate);
        deleteBtn = findViewById(R.id.btnDelete);

        databaseManager = new DatabaseManager(this);
        try{
            databaseManager.open();
        }
        catch(Exception exception){
            exception.printStackTrace();
        }
    }

    public void insertBtnPressed(View view){
        databaseManager.insert(userName.getText().toString(), userPassword.getText().toString());

    }

    public void fetchBtnPressed(View view) {
        Cursor cursor = databaseManager.fetch();
        if (cursor.moveToFirst()) {
            do {
                String ID = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_ID));
                String user_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_NAME));
                String user_password = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_PASSWORD));
                Log.i("DATABASE_TAG", "I have read ID : " + ID + "Username : " + user_name + "Password : " + user_password);
            } while (cursor.moveToNext());

        }
    }

    public void updateBtnPressed(View view){
        databaseManager.update(Long.parseLong(userID.getText().toString()), userName.getText().toString(), userPassword.getText().toString());

    }

    public void deleteBtnPressed(View view){
        databaseManager.delete(Long.parseLong(userID.getText().toString()));
    }
}