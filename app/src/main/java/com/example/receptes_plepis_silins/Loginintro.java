package com.example.receptes_plepis_silins;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.receptes_plepis_silins.databinding.IntroLoginBinding;

public class Loginintro extends Activity {
    private IntroLoginBinding binding;
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = IntroLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);// this is my 13th reason, why the fuck are you complaining?

        binding.LoginEnter.setOnClickListener(v ->
                NavHostFragment.findNavController(Loginintro.this)
                        .navigate(R.id.action_Loginintro_to_FirstFragment)
        );
        binding.RegisterEnter.setOnClickListener(v ->
                NavHostFragment.findNavController(Loginintro.this)
                        .navigate(R.id.action_Loginintro_to_SecondFragment)
        );
    }
}
