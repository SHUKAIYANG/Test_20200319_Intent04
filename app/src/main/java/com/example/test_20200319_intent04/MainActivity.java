package com.example.test_20200319_intent04;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText editTextInput;
    private ImageView imageButtonPhone, imageButtonEmail, imageButtonHttp, imageButtonSearch;
    private Button buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context =  this;

        editTextInput = (EditText) findViewById(R.id.editText_input);
        editTextInput.setText("");

        imageButtonPhone = (ImageView) findViewById(R.id.imageButton_phone);

        imageButtonEmail = (ImageView) findViewById(R.id.imageButton_email);

        imageButtonHttp = (ImageView) findViewById(R.id.imageButton_http);

        imageButtonSearch = (ImageView) findViewById(R.id.imageButton_search);

        imageButtonPhone.setOnClickListener(new MyButton());
        imageButtonEmail.setOnClickListener(new MyButton());
        imageButtonHttp.setOnClickListener(new MyButton());
        imageButtonSearch.setOnClickListener(new MyButton());

        buttonClear = (Button) findViewById(R.id.button_clear);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextInput.setText("");
            }
        });

    } // onCreate()

    private class MyButton implements View.OnClickListener {

        private String action;
        private String data;
        private Intent intent;
        private Uri uri;

        @Override
        public void onClick(View v) {

            data = editTextInput.getText().toString();

            if(editTextInput.length() == 0){
                Toast.makeText(context, "Please input data.", Toast.LENGTH_SHORT).show();

            }else {

                switch (v.getId()) {
                    case R.id.imageButton_phone:
                        action = Intent.ACTION_DIAL;
                        uri = Uri.parse("tel:" + data);  // ""雙引號內的字串要小寫且不能有空格
                        intent = new Intent(action, uri);
                        startActivity(intent);
                        break;

                    case R.id.imageButton_email:
                        action = Intent.ACTION_SENDTO;
                        uri = Uri.parse("mailto:" + data);
                        intent = new Intent(action, uri);
                        startActivity(intent);
                        break;

                    case R.id.imageButton_http:
                        action = Intent.ACTION_VIEW;
                        uri = Uri.parse("http:" + data);
                        intent =  new Intent(action, uri);
                        startActivity(intent);
                        break;

                    case R.id.imageButton_search:
                        action = Intent.ACTION_WEB_SEARCH;
                        intent = new Intent(action);
                        intent.putExtra(SearchManager.QUERY, data);
                        startActivity(intent);
                        break;


                }// switch

            } //  else


        } //  onClick(View v)

    }// class MyButton implements View.OnClickListener

}//  class main

