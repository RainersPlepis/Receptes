package com.example.receptes_plepis_silins;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.receptes_plepis_silins.databinding.ReciepeBookBinding;

public class ReciepeBook extends Activity {
    private ReciepeBookBinding binding;

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = ReciepeBookBinding.inflate(inflater, container, false);
        return binding.getRoot();    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.parMums.setOnClickListener(v -> // for later?
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment)
        );/*//*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
