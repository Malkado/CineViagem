package br.com.agillefilmes.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.agillefilmes.R;

public class HomeFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_home, container,false);
    }
    public static HomeFragment newInstance()
    {
        return new HomeFragment();
    }
}
