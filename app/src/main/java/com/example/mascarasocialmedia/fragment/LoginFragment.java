package com.example.mascarasocialmedia.fragment;

import static com.example.mascarasocialmedia.fragment.CreateAccountFragment.EMAIL_REGEX;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mascarasocialmedia.MainActivity;
import com.example.mascarasocialmedia.R;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private EditText emailEt, passwordEt;
    private TextView signupTV, forgotPasswordTV;
    private Button loginBtn, googleSignInBtn;
    private ProgressBar progressBar;

    private FirebaseAuth auth;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        auth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        clickListener();
    }

    private void clickListener() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();

                if (email.isEmpty() || email.matches(EMAIL_REGEX)) {
                    emailEt.setError("Input valid email");
                    return;
                }

                if (password.isEmpty() || password.length() < 6) {
                    passwordEt.setError("Input 6 digit valid password");
                    return;
                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = auth.getCurrentUser();

                                    if (!user.isEmailVerified()) {
                                        Toast.makeText(getContext(), "Please verify your email", Toast.LENGTH_SHORT).show();
                                    }
                                    sendUserToMainActivity();
                                } else {
                                    String exception = "Error: "+task.getException().getMessage();
                                    Toast.makeText(getContext(), exception, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void sendUserToMainActivity() {
        if (getActivity() == null)
            return;
        startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
        getActivity().finish();
    }

    private void init(View view) {
        emailEt = view.findViewById(R.id.emailAT);
        passwordEt = view.findViewById(R.id.passwordAT);
        signupTV = view.findViewById(R.id.signUpTV);
        forgotPasswordTV = view.findViewById(R.id.forgotTV);
        loginBtn = view.findViewById(R.id.loginBtn);
        googleSignInBtn = view.findViewById(R.id.googleSignInBtn);
        progressBar = view.findViewById(R.id.progressBar);
    }
    private void signIn() {

    }
}
