package com.example.inboxmodule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
Button btnInboxAdmin,btnEmpInbox;
    String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEmpInbox=findViewById(R.id.btnEmpInbox);
        btnInboxAdmin=findViewById(R.id.btnInboxAdmin);
        btnInboxAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AdminInboxArea.class));
            }
        });
        btnEmpInbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View deleteDialogView = factory.inflate(R.layout.dialog_box_layout, null);
                final AlertDialog deleteDialog = new AlertDialog.Builder(MainActivity.this).create();
                deleteDialog.setView(deleteDialogView);
                 final EditText edPfNum=deleteDialogView.findViewById(R.id.idInboxPfNum);

                deleteDialogView.findViewById(R.id.btnInboxOk).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String st=edPfNum.getText().toString();
                       Intent intent=new Intent(MainActivity.this,InboxView.class);
                       intent.putExtra("pfNum",st);
                       startActivity(intent);
                    }
                });
                deleteDialogView.findViewById(R.id.btnInboxCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog.dismiss();
                    }
                });

                deleteDialog.show();

            }
        });
    }
}