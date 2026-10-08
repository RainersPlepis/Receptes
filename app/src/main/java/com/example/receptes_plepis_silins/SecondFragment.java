package com.example.receptes_plepis_silins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.receptes_plepis_silins.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private MyDbHelper dbHelper;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        dbHelper = new MyDbHelper(requireContext());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.returnButton2.setOnClickListener(v ->
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_Loginintro));

        binding.loginButton2.setOnClickListener(v -> {
            String username = binding.lietotajvIevade3.getText().toString().trim();
            String password = binding.paroleIevade2.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (username.equals("admin") && password.equals("admin")) {
                Toast.makeText(requireContext(), "Welcome admin", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_AdminActivity);
                return;
            }

            boolean exists = dbHelper.getUser(username, password);
            if (exists) {
                Toast.makeText(requireContext(), "Welcome " + username, Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_ReciepeBook);
            } else {
                Toast.makeText(requireContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (dbHelper != null) {
            dbHelper.close();
            dbHelper = null;
        }
        binding = null;
    }
}
