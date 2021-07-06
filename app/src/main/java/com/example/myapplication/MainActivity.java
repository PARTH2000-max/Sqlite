package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DbHelper db;

    EditText eid,ename,eemail,emobile;

    Button binsert,bview,bdelete,bupdate,bsearch;

    String id,name,email,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eid = findViewById(R.id.etid);
        ename = findViewById(R.id.etname);
        eemail = findViewById(R.id.etemail);
        emobile = findViewById(R.id.etmobile);

        binsert = findViewById(R.id.binsert);
        bview = findViewById(R.id.bview);
        bdelete = findViewById(R.id.bdelete);
        bupdate = findViewById(R.id.bupdate);
        bsearch = findViewById(R.id.bserach);

        binsert.setOnClickListener(this);
        bview.setOnClickListener(this);
        bdelete.setOnClickListener(this);
        bupdate.setOnClickListener(this);
        bsearch.setOnClickListener(this);

        db = new DbHelper(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.binsert:
                name = ename.getText().toString();
                email = eemail.getText().toString();
                mobile = emobile.getText().toString();

                if (name.equals("") | email.equals("") | mobile.equals(""))
                {
                    Toast.makeText(this,"Please fill the fileds",Toast.LENGTH_LONG).show();

                }
                else
                {
                    db.insertStudent(name,email,mobile);
                    ename.setText("");
                    eemail.setText("");
                    emobile.setText("");
                    Toast.makeText(this,"Saved Successfully",Toast.LENGTH_LONG).show();

                }

                break;

            case R.id.bview:
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);

                startActivity(intent);

            case R.id.bdelete:
                id = eid.getText().toString();

                if (id.equals(""))
                {
                    Toast.makeText(this,"Please fill the id",Toast.LENGTH_LONG).show();

                }
                else {
                    long l = Long.parseLong(id);
                    db.deleteStudent(l);
                    eid.setText("");
                    ename.setText("");
                    eemail.setText("");
                    emobile.setText("");

                    Toast.makeText(this, "delete successfully", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.bupdate:
                id = eid.getText().toString();
                name = emobile.getText().toString();
                email = eemail.getText().toString();
                mobile = emobile.getText().toString();

                if (id.equals("")| name.equals("") | email.equals("") | mobile.equals(""))
            {
                Toast.makeText(this,"Please fill the fileds",Toast.LENGTH_LONG).show();

            }
                else {
                    long l = Long.parseLong(id);
                    db.updateStudent(l,name,email,mobile);
                    eid.setText("");
                    ename.setText("");
                    eemail.setText("");
                    emobile.setText("");

                    Toast.makeText(this,"Update Successfully",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.bserach:
                id = eid.getText().toString();
                if (id.equals(""))
            {
                Toast.makeText(this, "Please fill the fields", Toast.LENGTH_SHORT).show();
            }

                else {
                    try {
                        long l1= Long.parseLong(id);
                        name=db.getName(l1);
                        email=db.getEmail(l1);
                        mobile=db.getMobile(l1);

                        ename.setText(name);
                        eemail.setText(email);
                        emobile.setText(mobile);
                    }

                    catch (Exception e)
                    {
                        Toast.makeText(this, "id is not available", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
        }

    }
}