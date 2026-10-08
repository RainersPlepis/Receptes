package com.example.receptes_plepis_silins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.receptes_plepis_silins.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    private MyDbHelper dbHelper;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        dbHelper = new MyDbHelper(requireContext());
        return binding.getRoot();}

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.returnButton.setOnClickListener(v ->
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_Loginintro));

        binding.registerButton.setOnClickListener(v -> {
            String username = binding.lietotajvIevade.getText().toString().trim();
            String password = binding.paroleIevade.getText().toString().trim();
            String confirmPassword = binding.apstiprParoleIevade.getText().toString().trim();
            if (username.equals("Drop_Data")){
                dbHelper.Drop();
                return;}
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(requireContext(), "Lūdzu aizpildiet visas tabulas!", Toast.LENGTH_SHORT).show();
                return;}// exits listener
            if (!password.equals(confirmPassword)) {
                Toast.makeText(requireContext(), "Paroles nesakrīt!", Toast.LENGTH_SHORT).show();
                return;}// exits listener

            User user = new User(username,password);

            boolean inserted = dbHelper.insertUser(user);
            if (inserted) {
                Toast.makeText(requireContext(), "Reģisrācija veiksmīga", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_ReciepeBook);
            } else {Toast.makeText(requireContext(), "Kaut kas gāja greizi", Toast.LENGTH_SHORT).show();}
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (dbHelper != null) {
            dbHelper.close();
            dbHelper = null;}
        binding = null;
    }
}
