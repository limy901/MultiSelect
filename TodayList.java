import java.io.*;
import java.util.*;

class TodayList {
	String toDoList = "하고싶은일.txt";
	BufferedReader keybr = new BufferedReader(new InputStreamReader(System.in));
	FileReader fr;
	BufferedReader filebr;

	void readPath(){
		P.p("읽어올 파일을 입력해주세요. (Default : 하고싶은일.txt) : ");
		try {
			String path = keybr.readLine();
			if(path != null) path = path.trim();
			if(path.length() != 0) toDoList = path;
			fr = new FileReader(toDoList);
			filebr = new BufferedReader(fr);
			readFile();
		}catch(FileNotFoundException fe) {
			P.pln("입력하신 파일은 존재하지 않습니다.");
			readPath();
		}catch(IOException ie) {
			P.pln("다시 입력해주세요. ");
			readPath();
		}
	}
	
	Hashtable<Integer,String> map = new Hashtable<Integer, String> ();

	void readFile(){
		try{
			int count = 0;
			String line ="";
			while((line = filebr.readLine()) != null){
				map.put(count++,line);
			}
		}catch(IOException ie){
		}
		readListCnt();
	}
	int cntList;
	void readListCnt(){
		P.p("몇 가지 행동을 하고 싶나요? : ");
		String cntListStr = "";
		try{
			cntListStr = keybr.readLine();
		}catch(IOException ie){}

		if(cntListStr != null) cntListStr = cntListStr.trim();
		if(cntListStr.length() == 0){
			P.p("입력되지 않았어요. 입력해주세요.");
			readListCnt();
		}else{
			try{
				cntList = Integer.parseInt(cntListStr);
				if(cntList<0 || cntList>map.size()){
					P.pln("목록을 초과했네요. 다시 입력해주세요. 목록은 총"+map.size()+" 개 입니다.");
					readListCnt();
				}else{
					moveKeys();
					for(int i=0; i<cntList; i++) select();
					showList();
				}
			}catch(NumberFormatException ne){
				P.p("잘못된 입력이에요. 다시 입력해주세요.");
				readListCnt();
			}
		}
	}

	Vector<Integer> vList = new Vector<Integer>();

	void moveKeys(){
		Enumeration<Integer> enu = map.keys();
		while(enu.hasMoreElements()){
			vList.add(enu.nextElement());
		}
	}

	Random ran = new Random();
	TreeSet<Integer> sList = new TreeSet<Integer>();

	void select(){
		int i = ran.nextInt(vList.size());
		int listKey = vList.get(i);
		vList.remove(i);
		sList.add(listKey);
	}

	void showList(){
		Iterator<Integer> listKeys = sList.iterator();
		while(listKeys.hasNext()){
			int listKey = listKeys.next();
			//P.pln("내가 선택한 번호는 "+listKey+" 입니다.");
			P.pln("****  해야할 일 : "+map.get(listKey));
		}
	}

	public static void main(String[] args){
		TodayList tl = new TodayList();
		tl.readPath();
		P.pln("즐거운 마음으로 하루를 보내길 바랍니다!");
	}
}