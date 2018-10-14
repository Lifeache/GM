package com.lifeache.og;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.graphics.*;
import android.text.*;
import android.text.method.*;

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

	TextView tv;
	DefaultDispatcher dd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		dd = DefaultDispatcher.getInstance();
		dd.setMainActivity(this);
		tv = (TextView)findViewById(R.id.tv);
		tv.setTextSize(20);
		tv.setTextColor(Color.BLACK);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
		setTitle("什么");
        
		Dispatcher.setDefaultDispatcher(dd);
		Game g = dd.getGame();
		dd.setText(g.getStartScene());
    }
	
	Handler hander = new Handler();
}
