import java.util.*;

public class Table {
	private String name = new String();		// �e�[�u���̖��O(������)
	// ���R�[�h�W��
	private ArrayList<HashMap<String, Object>> records = new ArrayList<HashMap<String, Object>>();
	
	// �t�B�[���h�W��(���ׂď������̕�����Ƃ���)
	private ArrayList<String> fields;
	
	// �e�t�B�[���h�ɂ�����f�[�^�̌^���L������(������)
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
		// ���R�[�h�����݂���Ȃ�CsetFields�ɂ��ύX�͋����Ȃ�	
		}else{
			System.out.println("Table \"" + name + "\" has records.\nPlease use the \"changeField\"method");
		}	
	}
	
	public void changeField(String oldField, String newField){
		// �e�[�u����oldField�������Ă��āC����newField�������Ă��Ȃ��Ƃ��Ɍ���
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
	
	// ���R�[�h�̐���Ԃ�
	public int getCardinality(){
		return records.size();
	}
	
	// �t�B�[���h�̐���Ԃ�
	public int getDegree(){
		return fields.size();
	}
	
	public boolean isEmpty(){
		if(fields.isEmpty() || types.isEmpty())
			return true;
		else
			return false;
	}
	
	// �e�[�u���̏o��
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
