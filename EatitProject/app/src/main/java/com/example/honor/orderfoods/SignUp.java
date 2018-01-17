package com.example.honor.orderfoods;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.honor.orderfoods.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    MaterialEditText edtPhone, edtName, edtPassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        edtName = (MaterialEditText)findViewById(R.id.edtName);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        FirebaseDatabase database =FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Please waiting...");
                progressDialog.show();
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            progressDialog.dismiss();
                            Toast.makeText(SignUp.this, "Phone Number already register!", Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            User user = new User(edtName.getText().toString(), edtPassword.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "SignUp successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}
