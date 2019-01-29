package com.lifeache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NameFactory {
	public static String Envi_Or_Loca = "./word/环境或地点.txt";
	public static String What_Uint = "./word/什么什么者.txt";
	public static String Character = "./word/特征或性格.txt";
	public static String Gene_Spec = "./word/一般物种.txt";
	public static String Civi_Spec = "./word/有文明的物种.txt";
	public static String Occu_Or_Stat = "./word/职业或地位.txt";
	public static String Part_OR_Obje = "./word/部位或物件.txt";
	public static String One_Word_Char = "./word/单字特征.txt";
	public static String Obje_Noun = "./word/物体名词.txt";
	 
	public static String Figure = "./word/人物.txt";
	public static String Des_Goo = "./word/人物描述褒.txt";
	public static String Des_Bad = "./word/人物描述贬.txt";
	public static String Des_HeH = "./word/人物描述中.txt";
	
	
	static int cnt = 7;
	static int itemCnt = 3;
	
	static HashMap<String, ArrayList<String>> map = new HashMap<>();
	
	String basePath = "./";
	
	public NameFactory(String bString) {
		basePath = bString;
		map.put(Envi_Or_Loca, getArray(Envi_Or_Loca));
		map.put(What_Uint, getArray(What_Uint));
		map.put(Character, getArray(Character));
		map.put(Gene_Spec, getArray(Gene_Spec));
		map.put(Civi_Spec, getArray(Civi_Spec));
		map.put(Occu_Or_Stat, getArray(Occu_Or_Stat));
		map.put(Part_OR_Obje, getArray(Part_OR_Obje));
		map.put(One_Word_Char, getArray(One_Word_Char));
		map.put(Obje_Noun, getArray(Obje_Noun));
		
		map.put(Figure, getArray(Figure));
		map.put(Des_Goo, getArray(Des_Goo));
		map.put(Des_Bad, getArray(Des_Bad));
		map.put(Des_HeH, getArray(Des_HeH));
	}
	
	public static void main(String[] args) {
		NameFactory nameFactory = new NameFactory("./");
		System.out.println("你遇到了：");
		for (int i = 0; i < cnt*10; i++) {
			System.out.println("    " +nameFactory.getRandomName());
		}
	}
	
	public String getValue(String key){
		List<String> list = map.get(key);
		return (String)list.get((int)(Math.random() * list.size()));
	}
	
	public String getRandomName(){
		return getName((int)(Math.random() * cnt));
	}
	
	public String getName(int type){
		
		switch(type){
			case 0 : 
				return getValue(Envi_Or_Loca) + getValue(What_Uint) + "者";
			case 1 :
				String name = getValue(Gene_Spec);
				if(name.length() == 1){
					name = "之" + name;
				}
				return getValue(Envi_Or_Loca) + name;
			case 2 :
				String name2 = getValue(Gene_Spec);
				if(name2.length() == 1){
					name2 = "之" + name2;
				}
				return getValue(Character) + name2;
			case 3 : 
				return getValue(Civi_Spec) + getValue(What_Uint) + "者";
			case 4 : 
				return getValue(Civi_Spec) + getValue(Occu_Or_Stat);
			case 5 :
				return getValue(One_Word_Char) + getValue(Part_OR_Obje) + getValue(What_Uint) + "者";
			case 6 :
				return getValue(Obje_Noun) + getValue(What_Uint) + "者";
		}
		return "未知单位";
	}
	public String getRandomItemName(){
		return getItemName((int)(Math.random() * itemCnt));
	}
	public String getItemName(int type){
		
		switch(type){
			case 0 : 
				return getValue(Figure) + "的" + getValue(Des_Goo);
			case 1 :
				return getValue(Figure) + "的" + getValue(Des_Bad);
			case 2 :
				return getValue(Figure) + "的" + getValue(Des_HeH);
//			case 3 : 
//				return getValue(What_Uint) + "者的" + getValue(Des_Goo);
//			case 4 : 
//				return getValue(What_Uint) + "者的" + getValue(Des_Bad);
//			case 5 :
//				return getValue(What_Uint) + "者的" + getValue(Des_HeH);
		}
		return "未知装备";
	}
	
	public ArrayList<String> getArray(String path) {
		ArrayList<String> arrayList = new ArrayList<>();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File(basePath,path));
		} catch (FileNotFoundException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		char[] cbuf = new char[1];
		int len = 0;
		boolean j = false;
		CharBuffer buffer = CharBuffer.allocate(1024);
		try {
			while ((len = bufferedReader.read(cbuf)) != -1) {

				for (int i = 0; i < len; i++) {
					if (cbuf[i] == ' ' || cbuf[i] == '\n' || cbuf[i] == '\r') {

						if (j) {
							buffer.flip();
							arrayList.add(new String(buffer.array(),0,buffer.length()));
							j = false;
							buffer = CharBuffer.allocate(1024);
						}
						continue;

					}
					j = true;
					buffer.append(cbuf[i]);

				}

			}

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		return arrayList;
	}
}
