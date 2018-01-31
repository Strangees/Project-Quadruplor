package com.example.stianstrange.project_quadruplor;

import android.app.LauncherActivity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupWindow;
import android.app.AlertDialog;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryListenOptions;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import android.support.v7.widget.RecyclerView.ViewHolder;


/**
 * Created by stian.strange on 27.01.2018.
 */

public class Content extends AppCompatActivity implements View.OnClickListener{
    //Declarations
    FirebaseAuth mAuth;
    TextView ChatView;
    EditText ChatSend;
    ProgressBar progressBar;
    LinearLayoutManager mLinearLayoutManager;
    List<String> messagesList;
    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ChatSend = findViewById(R.id.ChatSend);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> messagesarraylist = new ArrayList<>();


        MessageAdapter adapter = new MessageAdapter(this, messagesarraylist) {
            @Override
            public int getItemCount() {
                return 0;
            }
        };
        recyclerView.setAdapter(adapter);



        //On click listener
        findViewById(R.id.buttonSend).setOnClickListener(this);
        progressBar.setVisibility(View.GONE);
        recievetextlive();
    }




    private void sendtext(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> message = new HashMap<>();

        //strings
        String textsend = ChatSend.getText().toString().trim();
        String Uid = mAuth.getCurrentUser().getUid().toString();
        Date currentTime = Calendar.getInstance().getTime(); //gets current time
        String TS = currentTime.toString().trim();

        //puts
        message.put("UserID",Uid);
        message.put("Message",textsend);
        message.put("Timestamp",TS); //make this firestore server timestamp
        message.put("Group","support");



// Add a new document with a generated ID
        db.collection("message")
                .add(message)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String FS = "Firestoredata";
                        Log.d(FS, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String FS = "Firestoredata";
                        Log.w(FS, "Error adding document", e);
                    }
                });

    }

    public void recievetextlive() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final String TAG = "FIREBASE RECIEVE";
        db.collection("message")
                .whereEqualTo("Group", "support")
                .orderBy("Timestamp", Query.Direction.DESCENDING)
                .limit(50)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot info,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        List<String> messagesarraylist = new ArrayList<>();
                        for (DocumentSnapshot doc : info) {
                            if (doc.get("Group") != null) {
                                messagesarraylist.add(doc.getString("Message"));

                                startActivity(MessageAdapter);

                            }
                        }
                        Log.d(TAG, "Messages: " + messagesarraylist);

                    }
                });
            }

//    private void recievetextsingletime() {
//       FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("message").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
//        {
//            String TAG = "FIREBASE RECIEVE";
//            String textrecieve = ChatView.getText().toString().trim();
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData().toString());
//                                //recycler();
//                            }
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
//
//    }



    public void onClick(View view) {
        sendtext();

    }
}



