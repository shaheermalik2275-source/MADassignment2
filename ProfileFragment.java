package com.example.L1S22BSCS0324;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment
  {

    private TextView tvUsername, tvEmail, tvTheme;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvUsername = view.findViewById(R.id.tvUsername);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvTheme = view.findViewById(R.id.tvTheme);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPreferences();
    }

    private void loadPreferences() {
        SharedPreferences prefs = requireActivity()
                .getSharedPreferences("user_prefs", Context.MODE_PRIVATE);

        String username = prefs.getString("username", "No username saved");
        String email = prefs.getString("email", "No email saved");
        String theme = prefs.getString("theme", "Not set");

        tvUsername.setText("Username: " + username);
        tvEmail.setText("Email: " + email);
        tvTheme.setText("Theme: " + theme);
    }
}
