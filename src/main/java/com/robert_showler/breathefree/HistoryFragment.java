package com.robert_showler.breathefree;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private HistoryListAdapter historyListAdapter;

    public HistoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        ListView listView = view.findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                History history = (History) parent.getItemAtPosition(position);
                new AlertDialog.Builder(view.getContext())
                        .setTitle("History Details")
                        .setMessage("Date:\t\t" + history.getDate() + "\n" +
                                "Time:\t\t" + history.getTime() + "\n" +
                                "Before Exercise:\t" + history.getBeforeExercise() + "\n" +
                                "After Exercise:\t" + history.getAfterExercise() + "\n" +
                                "Feedback:\t\t" + history.getFeedback() + "\n")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        });
        historyListAdapter = new HistoryListAdapter(view.getContext(), R.layout.history_card);
        listView.setAdapter(historyListAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setView(R.layout.progress);
        builder.setCancelable(false);
        final Dialog dialog = builder.create();
        dialog.show();
        String uid = FirebaseAuth.getInstance().getUid();
        if (uid != null) {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference("History");
            database.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot allData) {
                    for (DataSnapshot dataSnapshot : allData.getChildren()) {
                        History history = new History();
                        history.setDate(String.valueOf(dataSnapshot.child("Date").getValue()));
                        history.setTime(String.valueOf(dataSnapshot.child("Time").getValue()));
                        history.setBeforeExercise(String.valueOf(dataSnapshot.child("Before").getValue()));
                        history.setAfterExercise(String.valueOf(dataSnapshot.child("After").getValue()));
                        history.setFeedback(String.valueOf(dataSnapshot.child("Feedback").getValue()));
                        historyListAdapter.add(history);
                        historyListAdapter.notifyDataSetChanged();
                    }
                    dialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        return view;
    }
}