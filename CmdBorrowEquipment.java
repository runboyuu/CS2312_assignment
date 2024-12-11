public class CmdBorrowEquipment extends RecordedCommand{
    private BorrowItems borrowItems;
    private int day;
    public void execute(String[] cmdParts) throws ExInsufficientCmdArgs, ExMemberNotFound, ExEquipmentCodeNotFound, ExNoAvailableBorrow, ExExistBorrowMember, ExDayMustLatsOne, ExNotANumber, ExDayOverlap {
        if(cmdParts.length<3){
            throw new ExInsufficientCmdArgs();
        }
        int day=7;
        if(cmdParts.length==4){
            try {
                day = Integer.parseInt(cmdParts[3]);
            }catch (Exception e){
                throw new ExNotANumber();
            }
        }
        if(day<1){
            throw new ExDayMustLatsOne();
        }
        this.day=day;
        String id = cmdParts[1];
        String EqCode = cmdParts[2];

        Club c = Club.getInstance();
        Member m = c.findMember_i(id);
        Equipment eq = c.findEquipment(EqCode,false);
        this.borrowItems=new BorrowItems(m,eq);
        BorrowUtil.addBorrow(borrowItems,day);
        addUndoCommand(this);
        clearRedoList();
        Member member=borrowItems.getMember();
        Equipment equipment=borrowItems.getEquipment();
        System.out.printf("%s %s borrows %s (%s) for %s to %s\n",member.getId(),member.getName()
                ,borrowItems.getEquipmentSet().getEqSetCode(),equipment.getName(),borrowItems.getStartDay()
                ,borrowItems.getEndDay());
        System.out.println("Done.");

    }
    public void undoMe(){
        BorrowUtil.removeBorrow(borrowItems);
        addRedoCommand(this);
    }

    public void redoMe(){
        try {
            BorrowUtil.addBorrow(borrowItems,day);
        }catch (Exception e){
            // do nothing
        }
        addUndoCommand(this);
    }
}