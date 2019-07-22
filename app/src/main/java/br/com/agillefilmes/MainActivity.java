package br.com.agillefilmes;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {
    EditText EditEmail;
    EditText EditPassword;
    CheckBox ShowView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //EditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

        EditEmail=findViewById(R.id.EdtEmail);
        EditPassword=findViewById(R.id.EdtPassword);
        ShowView=findViewById(R.id.checkbox_mostrarSenha);

        ShowView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                {
                    EditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //EditPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else
                {
                   // EditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    EditPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });



    }
    public void video(View v ){
        Intent intent= new Intent(MainActivity.this, ViewActivity.class);
        startActivity(intent);
    }
    public void buttonText(View v){
        Toast.makeText(this,"Manda uma mensagem dizendo o que achou",Toast.LENGTH_LONG).show();
    }

    protected void onStart(){
        super.onStart();
    }

}
