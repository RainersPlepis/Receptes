package com.example.receptes_plepis_silins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.receptes_plepis_silins.databinding.IntroLoginBinding;

public class Loginintro extends Fragment {
    private IntroLoginBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = IntroLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.LoginEnter.setOnClickListener(v ->
                NavHostFragment.findNavController(Loginintro.this)
                        .navigate(R.id.action_Loginintro_to_FirstFragment)
        );
        binding.RegisterEnter.setOnClickListener(v ->
                NavHostFragment.findNavController(Loginintro.this)
                        .navigate(R.id.action_Loginintro_to_SecondFragment)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
