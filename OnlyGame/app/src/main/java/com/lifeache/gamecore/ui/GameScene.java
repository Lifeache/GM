package com.lifeache.gamecore.ui;
import java.util.*;

public class GameScene extends Scene
{
    public static final int NORTH = 0;
    public static final int WEST = 1;
    public static final int EAST = 2;
    public static final int SOUTH = 3;
    String name;
    int id;
    String text;
    GameScene[] dirsScene;
   Vector<String> monsters;
    Vector<String> items;
    Vector<String> npcs;

    public void setNpcs(Vector<String> npcs)
    {
        this.npcs = npcs;
    }

    public Vector<String> getNpcs()
    {
        return npcs;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }
   
   public void create(){
       clear();
       ShowableBuilder sb = new ShowableBuilder();
       sb.appendln("你来到了『"+name+"』");
       
       sb.appendln(text);
       if(monsters != null && !monsters.isEmpty()) {
           sb.appendln("你遇到了：");
           for(int i = 0; i < monsters.size();i++){
               String mn = monsters.get(i);
               Button m = new Button(mn,SOUTH + i);
               sb.append(m);
               sb.appendln();
           }
       }
       if(npcs != null && !npcs.isEmpty()) {
           sb.appendln("你遇到了：");
           for(int i = 0; i < npcs.size();i++){
               String mn = npcs.get(i);
               Button m = new Button(mn,SOUTH + i + 1);
               sb.append(m);
               sb.appendln();
           }
       }
       if(items != null && !items.isEmpty()) {
           sb.appendln("这里有：");
           for(int i = 0; i < items.size();i++){
               String mn = items.get(i);
               Button m = new Button(mn,SOUTH + i + 1);
               sb.append(m);
               sb.appendln();
           }
       }
       if (dirsScene[NORTH] != null){
       sb.append("北 ");
       Button nb = new Button(dirsScene[NORTH].getName(),NORTH);
       sb.append(nb);
       sb.appendln();
       }
       if (dirsScene[WEST] != null){
           sb.append("西 ");
       Button wb = new Button(dirsScene[WEST].getName(),WEST);
       sb.append(wb);
       sb.appendln();
       }
       if (dirsScene[EAST] != null){
           sb.append("东 ");
       Button eb = new Button(dirsScene[EAST].getName(),EAST);
       sb.append(eb);
       sb.appendln();
       }
       if (dirsScene[SOUTH] != null){
           sb.append("南 ");
       Button sob = new Button(dirsScene[SOUTH].getName(),SOUTH);
       sb.append(sob);
       sb.appendln();
       }
       
       append(sb);
       setSceneTransfer(new SceneTransfer(){

               @Override
               public Scene transferTo(int id)
               {
                   GameScene gs = GameScene.this;
                   if (id == GameScene.NORTH){
                       gs = dirsScene[GameScene.NORTH];
                   } else if (id == GameScene.WEST){
                       gs = dirsScene[GameScene.WEST];
                   } else if (id == GameScene.EAST){
                       gs = dirsScene[GameScene.EAST];
                   } else if (id == GameScene.SOUTH){
                       gs = dirsScene[GameScene.SOUTH];
                   }
                   if(gs != null){
                       gs.create();
                   } else {
                      gs = GameScene.this;
                   }
                  
                   return gs;
               }

           
       });
       
   }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setDirsScene(GameScene[] dirsScene)
    {
        this.dirsScene = dirsScene;
    }

    public GameScene[] getDirsScene()
    {
        return dirsScene;
    }

    public void setMonsters(Vector<String> monsters)
    {
        this.monsters = monsters;
    }

    public Vector<String> getMonsters()
    {
        return monsters;
    }

    public void setItems(Vector<String> items)
    {
        this.items = items;
    }

    public Vector<String> getItems()
    {
        return items;
    }
    }
