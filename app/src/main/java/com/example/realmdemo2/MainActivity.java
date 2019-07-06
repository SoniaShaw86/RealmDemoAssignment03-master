package com.example.realmdemo2;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private EditText name;
    private EditText dept;
    private EditText phone;
    private EditText roll;
    private Switch gen;
    private String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        name = findViewById(R.id.main_name_et);
        dept = findViewById(R.id.main_dept_et);
        phone = findViewById(R.id.main_phone_et);
        roll = findViewById(R.id.main_roll_et);
        gen=findViewById(R.id.genderSwitch);
    }

    public void onSavePressed(View view){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        try {
            final Person person = realm.createObject(Person.class, System.currentTimeMillis() / 1000);
            person.setName(name.getText().toString());
            person.setDept(dept.getText().toString());
            person.setPhone(phone.getText().toString());
            person.setRoll(Integer.parseInt(roll.getText().toString()));
            if(gen.isChecked()) {
                person.setGender(gen.getTextOn().toString());
            }
            else
            {
                person.setGender(gen.getTextOff().toString());
            }
            realm.commitTransaction();
            Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex){
            realm.cancelTransaction();
            Toast.makeText(mContext, "Failure" + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public void onDisplayButtonPressed(View view){
        Intent intent = new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }
}
