package com.example.sql;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText etName;
    Button btnAdd, btnDisplay;
    TextView tvNames;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        btnAdd = findViewById(R.id.btnAdd);
        btnDisplay = findViewById(R.id.btnDisplay);
        tvNames = findViewById(R.id.tvNames);
        dbHelper = new DatabaseHelper(this);
        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty()) {
                etName.setError("Enter a name");
                return;
            }
            if (dbHelper.addName(name)) {
                Toast.makeText(this, "Name added",
                        Toast.LENGTH_SHORT).show();
                etName.setText("");
            } else {
                Toast.makeText(this, "Failed to add name",
                        Toast.LENGTH_SHORT).show();
            }
        });
        btnDisplay.setOnClickListener(v -> {
            String names = dbHelper.getAllNames();
            tvNames.setText(names);
        });
    }
    @Override
    protected void onDestroy() {
        if (dbHelper != null) {
            dbHelper.close();
        }
        super.onDestroy();
    }
}