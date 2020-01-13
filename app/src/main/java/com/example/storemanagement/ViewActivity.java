package com.example.storemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<ProductData> productDataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        listView = findViewById(R.id.ListViewID);

        productDataList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Update Information");



            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    productDataList.clear();
                    for (DataSnapshot productSnapshot : dataSnapshot.getChildren()){
                        ProductData value = productSnapshot.getValue(ProductData.class);

                        productDataList.add(value);
                    }
                    ProductListAdapter adapter = new ProductListAdapter(ViewActivity.this,productDataList);
                    listView.setAdapter(adapter);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }
}
