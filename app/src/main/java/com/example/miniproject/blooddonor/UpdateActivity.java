package com.example.miniproject.blooddonor;
/**
 * Created by AzMak Pirates on 11/3/2015.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText editTextUserName,editTextPassword,editTextConfirmPassword,
            firstName2,lastName2,contactNo2,address2;
    String bloodGroup;
    Button btnCreateAccount;
    LoginDataBaseAdapter loginDataBaseAdapter;
    Spinner spinner;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        // get Instance  of Database Adapter
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        // Get Refferences of Views
        editTextUserName=(EditText)findViewById(R.id.editTextUserName);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);
        address2 =(EditText)findViewById(R.id.address);
        firstName2 = (EditText)findViewById(R.id.firstName);
        lastName2 = (EditText)findViewById(R.id.lastName);
        contactNo2 =(EditText)findViewById(R.id.contactNo);

        spinner = (Spinner)findViewById(R.id.spinner);
        String[] items = new String[]{"","A+","A-","B+","B-","O+","O-","AB+","AB-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        btnCreateAccount=(Button)findViewById(R.id.buttonCreateAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();
                bloodGroup = spinner.getSelectedItem().toString();
                String firstName = firstName2.getText().toString();
                String lastName = lastName2.getText().toString();
                String contactNo = contactNo2.getText().toString();
                String address = address2.getText().toString();

                // check if any of the fields are vaccant
                if(userName.equals("")||password.equals("")||confirmPassword.equals("")
                        ||firstName.equals("")||lastName.equals("")||contactNo.equals("")||address.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                // check if both password matches
                if(!password.equals(confirmPassword))
                {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                if(contactNo.length()!=10){
                    Toast.makeText(getApplicationContext(), "Enter correct contact no", Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    // Save the Data in Database
                    loginDataBaseAdapter.updateEntry(userName, password);
                    loginDataBaseAdapter.updateEntry2(firstName, lastName, contactNo, address, bloodGroup,id);
                    Toast.makeText(getApplicationContext(), "Account Updated Successfully ", Toast.LENGTH_LONG).show();
                    Intent intentLogin = new Intent(getApplicationContext(), spinner.class);
                    startActivity(intentLogin);
                    finish();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
