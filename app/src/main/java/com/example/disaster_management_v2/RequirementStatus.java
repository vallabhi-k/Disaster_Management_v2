package com.example.disaster_management_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RequirementStatus extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.requirement_status_layout,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
//                                                               @Override
//                                                               public void onClick(View v) {
//                                                                   Intent transfertomaps = new Intent(getContext(), MainActivity.class);
//                                                                   startActivity(transfertomaps);
//
//                                                               }
//                                                           }
//        );
    }
}
