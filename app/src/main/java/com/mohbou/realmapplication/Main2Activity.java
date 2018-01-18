package com.mohbou.realmapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;

public class Main2Activity extends AppCompatActivity {


    private EditText mNameEditText;
    private EditText mFirstNameText;
    private EditText mFirstEmail;
    private EditText mSecondEmail;
    private CheckBox mActive1Checkbox;
    private CheckBox mActive2Checkbox;
    private Button mButtonSave;
    private TextView mLogTextView;


    private Realm mRealm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRealm = Realm.getDefaultInstance();


        mNameEditText= findViewById(R.id.last);
        mFirstNameText= findViewById(R.id.first);
        mFirstEmail= findViewById(R.id.email1);
        mSecondEmail= findViewById(R.id.email2);
        mActive1Checkbox= findViewById(R.id.email1_active);
        mActive2Checkbox= findViewById(R.id.email2_active);
        mButtonSave= findViewById(R.id.save);
        mLogTextView= findViewById(R.id.log);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
