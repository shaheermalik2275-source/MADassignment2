package com.example.L1S22BSCS0324;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    private EditText etUsername, etEmail, etPassword, etTheme;
    private Button btnSave, btnReset;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        etUsername = view.findViewById(R.id.etUsername);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etTheme = view.findViewById(R.id.etTheme);
        btnSave = view.findViewById(R.id.btnSave);
        btnReset = view.findViewById(R.id.btnReset);

        // Load existing data into the fields (if any)
        loadPreferences();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPreferences();
            }
        });

        return view;
    }

    private void loadPreferences() {
        SharedPreferences prefs = requireActivity()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        String username = prefs.getString("username", "");
        String email = prefs.getString("email", "");
        String password = prefs.getString("password", "");
        String theme = prefs.getString("theme", "");

        etUsername.setText(username);
        etEmail.setText(email);
        etPassword.setText(password);
        etTheme.setText(theme);
    }

    private void savePreferences() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String theme = etTheme.getText().toString().trim().toLowerCase();

        // Simple validation
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Username required");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email required");
            return;
        }

        if (!email.contains("@")) {
            etEmail.setError("Invalid email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password required");
            return;
        }

        if (!(TextUtils.isEmpty(theme) ||
                theme.equals("light") ||
                theme.equals("dark"))) {
            etTheme.setError("Use 'light' or 'dark'");
            return;
        }

        SharedPreferences prefs = requireActivity()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("theme", theme);
        editor.apply();

        Toast.makeText(getActivity(), "Preferences saved successfully", Toast.LENGTH_SHORT).show();
    }

    private void resetPreferences() {
        SharedPreferences prefs = requireActivity()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.clear(); // remove all stored keys
        editor.apply();

        // Clear input fields on screen
        etUsername.setText("");
        etEmail.setText("");
        etPassword.setText("");
        etTheme.setText("");

        Toast.makeText(getActivity(), "Preferences reset", Toast.LENGTH_SHORT).show();
    }
}
