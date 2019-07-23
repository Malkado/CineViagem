package br.com.agillefilmes.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.agillefilmes.R;

public class MainActivity extends AppCompatActivity {
    EditText EditEmail;
    EditText EditPassword;
    CheckBox ShowView;
    private FirebaseAuth mAuth;


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

        mAuth= FirebaseAuth.getInstance();

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
    public void video(View v )
    {
        Intent intent= new Intent(MainActivity.this, ViewActivity.class);
        startActivity(intent);
    }
    public void ExcuteLogin(View v )
    {
        String email = EditEmail.getText().toString().trim();
        String password= EditPassword.getText().toString().trim();
        if (email.equals(""))
        {
            EditEmail.setError("Preencha este campo!!");
            return;
        }
        if (password.equals(""))
        {
            EditPassword.setError("Preencha este campo!!");
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    UpdateUI(mAuth.getCurrentUser());
                }else{
                    Toast.makeText(MainActivity.this,"Usuário ou senha incorreta!",Toast.LENGTH_SHORT).show();
                    UpdateUI(null);
                }

            }
        });


    }
    public void buttonText(View v){
        Toast.makeText(this,"Manda uma mensagem dizendo o que achou",Toast.LENGTH_LONG).show();
    }
    public void UpdateUI(FirebaseUser user)
    {
        if(user!=null){
            Intent intent= new Intent(MainActivity.this, ViewActivity.class);
            startActivity(intent);
        }
    }
    public void SelectSingUP(View view){
        startActivity(new Intent(MainActivity.this, CadastroActivity.class));
    }
    protected void onStart(){

        super.onStart();
        FirebaseUser currentUser= mAuth.getCurrentUser();
        UpdateUI (currentUser);
    }


}
