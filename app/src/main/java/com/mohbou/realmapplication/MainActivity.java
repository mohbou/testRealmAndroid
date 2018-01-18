package com.mohbou.realmapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mFirstNameText;
    private Button mButtonSave;
    private TextView mLogTextView;
    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRealm =  Realm.getDefaultInstance();

        mButtonSave = findViewById(R.id.save);
        mNameEditText = findViewById(R.id.last);
        mFirstNameText = findViewById(R.id.first);
        mLogTextView = findViewById(R.id.log);

        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
                updateTextLog();

            }
        });

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

    private void createUser() {
        final User user = new User();
        user.setUserId(Math.random());
        user.setLastName(mNameEditText.getText().toString().trim());
        user.setFirstName(mFirstNameText.getText().toString().trim());

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(user);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }
}
