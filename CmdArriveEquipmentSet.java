public class CmdArriveEquipmentSet extends RecordedCommand{
    String EqCode;
    EquipmentSet equipmentSet;
    public void execute(String[] cmdParts) throws ExInsufficientCmdArgs, ExEquipmentCodeNotFound{
        
        if(cmdParts.length<2){
            throw new ExInsufficientCmdArgs();
        }

        String EqCode = cmdParts[1];
        this.EqCode=EqCode;
        Club c = Club.getInstance();
        Equipment eq = c.findEquipment(EqCode,true);

        equipmentSet=eq.addEquipmentSet();
            
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");

        

        
    }

    public void undoMe(){
        equipmentSet.setUndo(true);
        addRedoCommand(this);
    }

    public void redoMe(){
        equipmentSet.setUndo(false);
        addUndoCommand(this);
    }
}
