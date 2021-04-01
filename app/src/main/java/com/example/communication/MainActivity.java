package com.example.communication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextNumber;
    RecyclerView recycle;
    EditText editTextAddress;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.Name);
        editTextNumber = findViewById(R.id.Number);
        editTextAddress = findViewById(R.id.Address);

    }

    public void saveToFirebase(View v) {
        String name = editTextName.getText().toString();
        String number = editTextNumber.getText().toString();
        String address = editTextAddress.getText().toString();

        Map<String, Object> contact = new HashMap<>();
        contact.put("Name", name);
        contact.put("number", number);
        contact.put("address", address);
        db.collection("Contacts")
                .add(contact)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });
    }

    public void getFromFirebase() {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                recycle = findViewById(R.id.recycle);

                                ArrayList<model> itemList = new ArrayList<model>();
                                itemList.add(new model(document.getData().get("Name").toString(),
                                        document.getData().get("number").toString(),
                                        document.getData().get("Address").toString()));
                                recycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                Communi c = new Communi(getApplicationContext(), itemList);
                                recycle.setAdapter(c);

                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}