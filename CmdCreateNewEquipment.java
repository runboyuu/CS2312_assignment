public class CmdCreateNewEquipment extends RecordedCommand{
    String EqCode;
    String name;
    public void execute(String[] cmdParts) throws ExInsufficientCmdArgs, ExEquipmentCodeInUse{
        
        if(cmdParts.length<3){
            throw new ExInsufficientCmdArgs();
        }
        String EqCode = cmdParts[1];
        String name = cmdParts[2];
        this.EqCode=EqCode;
        this.name=name;
        new Equipment(EqCode, name);

        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");

        

    }

    public void undoMe(){
        try {
            Club.getInstance().removeEquipment(Club.getInstance().findEquipment(this.EqCode,false));
        }catch (Exception e){
            // do nothing
        }
        addRedoCommand(this);
    }

    public void redoMe(){
        try {
            new Equipment(EqCode, name);
        }catch (Exception e){
            // do nothing
        }
        addUndoCommand(this);
    }
}