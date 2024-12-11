public class CmdStartNewDay extends RecordedCommand{
    private String dateString;
    private SystemDate prevDate;

    public void execute(String[] cmdParts) throws ExInsufficientCmdArgs, ExInvalidNewDay, ExInvalidDayFormat{
        
        if(cmdParts.length<2){
            throw new ExInsufficientCmdArgs();
        }
        prevDate = SystemDate.getInstance();
        dateString = cmdParts[1];
            

        SystemDate newSysDate = new SystemDate(dateString);  // may lead to an invalid date format exception
        if(newSysDate.compareTo(prevDate)<=0){
            throw new ExInvalidNewDay(prevDate);
        }

        SystemDate.setToNULL();
        SystemDate.createTheInstance(dateString);

        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
        
    }

    public void undoMe(){
        SystemDate.set(prevDate);
        addRedoCommand(this);
    }

    public void redoMe(){
        SystemDate.setToNULL();
        SystemDate.createTheInstance(dateString);
        addUndoCommand(this);
    }
}
