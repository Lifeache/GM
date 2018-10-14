package com.lifeache.og;
import android.text.style.*;
import android.view.*;
import android.text.*;
import android.graphics.*;
import java.util.logging.*;
import com.lifeache.gamecore.ui.*;

public class TextClicker extends ClickableSpan
{
	int id;
	public TextClicker(int id){
		this.id = id;
	}
	@Override
	public void onClick(View v)
	{
		DefaultDispatcher dd = DefaultDispatcher.getInstance();
		Game g = dd.getGame();
		Scene s = g.getCurrentScene();
        s = s.transferById(id);
		dd.setText(s);
        g.setCurrentScene(s);
	}

	@Override
	public void updateDrawState(TextPaint ds)
	{
		ds.setColor(Color.BLUE);
		ds.setUnderlineText(true);
	}
	
}
