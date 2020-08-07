package com.robert_showler.breathefree;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import static com.robert_showler.breathefree.R.*;



/**
 * A simple {@link Fragment} subclass.
 */
public class BreatheFragment extends Fragment {


    public BreatheFragment() {


    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layout.fragment_breathe, container, false);
        ((Button) view.findViewById(id.buttonFinishExercise)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommentFragment fragment = new CommentFragment();
                if (getActivity() != null)
                    getActivity().getSupportFragmentManager().beginTransaction().replace(id.fragment, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
            }
        });
        return view;
    }
}