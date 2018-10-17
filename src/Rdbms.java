public class Rdbms {

	public static void main(String[] args) {
		Database db = new Database();
		
		// 既存のテーブルをファイルから読み込み
		db.readTableFromCSV("Tools");
		db.readTableFromCSV("Tools1");
		db.readTableFromCSV("Tools2");
		db.readTableFromCSV("Tools3");
		db.readTableFromCSV("Source");
		db.readTableFromCSV("Properties");
		
		Instruction.commandHelp();
		// イベント駆動方式で命令を入力・実行する
		while(true){
			Instruction inst = new Instruction();
			System.out.print("> ");
			inst.getInstruction();
			if(inst.getInst().get(0).toLowerCase().equals("quit")){
				System.out.println("quit");
				break;
			}else {
				inst.execInstruction(db, inst.getInst());
			}
		}
	}
}
