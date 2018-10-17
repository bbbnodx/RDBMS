import java.util.*;

public class Table {
	private String name = new String();		// テーブルの名前(小文字)
	// レコード集合
	private ArrayList<HashMap<String, Object>> records = new ArrayList<HashMap<String, Object>>();
	
	// フィールド集合(すべて小文字の文字列とする)
	private ArrayList<String> fields;
	
	// 各フィールドにおけるデータの型を記憶する(小文字)
	private ArrayList<String> types;
	
	public Table(){	
		fields = new ArrayList<String>();
		types = new ArrayList<String>();
	}
	
	public Table(String name, ArrayList<String> fields, ArrayList<String> types){
		this.name = name.toLowerCase();
		this.fields = new ArrayList<String>(fields);
		this.types = new ArrayList<String>(types);
		for(int i=0; i<fields.size(); i++){
			this.fields.set(i, fields.get(i).toLowerCase());
			this.types.set(i, types.get(i).toLowerCase());
		}
	}
	
	public void setName(String name){
		this.name = name.toLowerCase();
	}
	
	public String getName(){
		return name;
	}
	
	public void setRecords(ArrayList<HashMap<String, Object>> records){
		this.records = records;
	}
	
	public ArrayList<HashMap<String, Object>> getRecords(){
		return records;
	}
	
	public void addRecord(HashMap<String, Object> record){
		records.add(record);
	}
	
	public void setFields(ArrayList<String> fields){
		if(records.isEmpty()){
			this.fields = fields;
			for(int i=0; i<fields.size(); i++){
				this.fields.set(i, fields.get(i).toLowerCase());
			}
		// レコードが存在するなら，setFieldsによる変更は許可しない	
		}else{
			System.out.println("Table \"" + name + "\" has records.\nPlease use the \"changeField\"method");
		}	
	}
	
	public void changeField(String oldField, String newField){
		// テーブルがoldFieldをもっていて，かつnewFieldをもっていないときに限る
		if(fields.contains(oldField) && !fields.contains(newField.toLowerCase())){
			fields.set(fields.indexOf(oldField), newField.toLowerCase());
			for(int i=0; i<records.size(); i++){
				records.get(i).put(newField.toLowerCase(), records.get(i).get(oldField));
				records.get(i).remove(oldField);
			}
		}
	}
	
	public ArrayList<String> getFields(){
		return fields;
	}
	
	public void setTypes(ArrayList<String> types){
		this.types = types;
		for(int i=0; i<fields.size(); i++){
			this.types.set(i, types.get(i).toLowerCase());
		}
	}
	
	public ArrayList<String> getTypes(){
		return types;
	}
	
	// レコードの数を返す
	public int getCardinality(){
		return records.size();
	}
	
	// フィールドの数を返す
	public int getDegree(){
		return fields.size();
	}
	
	public boolean isEmpty(){
		if(fields.isEmpty() || types.isEmpty())
			return true;
		else
			return false;
	}
	
	// テーブルの出力
	public void printTable(){
		System.out.println("Print table \"" + name + "\"");
		for(int i=0; i<fields.size(); i++){
			System.out.printf("%15s |", fields.get(i));
		}
		System.out.println();
		for(int i=0; i<fields.size()*17; i++){
			System.out.print("-");
		}		
		System.out.println();
		for(int i=0; i<fields.size(); i++){
			System.out.printf("%15s |", types.get(i));
		}
		System.out.println();
		for(int i=0; i<fields.size()*17; i++){
			System.out.print("-");
		}		
		System.out.println();
		for(int i=0; i<records.size(); i++){
			for(int j=0; j<fields.size(); j++){
				System.out.printf("%15s |", records.get(i).get(fields.get(j)));
			}
			System.out.println();
		}
		System.out.println();
	}
}
