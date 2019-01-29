package com.lifeache.og;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.graphics.*;
import android.text.*;
import android.text.method.*;
import java.util.*;
import com.lifeache.og.ui.*;
import android.view.View.*;
import android.view.*;

public class MainActivity extends Activity
{
	public void append(final String t)
	{
		hander.post(new Runnable(){
		public void run(){
			Clickable spStr = new Clickable(t,0);
			tv.append("\n");
			tv.append(spStr);
		};
		});
	}
	
	public void setText(final CharSequence t)
	{
		hander.post(new Runnable(){
				public void run(){
					tv.setText(t);
				};
			});
	}
	public TextView tv;
	DefaultDispatcher dd;
    Button gameButton;
    Button jsonButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initFragment();
       
		dd = DefaultDispatcher.getInstance();
		dd.setMainActivity(this);
        dd.init();
        gameButton = (Button)findViewById(R.id.mainButton1);
        jsonButton = (Button)findViewById(R.id.mainButton2);
      MyOnClickListener mocl = new MyOnClickListener();
        gameButton.setOnClickListener(mocl);
        jsonButton.setOnClickListener(mocl);
        /*
		tv = (TextView)frags.get(currentIndex).getView().findViewById(R.id.tv);
		tv.setTextSize(20);
		tv.setTextColor(Color.BLACK);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
		*/
        setTitle("什么");
        
		Dispatcher.setDefaultDispatcher(dd);
		Game g = dd.getGame();
		dd.setText(g.getStartScene());
        
    }
	
	Handler hander = new Handler();
    
    Vector<Fragment> frags = new Vector<Fragment>();
    int currentIndex = 0;
    public void initFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        frags.add(new GameFragment());
        frags.add(new JSONFragment());
        for (int i = 0; i < frags.size(); i++){
          Fragment f =  fm.findFragmentByTag(String.valueOf(i));
          if (f != null){
              ft.hide(f).commitAllowingStateLoss();
          }
        }
        showFragment(currentIndex);
    }
    
    public void showFragment(int index){
        Fragment cf = frags.get(currentIndex);
        Fragment nf = frags.get(index);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.hide(cf);
        if( ! nf.isAdded()){
           ft.add(R.id.gamefragFrameLayout1, nf,String.valueOf(index));
       }
       ft.show(nf);
       ft.commitAllowingStateLoss();
       currentIndex = index;
    }
    
    class MyOnClickListener implements   OnClickListener
    {

        @Override
        public void onClick(View p1)
        {
            if (p1 == gameButton){
                showFragment(0);
            }
            if(p1 == jsonButton){
                showFragment(1);
            }
        }

       
   }
}
