package com.example.inboxmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import static android.widget.Toast.LENGTH_LONG;

public class IndividualMailView extends AppCompatActivity {
Button btnClose;
TextView txtByMail,txtDate,txtSubject,txtContext;
String idNumber;
    String[] myList;
    Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_mail_view);
        btnClose=findViewById(R.id.btnClose);
        txtByMail=findViewById(R.id.idSenderView);
        txtContext=findViewById(R.id.idContext);
        txtDate=findViewById(R.id.idDate);
        txtSubject=findViewById(R.id.idSubject);
        idNumber= Objects.requireNonNull(getIntent().getExtras()).getString("mailID");
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndividualMailView.this,InboxView.class));
                
            }
        });
        CheckMail checkMail = new CheckMail();// ShowAdmin.this is the Asynctask, which is used to process in background to reduce load on app process
        checkMail.execute("");
    }
    public class CheckMail extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String r) {
            Toast.makeText(IndividualMailView.this, r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Toast.makeText(IndividualMailView.this, "Login Successful", LENGTH_LONG).show();

            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected String doInBackground(String... params) {
            // @SuppressLint("WrongThread") String usernam = userTv.getText().toString();
            //@SuppressLint("WrongThread") String passwordd = passwordTv.getText().toString();

            try {
                con =new  ConnectionClass().CONN(); // Connect to database
                if (con == null) {
                    z = "Check Your Internet Access!";
                } else {
                    String query = "SELECT * FROM mailTable  WHERE mailID=" + idNumber + ";";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);


                    if (rs.next()) {

                        myList = new String[]{rs.getString("mailTitle"), rs.getString("mailBody"), rs.getString("mTimeDate"), rs.getString("read"), rs.getString("senderPfNumber")};


                        txtDate.setText("Date :" + myList[2]);
                        txtByMail.setText("Mail By :"+myList[4]);
                        txtSubject.setText(myList[0]);
                        txtContext.setText(myList[1]);
                        if (myList[3].equals("0")){
                            System.out.println("******************************************************************");
                            String query1 = "UPDATE mailTable SET [read]='1' WHERE mailID="+idNumber+" ;";
                            Statement stmt1 = con.createStatement();
                            int rs1 = stmt1.executeUpdate(query1);
                        }
                        //spliting the string, here, it seperate the latitiude and longitude

                    } else {
                        // you have no record

                    }


                }
            } catch (Exception ex) {
                isSuccess = false;
                z = ex.getMessage();
            }

            return z;


        }

    }
}