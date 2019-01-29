package com.lifeache.gamecore.ui;
import java.util.*;

public class ShowableBuilder
{
	Vector<Showable> showables = new Vector<Showable>();
	
	public ShowableBuilder(){
		
	}
	
	public ShowableBuilder(String str){
		append(str);
	}
	
	public ShowableBuilder(ShowableBuilder sb){
		append(sb);
	}
	
	public ShowableBuilder(Showable s){
		append(s);
	}
	
	public ShowableBuilder append(Showable showable){
		showables.add(showable);
		return this;
	}
	
	public ShowableBuilder append(String text){
		Text t = new Text(text);
		showables.add(t);
		return this;
	}
	
	public ShowableBuilder append(ShowableBuilder sb){
		for(int i = 0; i < sb.size(); i++){
			append(sb.get(i));
		}
		return this;
	}
	
	public ShowableBuilder appendln(String text){
		append(text + "\n");
		return this;
	}
    
    public ShowableBuilder appendln(){
        append("\n");
        return this;
	}
	
	public Showable get(int location){
		return showables.get(location);
	}
	
	public int size(){
		return showables.size();
	}
    
    public void clear(){
        showables.clear();
    }
}
