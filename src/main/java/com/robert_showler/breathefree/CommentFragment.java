package com.robert_showler.breathefree;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {

    private Spinner before;
    private Spinner after;
    private EditText feedback;

    public CommentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        before = view.findViewById(R.id.spinnerFeedbackTypeBefore);
        after = view.findViewById(R.id.spinnerFeedbackTypeAfter);
        feedback = view.findViewById(R.id.textEditFeedback);

        ((Button) view.findViewById(R.id.buttonSubmitFeedback)).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View view) {
                FirebaseAuth user = FirebaseAuth.getInstance();
                if (user.getUid() != null) {
                    Map<String, String> history = new HashMap<>();
                    history.put("Date", (new SimpleDateFormat("d MMM yyyy")).format(new Date()));
                    history.put("Time", (new SimpleDateFormat("HH:mm:ss z")).format(new Date()));
                    history.put("Before", before.getSelectedItem().toString());
                    history.put("After", after.getSelectedItem().toString());
                    history.put("Feedback", feedback.getText().toString());

                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    database.child("History").child(user.getUid()).push().setValue(history);
                    Toast.makeText(view.getContext(), "Feedback submitted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}