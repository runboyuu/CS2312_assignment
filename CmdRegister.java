public class CmdRegister extends RecordedCommand{
    private String id;
    private String name;

    public void execute(String[] cmdParts) throws ExInsufficientCmdArgs, ExMemberIdInUse{
        
        if(cmdParts.length<3){
            throw new ExInsufficientCmdArgs();
        }
        id = cmdParts[1];
        name = cmdParts[2];
            
        new Member(id,name);

        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
        
    }

    public void undoMe(){
        Club c = Club.getInstance();
        try {
            c.removeMember(c.findMember_n(name));
        } catch (ExMemberNotFound e) {
            // do nothing
        }
        addRedoCommand(this);
    }

    public void redoMe(){
        try {
            new Member(id, name);
        }catch (Exception e){
            // do nothing
        }
        addUndoCommand(this);
    }
}
   
