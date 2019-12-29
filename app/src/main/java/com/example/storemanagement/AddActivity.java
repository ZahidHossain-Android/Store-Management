package com.example.storemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText productname,productquantity;
    Button button;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference requestRF = FirebaseDatabase.getInstance().getReference("Update Information");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        productname=findViewById(R.id.productname);
        productquantity=findViewById(R.id.productquantity);
        button=findViewById(R.id.savebtn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String product_name = productname.getText().toString();
                String product_quantity = productquantity.getText().toString();


                if (!TextUtils.isEmpty(product_name)
                        && !TextUtils.isEmpty(product_quantity)
                ){


                    String myCurrentDateTime = DateFormat.getDateTimeInstance()
                            .format(Calendar.getInstance().getTime());
                    AddData sendData = new AddData(product_name,product_quantity);

                    requestRF.child(myCurrentDateTime).setValue(sendData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                    Toast.makeText(AddActivity.this, "Add Successfully", Toast.LENGTH_SHORT).show();
                                    productname.setText("");
                                    productquantity.setText("");

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddActivity.this, "Error found", Toast.LENGTH_SHORT).show();
                        }
                    });



                }

            }
        });


    }
}
