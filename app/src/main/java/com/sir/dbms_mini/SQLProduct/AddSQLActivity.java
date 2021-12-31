package com.sir.dbms_mini.SQLProduct;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sir.dbms_mini.R;

public class AddSQLActivity extends AppCompatActivity {
    EditText name, contact, course,email,exam,qualification;
    Button insert, update, delete, view;
    DBHolder DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sql);
        name = findViewById(R.id.name);
        email=findViewById( R.id.email );
        contact = findViewById(R.id.contact );
        course = findViewById(R.id.course );
        exam=findViewById( R.id.exam );
        qualification=findViewById( R.id.qualification );

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBHolder(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String emailTXT=email.getText().toString();
                String contactTXT = contact.getText().toString();
                String courseTXT = course.getText().toString();
                String examTXT = exam.getText().toString();
                String qualificationTXT = qualification.getText().toString();


                Boolean checkinsertdata = DB.insertuserdata(nameTXT,emailTXT,contactTXT, courseTXT,examTXT,qualificationTXT);
                if(checkinsertdata==true)
                    Toast.makeText(getApplicationContext(), "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                name.setText( " " );
                email.setText( " " );
                contact.setText( " " );
                course.setText( " " );
                exam.setText( " " );
                qualification.setText( " " );
            }

        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String emailTXT=email.getText().toString();
                String contactTXT = contact.getText().toString();
                String courseTXT = course.getText().toString();
                String examTXT = exam.getText().toString();
                String qualificationTXT = qualification.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, courseTXT,emailTXT,examTXT,qualificationTXT);
                if(checkupdatedata==true)
                    Toast.makeText(getApplicationContext(), "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                name.setText( " " );
                email.setText( " " );
                contact.setText( " " );
                course.setText( " " );
                exam.setText( " " );
                qualification.setText( " " );
            }

        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(getApplicationContext(), "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                name.setText( " " );
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(getApplicationContext(), "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Email :"+res.getString(1)+"\n");
                    buffer.append("Contact :"+res.getString(2)+"\n");
                    buffer.append("Course Name :"+res.getString(3)+"\n");
                    buffer.append("Exam Name :"+res.getString(4)+"\n");
                    buffer.append("Qualification :"+res.getString(5)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AddSQLActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}