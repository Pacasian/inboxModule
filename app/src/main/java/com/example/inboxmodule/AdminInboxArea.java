package com.example.inboxmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdminInboxArea extends AppCompatActivity {
Button btnMailSend;
EditText edTitle,edBody,pfNumber;
    /**
     * UNCOMMENT WHILE INTEGRATION
     *  private RailwaySharedPreference sharedInfo;
     */

    Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_inbox_area);
        edTitle=findViewById(R.id.idMailTitle);
        edBody=findViewById(R.id.idMailBody);
        /**
         * UNCOMMENT WHILE INTEGRATION
         *  sharedInfo=RailwaySharedPreference.getInstance(getApplicationContext());
         */
        pfNumber=findViewById(R.id.receiverPfNumber);
        btnMailSend=findViewById(R.id.btnInboxSend);
        con = new ConnectionClass().CONN();
        if (con != null) {
            System.out.println("-------------------------------");
            System.out.println("Connection valid");
            System.out.println("-------------------------------");

        }
        else{
            System.out.println("-------------------------------");
            System.out.println("Connection invalid");
            System.out.println("-------------------------------");
        }

    btnMailSend.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        UploadMail mailUpload = new UploadMail();
        mailUpload.execute("");
    }
});

    }
    public class UploadMail extends AsyncTask<String, String, String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String r) {
            Toast.makeText(AdminInboxArea.this, r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Toast.makeText(AdminInboxArea.this, "Login Successful", Toast.LENGTH_LONG).show();

            }
        }

        @Override
        protected String doInBackground(String... params) {

            try
            {

                String title=edTitle.getText().toString();
                String body=edBody.getText().toString() ;
                String pfNUmber= pfNumber.getText().toString();
                String readMsg= "0";
                String senderPfNumber="123456789";
                /**
                 * UNCOMMENT WHILE INTEGRATION
                 *
                 * sharedInfo=RailwaySharedPreference.getInstance(getApplicationContext());
                 * String senderPfNumber = sharedInfo.get("pfNumber");
                 */

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String currentDate = sdf.format(new Date());
                System.out.println("-------------------------------");
                System.out.println(currentDate);
                System.out.println("-------------------------------");

                String query = "insert into mailTable (senderPfNumber,mailTitle,receverPfNumber,mailBody,mTimeDate,[read]) values ('" + senderPfNumber + "','" + title + "','" + pfNUmber + "','" + body + "','" + currentDate + "','" + readMsg + "');";

                Statement stmt = con.createStatement();
                System.out.println("-------------------------------");
                System.out.println("Sucesss");
                System.out.println("-------------------------------");
                stmt.executeQuery(query);
            }
            catch (SQLException se)
            {
                Log.e("ERROR", se.getMessage());
            }
            return "suuss";
        }
    }
}
