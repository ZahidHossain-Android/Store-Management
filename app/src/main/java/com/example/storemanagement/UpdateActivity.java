package com.example.storemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateActivity extends AppCompatActivity {

    LinearLayout showItem,updateSection;
    TextView showName,showQuantity,updateName;
    Button btnSearch,btnUpdate,updateConfirm,btnIncrease,btnDecrease;
    EditText updateQuantity,searchText;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        searchText = findViewById(R.id.searchText);
        showItem = findViewById(R.id.showItem);
        showName = findViewById(R.id.showName);
        showQuantity = findViewById(R.id.showQuantity);
        btnSearch = findViewById(R.id.btnSearch);
        btnUpdate = findViewById(R.id.btnUpdate);
        updateSection = findViewById(R.id.updateSection);
        updateName = findViewById(R.id.updateName);
        updateQuantity = findViewById(R.id.updateQuantity);
        updateConfirm = findViewById(R.id.updateConfirm);
        btnIncrease = findViewById(R.id.btnP);
        btnDecrease = findViewById(R.id.btnM);
        databaseReference = FirebaseDatabase.getInstance().getReference("Update Information");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchItem();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSection.setVisibility(View.VISIBLE);
                String exitPname = showName.getText().toString();
                String exitPquantity = showQuantity.getText().toString();
                updateName.setText(exitPname);
                updateQuantity.setText(exitPquantity);
            }
        });
        updateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateValue();
            }
        });
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qDecrease();
            }
        });
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qIncrease();
            }
        });
    }
    private void updateValue() {
        String exitPname = showName.getText().toString();
        final String newPname = updateName.getText().toString();
        final String newPquantity = updateQuantity.getText().toString().trim();
        AddData setData = new AddData(newPname, newPquantity);
        databaseReference.child(exitPname).setValue(setData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(UpdateActivity.this,"Successfully Updated!!",Toast.LENGTH_SHORT).show();
                    String exitQname = showQuantity.getText().toString();
                    showName.setText(newPname);
                    showQuantity.setText(newPquantity);
                    updateSection.setVisibility(View.GONE);
                    updateName.setText("");
                    updateQuantity.setText(exitQname);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateActivity.this,"Error Found",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void searchItem() {
        String sText = searchText.getText().toString();
        databaseReference.orderByChild("product_name").equalTo(sText).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    AddData value = ds.getValue(AddData.class);
                    showName.setText(value.getProduct_name().toString());
                    showQuantity.setText(value.getProduct_quantity().toString());
                    showItem.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    private void qIncrease() {
        String quantity = updateQuantity.getText().toString().trim();
        int nQuantity = Integer.parseInt(quantity) + 1;
        updateQuantity.setText(String.valueOf(nQuantity));
    }
    private void qDecrease() {
        String quantity = updateQuantity.getText().toString().trim();
        int nQuantity = Integer.parseInt(quantity) - 1;
        updateQuantity.setText(String.valueOf(nQuantity));
    }
}
