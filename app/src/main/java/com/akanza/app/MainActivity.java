package com.akanza.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{

    private EditText edtText;
    private EditText edtNumberPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtText = (EditText) findViewById(R.id.edtText);
        edtNumberPhone = (EditText) findViewById(R.id.edtNumberPhone);
    }

    public void sendSms(View view)
    {
        // TODO send later
    }
}
