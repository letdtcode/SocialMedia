package com.example.mascarasocialmedia.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mascarasocialmedia.FragmentReplacerActivity;
import com.example.mascarasocialmedia.R;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountFragment extends Fragment {

    private EditText nameEt, emailEt, passwordEt, confirmPasswordEt;
    private TextView loginTv;
    private Button signUpBtn;
    private FirebaseAuth auth;
    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        clickListener();
    }

    private void clickListener() {
        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentReplacerActivity) getActivity()).setFragment(new LoginFragment());
            }
        });
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEt.getText().toString();
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();
                String confirmPassword = confirmPasswordEt.getText().toString();

                if(name.isEmpty() || name.equals(" ")) {
                    nameEt.setError("Please input valid name");
                    return;
                }
                
            }
        });
    }

    private void init(View view) {
        nameEt = view.findViewById(R.id.nameAT);
        emailEt = view.findViewById(R.id.emailAT);
        passwordEt = view.findViewById(R.id.passwordAT);
        confirmPasswordEt = view.findViewById(R.id.confirmPassAT);
        loginTv = view.findViewById(R.id.loginTV);
        signUpBtn = view.findViewById(R.id.signUpBtn);

        auth = FirebaseAuth.getInstance();
    }
}