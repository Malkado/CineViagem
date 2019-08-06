package br.com.agillefilmes.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.agillefilmes.Fragment.HomeFragment;
import br.com.agillefilmes.Fragment.SeachFragment;
import br.com.agillefilmes.Fragment.SettingsFragment;
import br.com.agillefilmes.R;

public class ActivitySelection extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mAuth =FirebaseAuth.getInstance();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Fragment homefragment = HomeFragment.newInstance();
                    openFragment(homefragment);

                    break;
                case R.id.navegation_seach:

                    Fragment seachfragment = SeachFragment.newInstance();
                    openFragment(seachfragment);

                    break;
                case R.id.navegation_settings:

                    Fragment settingsfragments = SettingsFragment.newInstance();
                    openFragment(settingsfragments);

                    break;
            }
            return false;
        }
    };
    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout , fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

    }

    public void logout(View view){
        mAuth.getInstance().signOut();
        FirebaseUser user= mAuth.getCurrentUser();
        UpdateUI(user);
    }
    public void UpdateUI(FirebaseUser user)
    {
        if(user==null){
            Intent intent= new Intent(ActivitySelection.this, MainActivity.class);
            startActivity(intent);
        }
    }

}
