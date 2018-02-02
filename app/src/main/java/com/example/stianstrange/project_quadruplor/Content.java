package com.example.stianstrange.project_quadruplor;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;


/**
 * Created by stian.strange on 27.01.2018.
 */

public class Content extends Activity implements View.OnClickListener{
    //Declarations
    FirebaseAuth mAuth;
    EditText ChatSend;
    private List<Person> persons;
  //  public List<String> messages;
    //public List<char[]> MessageInfo;
    private RecyclerView mRecyclerView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.recyclerview_activity);

            mRecyclerView = findViewById(R.id.rv);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(llm);
            mRecyclerView.setHasFixedSize(true);
            mAuth = FirebaseAuth.getInstance();
            recievetextlive();
            //initializeData();
            // initializeAdapter();
            //initializeAdaptermessage();
            //initializeDataMessage();
        }
    private void recievetextlive() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final String TAG = "FIREBASE RECIEVE";
        db.collection("message")
                .whereEqualTo("Group", "support")
                .orderBy("Timestamp", Query.Direction.DESCENDING)
                .limit(50)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent

                            (@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }

                        final List<String> messages = new ArrayList<>();
                        for (DocumentSnapshot doc : value) {
                            if (doc.get("Message") != null) {
                                messages.add(doc.getString("Message"));
                               // messages.add(String.valueOf(new Message(doc.getString("Message"))));

                            }
                        }
                        Log.d(TAG, "Current messages in document" + messages);

                        initializeAdaptermessage();
                    }
                });
    }

        private void initializeData(){
            persons = new ArrayList<>();
            persons.add(new Person("Emma Wilson", "23 years old"));
            persons.add(new Person("Lavery Maiss", "25 years old"));
            persons.add(new Person("Lillie Watts", "35 years old"));
            persons.add(new Person("Emma Wilson", "23 years old"));
            persons.add(new Person("Lavery Maiss", "25 years old"));
            persons.add(new Person("Lillie Watts", "35 years old"));
            persons.add(new Person("Emma Wilson", "23 years old"));
            persons.add(new Person("Lavery Maiss", "25 years old"));
            persons.add(new Person("Lillie Watts", "35 years old"));
            persons.add(new Person("Emma Wilson", "23 years old"));
            persons.add(new Person("Lavery Maiss", "25 years old"));
            persons.add(new Person("Lillie Watts", "35 years old"));
            persons.add(new Person("Emma Wilson", "23 years old"));
            persons.add(new Person("Lavery Maiss", "25 years old"));
            persons.add(new Person("Lillie Watts", "35 years old"));
            persons.add(new Person("Emma Wilson", "23 years old"));
            persons.add(new Person("Lavery Maiss", "25 years old"));
            persons.add(new Person("Lillie Watts", "35 years old"));
            persons.add(new Person("Emma Wilson", "23 years old"));
            persons.add(new Person("Lavery Maiss", "25 years old"));
            persons.add(new Person("Lillie Watts", "35 years old"));
            persons.add(new Person("Emma Wilson", "23 years old"));
            persons.add(new Person("Lavery Maiss", "25 years old"));
            persons.add(new Person("Lillie Watts", "35 years old"));
            persons.add(new Person("Emma Wilson", "23 years old"));
            persons.add(new Person("Lavery Maiss", "25 years old"));
            persons.add(new Person("Lillie Watts", "35 years old"));
            persons.add(new Person("Emma Wilson", "23 years old"));
            persons.add(new Person("Lavery Maiss", "25 years old"));
            persons.add(new Person("Lillie Watts", "35 years old"));
        }

       private void initializeAdapter(){
          //RVAdapter adapter = new RVAdapter(persons);
         // RVAdaptermessage adapter = new RVAdaptermessage(persons);
         //mRecyclerView.setAdapter(adapter);

       }


       public void initializeAdaptermessage() {
           // recievetextlive();
           //   messages.add(new Message("hei hei"));
           RVAdapter adapter = new RVAdapter(Message);
           mRecyclerView.setAdapter(adapter);


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



    public void onClick(View view) {
        sendtext();

    }
}



