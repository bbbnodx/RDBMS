public class Rdbms {

	public static void main(String[] args) {
		Database db = new Database();
		
		// �����̃e�[�u�����t�@�C������ǂݍ���
		db.readTableFromCSV("Tools");
		db.readTableFromCSV("Tools1");
		db.readTableFromCSV("Tools2");
		db.readTableFromCSV("Tools3");
		db.readTableFromCSV("Source");
		db.readTableFromCSV("Properties");
		
		Instruction.commandHelp();
		// �C�x���g�쓮�����Ŗ��߂���́E���s����
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
