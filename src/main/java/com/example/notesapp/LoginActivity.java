package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import com.example.notesapp.databinding.ActivityLoginBinding;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    private final ActivityResultLauncher<Intent> signInLauncher =
            registerForActivityResult(
                    new FirebaseAuthUIActivityResultContract(),
                    this::onSignInResult
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            gotoMainScreen();
            return;
        }

        binding.btnLoginGoogle.setOnClickListener(v ->
                startSignIn(new AuthUI.IdpConfig.GoogleBuilder().build())
        );

        binding.btnLoginWithEmail.setOnClickListener(v ->
                startSignIn(new AuthUI.IdpConfig.EmailBuilder().build())
        );
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();

        if (result.getResultCode() == RESULT_OK) {
            Log.i("LoginActivity", "User logged in");
            gotoMainScreen();
        } else {
            String message = "Sign in cancelled";
            if (response != null && response.getError() != null) {
                message = "Sign in failed: " + response.getError().getErrorCode();
            }
            Log.w("LoginActivity", message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    private void startSignIn(AuthUI.IdpConfig provider) {
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(provider))
                .setIsSmartLockEnabled(false)
                .build();

        signInLauncher.launch(signInIntent);
    }

    private void gotoMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
