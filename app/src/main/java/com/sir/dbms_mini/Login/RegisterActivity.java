package com.sir.dbms_mini.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sir.dbms_mini.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText regname, regemail, regphone, regpwd, regdept;
    Button register;
    TextView regalready;
    FirebaseAuth reg_fauth;
    FirebaseFirestore reg_store;
    String userid_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regname = (EditText) findViewById(R.id.reg_name);
        regemail = (EditText) findViewById(R.id.reg_email);
        regphone = (EditText) findViewById(R.id.reg_phone);
        regpwd = (EditText) findViewById(R.id.reg_pwd);
        regdept = (EditText) findViewById(R.id.reg_dept);

        regalready = (TextView) findViewById(R.id.reg_already);

        register = (Button) findViewById(R.id.btn_reg);

        reg_fauth = FirebaseAuth.getInstance();
        reg_store = FirebaseFirestore.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = regname.getText().toString().trim();
                String email = regemail.getText().toString().trim();
                String password = regpwd.getText().toString().trim();
                String phone = regphone.getText().toString().trim();
                String dept = regdept.getText().toString().trim();

                if ((TextUtils.isEmpty(fname))
                    && (TextUtils.isEmpty(email))
                    && (TextUtils.isEmpty(password))
                    && (TextUtils.isEmpty(phone)))
                {
                    regname.setError("Name Required");
                    regemail.setError("Email required");
                    regpwd.setError("Password required");
                    regphone.setError("Phone Number Required");
                    return;
                }
                if (phone.length()>10
                        &&  phone.length()<10
                        && password.length()<6){
                    regpwd.setError("Password is weak");
                    regphone.setError("Number should be exact 10 digits");
                    return;
                }

                //register user in firebase
                reg_fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"User Created",Toast.LENGTH_SHORT).show();
                            userid_register = reg_fauth.getCurrentUser().getUid();
                            DocumentReference register = reg_store.collection("Users").document(userid_register);

                            Map<String,Object> item_register = new HashMap<>();
                            item_register.put("name",fname);
                            item_register.put("email",email);
                            item_register.put("phone",phone);
                            item_register.put("password",password);
                            item_register.put("department",dept);

                            register.set(item_register).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG", "onSuccess:user Profile is created" + userid_register);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG","onFailure:"+e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext() , LoginActivity.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error"+task.getException().getMessage() ,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        regalready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , LoginActivity.class));
            }
        });
    }
}