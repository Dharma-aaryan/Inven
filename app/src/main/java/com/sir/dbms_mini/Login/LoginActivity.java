package com.sir.dbms_mini.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sir.dbms_mini.HomePage.HomePageActivity;
import com.sir.dbms_mini.ExtraActivities.IntroPage;
import com.sir.dbms_mini.R;

public class LoginActivity extends AppCompatActivity {
    EditText lemail, lpwd;
    Button login, back;
    TextView lfp, ldonthave;
    FirebaseAuth login_fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lemail = (EditText) findViewById(R.id.login_email);
        lpwd = (EditText) findViewById(R.id.login_pwd);

        lfp = (TextView) findViewById(R.id.login_forgotpwd);
        ldonthave = (TextView) findViewById(R.id.login_donthaveaccount);

        login = (Button) findViewById(R.id.btn_login);
        back = (Button) findViewById(R.id.btn_back);

        login_fauth = FirebaseAuth.getInstance();

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_email = lemail.getText().toString().trim();
                String login_pwd = lpwd.getText().toString().trim();

                if (TextUtils.isEmpty( login_email )
                        && TextUtils.isEmpty(login_pwd )
                        && (login_pwd.length() < 6)) {
                    lemail.setError("Email is required");
                    lpwd.setError("Password is required");
                    lpwd.setError("Password is weak");
                    return;
                }
                //authentication
                login_fauth.signInWithEmailAndPassword( login_email, login_pwd ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText( getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT ).show();
                            startActivity( new Intent( getApplicationContext(), HomePageActivity.class ) );
                        } else {
                            Toast.makeText( getApplicationContext(), "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );
            }
        });

        lfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = lemail.getText().toString().trim();
                if (TextUtils.isEmpty(mail)){
                    lemail.setError("Please Enter Your Email");
                }
                else {
                    String input = lemail.getText().toString().trim();
                    login_fauth.getInstance().sendPasswordResetEmail(input)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Reset Link Sent to Your Email", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        ldonthave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), IntroPage.class));
            }
        });
    }
}