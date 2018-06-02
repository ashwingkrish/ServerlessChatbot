package com.ashwingkrish.chattest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    SignInButton sb;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener firebaseAuthListener;

    String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sb = findViewById(R.id.sb_login);

        // TODO: Handle already signed in user (auth state)



        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO: Handle sign in request
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: Add auth state listener
    }


    @Override
    protected void onStop() {
        super.onStop();
        // TODO: Detach auth state listener
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO: Handle sign in result
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        // TODO: Authenticate user inside our app
    }

}
