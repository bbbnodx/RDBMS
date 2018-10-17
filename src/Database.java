import java.util.*;

public class Database {
	// �e�[�u���W��
	private HashMap<String, Table> tables = new HashMap<String, Table>();
	// �e�[�u���̖��O���
	private ArrayList<String> tableNames = new ArrayList<String>();
	
	public void setTables(HashMap<String, Table> tables){
		this.tables = tables;
	}
	
	public HashMap<String, Table> getTables(){
		return tables;
	}
	
	public void AddTables(Table table){
		tables.put(table.getName(), table);
	}
	
	public void RemoveTables(String tableName){
		tables.remove(tables.get(tableName));
	}
	
	public void setTableNames(ArrayList<String> tableNames){
		this.tableNames = tableNames;
	}
	
	public ArrayList<String> getTableNames(){
		return tableNames;
	}
	
	// �e�[�u���̖��O��ύX����
	public void ChangeTableName(String oldName, String newName){
		Table table = tables.get(oldName);
		tables.remove(oldName);
		tables.put(newName, table);
		table.setName(newName);
		tableNames.set(tableNames.indexOf(oldName), newName);
	}
	
	// SELECT�������s����
	// �����̃e�[�u������w�肵���t�B�[���h�̎w��l����v���郌�R�[�h�W�������e�[�u����Ԃ�
	public Table selectTable(
			Table table,						// SELECT�������s����e�[�u��
			ArrayList<String> fields,			// �������ɏo������t�B�[���h�W��
			ArrayList<Object> values,			// �������Ŏw�肳���l
			Operator logop){
		// fields���󂩁Cfields��values�̃T�C�Y���قȂ�ꍇ�C���̃e�[�u����Ԃ�
		if(fields.isEmpty() || fields.size() != values.size()){
			table.printTable();
			return table;
		}
		Table selected = new Table("selected", table.getFields(), table.getTypes());
		if(fields.size() == 1){
			for(int i=0; i<table.getCardinality(); i++){
				if(table.getRecords().get(i).get(fields.get(0)).equals(values.get(0))){
					selected.addRecord(table.getRecords().get(i));
				}
			}
		}else if(logop == Operator.AND){
			for(int i=0; i<table.getCardinality(); i++){
				int j;
				// �w�肳�ꂽ���ׂẴt�B�[���h�̒l���Ή�����w��l�ƈ�v�����炻�̃��R�[�h�͑I�������
				for(j=0; j<fields.size() && table.getRecords().get(i).get(fields.get(j)).equals(values.get(j)); j++);
				if(j == fields.size()){
					selected.addRecord((table.getRecords().get(i)));
				}
			}
		}else if(logop == Operator.OR){
			for(int i=0; i<table.getCardinality(); i++){
				int j;
				// �w�肳�ꂽ�����ꂩ�̃t�B�[���h�̒l���Ή�����w��l�ƈ�v�����炻�̃��R�[�h�͑I�������
				for(j=0; j<fields.size() && !table.getRecords().get(i).get(fields.get(j)).equals(values.get(j)); j++);
				if(j < fields.size()){
					selected.addRecord((table.getRecords().get(i)));
				}
			}
		}else{
			return new Table();
		}
		
		if(selected.getRecords().isEmpty()){
			System.out.println("Caution : No record selected from " + table.getName() + ".");
		}
		
		selected.printTable();
		return selected;
	}
	
	// PROJECT�������s����
	// �����̃��R�[�h�W���ƃt�B�[���h�W���Ƃ̎ˉe�����e�[�u����Ԃ�
	public Table projectTable(
			Table table,
			ArrayList<String> fields){
		// fields����̂Ƃ��C�܂��͈����̃e�[�u���ɑ��݂��Ȃ��t�B�[���h���܂�ł����ꍇ�C���̃e�[�u����Ԃ�
		if(fields.isEmpty() /*|| !table.getFields().containsAll(fields)*/){
			//System.out.println("Invalid fields.\nReturn this table.");
			table.printTable();
			return table;
		}
		ArrayList<String> types = new ArrayList<String>();
		
		for(int i=0; i<fields.size(); i++){
			types.add(table.getTypes().get(table.getFields().indexOf(fields.get(i))));
		}
		Table projected = new Table("projected", fields, types);
		for(int i=0; i<table.getCardinality(); i++){
			HashMap<String, Object> record = new HashMap<String, Object>();
			for(int j=0; j<fields.size(); j++){
				record.put(fields.get(j), table.getRecords().get(i).get(fields.get(j)));
			}
			projected.addRecord(record);
		}
		projected.printTable();
		return projected;
	}
	
	// JOIN�������s����
	// 2�̃e�[�u���̃t�B�[���h�𓝍����C�����Ɋ�Â��ē������ꂽ���R�[�h�W�������e�[�u����Ԃ�
	public Table joinTable(
			Table table1,
			Table table2,
			ArrayList<String> fields1,
			ArrayList<String> fields2){
		// fields�̂ǂ��炩���󂩁C���邢�͗��҂̃T�C�Y���قȂ�ꍇ�C��̃e�[�u����Ԃ�
		if(fields1.isEmpty() || fields1.size() != fields2.size()){
			return new Table();
		}
		// �߂�l�Ƃ���e�[�u��joined������������
		ArrayList<String> joinedFields = new ArrayList<String>(table1.getFields());
		ArrayList<String> oldFields = new ArrayList<String>();
		ArrayList<String> newFields1 = new ArrayList<String>();
		ArrayList<String> newFields2 = new ArrayList<String>();
		
		for(int i=0; i<table2.getFields().size(); i++){
			if(joinedFields.contains(table2.getFields().get(i))){
				oldFields.add(table2.getFields().get(i));
				newFields1.add(table2.getFields().get(i) + "." + table1.getName());
				newFields2.add(table2.getFields().get(i) + "." + table2.getName());
				joinedFields.set(joinedFields.indexOf(table2.getFields().get(i)),
						table2.getFields().get(i) + "." + table1.getName());
				joinedFields.add(table2.getFields().get(i) + "." + table2.getName());
			}else{
				joinedFields.add(table2.getFields().get(i));
			}
		}
		ArrayList<String> joinedTypes = new ArrayList<String>(table1.getTypes());
		joinedTypes.addAll(table2.getTypes());
		
		Table joined = new Table("joined", joinedFields, joinedTypes);
		
		// ���ꂼ��̃��R�[�h�ɂ��āC�w�肵���t�B�[���h�̗v�f����v����Ȃ烌�R�[�h�𓝍�����
		for(int i=0; i<table1.getRecords().size(); i++){
			for(int j=0; j<table2.getRecords().size(); j++){
				int p;
				// �w�肵�����ׂẴt�B�[���h�ɂ��Ĉ�v���Ă��邩���ׂ�
				for(p=0; p<fields1.size() && table1.getRecords().get(i).get(fields1.get(p)).equals(
						table2.getRecords().get(j).get(fields2.get(p))); p++);
				if(p == fields1.size()){
					HashMap<String, Object> record = new HashMap<String, Object>(table1.getRecords().get(i));
					// �t�B�[���h���̕ύX�ɑ΂��鏈��
					for(int q=0; q<oldFields.size(); q++){
						record.put(newFields1.get(q), record.get(oldFields.get(q)));
						record.remove(oldFields.get(q));
					}
					record.putAll(table2.getRecords().get(j));
					// �t�B�[���h���̕ύX�ɑ΂��鏈��
					for(int q=0; q<oldFields.size(); q++){
						record.put(newFields2.get(q), record.get(oldFields.get(q)));
						record.remove(oldFields.get(q));
					}
					joined.addRecord(record);
				}
			}
		}
		joined.printTable();
		return joined;
	}
	
	// �e�[�u���̖��O��񋓂���
	public void printTableNames(){
		for(int i=0; i<tableNames.size(); i++){
			System.out.print(tableNames.get(i) + "  ");
		}
		System.out.println();
	}
	
	// �e�[�u���W���Ɋ܂܂�邷�ׂẴe�[�u�����o�͂���
	public void printTables(){
		for(int i=0; i<tableNames.size(); i++){
			tables.get(tableNames.get(i)).printTable();
		}
		System.out.println();
	}
	
	// �e�[�u����CSV�t�@�C������ǂݍ���
	public Table readTableFromCSV(String tableName){
		ArrayList<ArrayList<String>> list = IOcsv.readCSV(tableName);
		
		if(list.size() < 1)
			return new Table();
		
		Table table = new Table(tableName, list.get(0), list.get(1));
		
		for(int i=2; i<list.size(); i++){
			HashMap<String, Object> record = new HashMap<String, Object>();
			for(int j=0; j<table.getFields().size(); j++){
				// �t�B�[���h�̌^�w��Ɋ�Â��ĕ�������^�ϊ�����
				if(list.get(1).get(j).equals("string")){
					record.put(table.getFields().get(j), list.get(i).get(j));
				}else if(list.get(1).get(j).equals("int")){
					record.put(table.getFields().get(j), Integer.valueOf(list.get(i).get(j)).intValue());
				}else if(list.get(1).get(j).equals("double")){
					record.put(table.getFields().get(j), Double.valueOf(list.get(i).get(j)).doubleValue());
				}else if(list.get(1).get(j).equals("float")){
					record.put(table.getFields().get(j), Float.valueOf(list.get(i).get(j)).floatValue());
				}
			}
			table.addRecord(record);
		}
		tableNames.add(tableName.toLowerCase());
		tables.put(tableName.toLowerCase(), table);
		System.out.println("Load table \"" + table.getName() + "\" from " + table.getName() + ".csv");
		
		return table;
	}
	
	// �e�[�u����CSV�t�@�C���ɏ�������
	public void writeTableToCSV(Table table, String name){
		if(!table.isEmpty()){
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			
			list.add(table.getFields());
			list.add(table.getTypes());
			for(int i=0; i<table.getCardinality(); i++){
				ArrayList<String> starr = new ArrayList<String>();
				for(int j=0; j<table.getDegree(); j++){
					starr.add((String)table.getRecords().get(i).get(table.getFields().get(j)));
				}
				list.add(starr);
			}
			IOcsv.writeCSV(name, list);
			System.out.println("Save table \"" + table.getName() + "\" to " + name + ".csv");
		}
	}
}
