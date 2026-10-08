package com.example.receptes_plepis_silins;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_first);

        MyDbHelper dbHelper = new MyDbHelper(this, "login.db", null, 1);

        Button backButton = findViewById(R.id.return_button);
        backButton.setOnClickListener(v -> {
            Intent Intent = new Intent(Register.this, MainActivity.class);
            startActivity(Intent);
        });
        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(v -> {
            EditText username = findViewById(R.id.lietotajv_ievade);
            String usernameText = username.getText().toString();
            EditText password = findViewById(R.id.parole_ievade);
            String passwordText = password.getText().toString();
            EditText confirmPassword = findViewById(R.id.apstiprParole_ievade);
            String confirmPasswordText = confirmPassword.getText().toString();
            if (passwordText.equals(confirmPasswordText)) {
                Intent Intent = new Intent(Register.this, MainActivity.class);
                startActivity(Intent);
            }
            if(usernameText.isEmpty() && passwordText.isEmpty() && confirmPasswordText.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!passwordText.equals(confirmPasswordText)){
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues values = new ContentValues();
            values.put("username", usernameText);
            values.put("password", passwordText);
            values.put("confirmPassword", confirmPasswordText);

            long newRowId = db.insert("users", null, values);

            if (newRowId == -1) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                Intent Intent = new Intent(Register.this, MainActivity.class);
                startActivity(Intent);
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}
