package com.mohbou.realmapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;

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


        mButtonSave.setOnClickListener(view -> mRealm.executeTransactionAsync(bgRealm -> {
            User user = bgRealm.createObject(User.class);
            user.setLastName(mNameEditText.getText().toString().trim());
            user.setFirstName(mFirstNameText.getText().toString().trim());

            Email email1 = bgRealm.createObject(Email.class);
            email1.setAddress(mFirstEmail.getText().toString().trim());
            email1.setActive(mActive1Checkbox.isChecked());

            Email email2 = bgRealm.createObject(Email.class);
            email2.setAddress(mSecondEmail.getText().toString().trim());
            email2.setActive(mActive2Checkbox.isChecked());

            user.getEmail().add(email1);
            user.getEmail().add(email2);

        }, this::updateTextLog, error -> {
            // Transaction failed and was automatically canceled.
            Toast.makeText(Main2Activity.this, "Failed", Toast.LENGTH_SHORT).show();
        }));

    }

    private void updateTextLog() {
        mLogTextView.setText("");
        final RealmResults<User> users = mRealm.where(User.class).findAll();
        for (User user:
                users) {
            Log.d("Name", "updateTextLog: user "+user.getFirstName());
            mLogTextView.setText(mLogTextView.getText().toString()+user.toString());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
