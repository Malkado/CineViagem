package br.com.agillefilmes.View;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import br.com.agillefilmes.R;

public class CadastroActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    EditText EditUser;
    EditText EditEmail;
    EditText EditPass;
    EditText EditConfirmPass;
    EditText EditNome;

    CheckBox ShowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth=FirebaseAuth.getInstance();



        EditNome = findViewById(R.id.EdtNome);
        EditEmail= findViewById(R.id.EdtEmail);
        EditUser = findViewById(R.id.EdtUser);
        EditPass = findViewById(R.id.EdtPassword);
        EditConfirmPass= findViewById(R.id.EdtConPassword);
        ShowView = findViewById(R.id.checkbox_mostrarSenha);

        ShowView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked)
                {
                    EditPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    EditConfirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else
                    {
                    EditPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    EditConfirmPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

            }
        });
    }
    public void Salvar(View v)
    {
        final String nome = EditNome.getText().toString().trim();
        final String email = EditEmail.getText().toString().trim();
        final String usuario = EditUser.getText().toString().trim();
        final String password = EditPass.getText().toString().trim();
        String confirmPass = EditConfirmPass.getText().toString().trim();

        if (nome.equals(""))
        {
            EditNome.setError("Preencha este campo!");
            EditNome.requestFocus();
            return;
        }
        if (email.equals(""))
        {
            EditEmail.setError("Preencha este campo !");
            EditEmail.requestFocus();
            return;
        }
        if (usuario.equals("")){
            EditUser.setError("Preencha este campo !");
            EditUser.requestFocus();
            return;
        }
        if (password.equals("")){
            EditPass.setError("Preencha este campo !");
            EditPass.requestFocus();
            return;
        }
        if (confirmPass.equals("")){
            EditConfirmPass.setError("Preencha este campo !");
            EditConfirmPass.requestFocus();
            return;
        }
        /*if (password != confirmPass){

            EditConfirmPass.setError("Confirme a senha corretamente!");
            EditConfirmPass.requestFocus();
            return;
        }*/

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                   FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference userRef = database.getReference("Users/" + user.getUid());

                    Map<String,Object> userInfors = new HashMap<>();
                    userInfors.put("usuario", usuario);
                    userInfors.put("email",email);
                    userInfors.put("nome", nome);
                    userInfors.put("password",password);

                    userRef.setValue(userInfors);
                    finish();

                }else {
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                            EditPass.setError("Senha fraca!");
                            EditPass.requestFocus();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        EditEmail.setError("Email inválido!");
                        EditEmail.requestFocus();
                    }catch (Exception e){
                        Log.e("Cadastro" , e.getMessage());

                    }
                }

            }
        });
    }


}
