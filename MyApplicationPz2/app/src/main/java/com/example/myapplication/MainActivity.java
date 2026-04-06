package com.example.view_binding;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.view_binding.databinding.ActivityMainBinding; [cite: 160]
import com.example.view_binding.models.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); [cite: 158]

        // Створення об'єкта зв'язування
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main); [cite: 160, 161]

        // Створення початкових даних
        User user = new User("Andrew", "Nikolsky", 19, "AB943284"); [cite: 161]

        // Передача моделі в розмітку
        binding.setUser(user); [cite: 161]
    }
}