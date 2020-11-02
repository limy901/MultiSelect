import java.io.*;
import java.util.*;

class TodayList {
	String toDoList = "�ϰ������.txt";
	BufferedReader keybr = new BufferedReader(new InputStreamReader(System.in));
	FileReader fr;
	BufferedReader filebr;

	void readPath(){
		P.p("�о�� ������ �Է����ּ���. (Default : �ϰ������.txt) : ");
		try {
			String path = keybr.readLine();
			if(path != null) path = path.trim();
			if(path.length() != 0) toDoList = path;
			fr = new FileReader(toDoList);
			filebr = new BufferedReader(fr);
			readFile();
		}catch(FileNotFoundException fe) {
			P.pln("�Է��Ͻ� ������ �������� �ʽ��ϴ�.");
			readPath();
		}catch(IOException ie) {
			P.pln("�ٽ� �Է����ּ���. ");
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
		P.p("�� ���� �ൿ�� �ϰ� �ͳ���? : ");
		String cntListStr = "";
		try{
			cntListStr = keybr.readLine();
		}catch(IOException ie){}

		if(cntListStr != null) cntListStr = cntListStr.trim();
		if(cntListStr.length() == 0){
			P.p("�Էµ��� �ʾҾ��. �Է����ּ���.");
			readListCnt();
		}else{
			try{
				cntList = Integer.parseInt(cntListStr);
				if(cntList<0 || cntList>map.size()){
					P.pln("����� �ʰ��߳׿�. �ٽ� �Է����ּ���. ����� ��"+map.size()+" �� �Դϴ�.");
					readListCnt();
				}else{
					moveKeys();
					for(int i=0; i<cntList; i++) select();
					showList();
				}
			}catch(NumberFormatException ne){
				P.p("�߸��� �Է��̿���. �ٽ� �Է����ּ���.");
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
			//P.pln("���� ������ ��ȣ�� "+listKey+" �Դϴ�.");
			P.pln("****  �ؾ��� �� : "+map.get(listKey));
		}
	}

	public static void main(String[] args){
		TodayList tl = new TodayList();
		tl.readPath();
		P.pln("��ſ� �������� �Ϸ縦 ������ �ٶ��ϴ�!");
	}
}