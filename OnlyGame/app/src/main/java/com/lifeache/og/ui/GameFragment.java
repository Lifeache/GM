package com.lifeache.og.ui;
import android.app.*;
import android.os.*;
import android.view.*;
import com.lifeache.og.*;
import android.widget.*;
import android.graphics.*;
import android.text.method.*;

public class GameFragment extends Fragment
{
    public GameFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        DefaultDispatcher dd = DefaultDispatcher.getInstance();
        MainActivity ma = dd.getMainActivity();
        View v = inflater.inflate(R.layout.game_frag,container,false);
        ma.tv = (TextView) v.findViewById(R.id.tv);
        ma.tv.setTextSize(20);
        ma.tv.setTextColor(Color.BLACK);
        ma.tv.setMovementMethod(LinkMovementMethod.getInstance());
		
        return v;
    }
    
}
