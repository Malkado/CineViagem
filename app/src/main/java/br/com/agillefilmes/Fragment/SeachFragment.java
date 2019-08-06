package br.com.agillefilmes.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

import br.com.agillefilmes.R;

public class SeachFragment extends Fragment {
    @Override
    public View onCreateView (LayoutInflater inflater , ViewGroup container ,
                              Bundle saveInstanceState)
    {
        return inflater.inflate(R.layout.fragment_settings , container , false );
    }
    public static SeachFragment newInstance()
    {
        return new SeachFragment();
    }
}
