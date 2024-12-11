import java.util.Collections;

public class CmdRequestEquipment extends RecordedCommand{
    RequestItem requestItem;
    int day;
    String id;
    String EqCode;
    Day startDay;
    Day endDay;
    Member member;
    Equipment equipment;
    EquipmentSet equipmentSet;
    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCmdArgs, ExEquipmentCodeInUse, ExEquipmentCodeNotFound, ExMemberIdInUse, ExInvalidNewDay, ExInvalidDayFormat, ExMemberNotFound, ExNoAvailableBorrow, ExExistBorrowMember, ExNotANumber, ExDayMustLatsOne, ExDayOverlap {
        if(cmdParts.length<5){
            throw new ExInsufficientCmdArgs();
        }

        this.id = cmdParts[1];
        this.EqCode = cmdParts[2];
        this.startDay=new Day(cmdParts[3]);
        try {
            this.day = Integer.parseInt(cmdParts[4]);

        }catch (Exception e){
            throw new ExNotANumber();
        }
        if(this.day<1){
            throw new ExDayMustLatsOne();
        }
        endDay=startDay.clone().addDay(day);
        Club c = Club.getInstance();
        member = c.findMember_i(id);
        equipment = c.findEquipment(EqCode,false);
        this.requestItem=new RequestItem(member,equipment);
        addRequestItem(requestItem);
        addUndoCommand(this);
        clearRedoList();
        Member member=requestItem.getMember();
        Equipment equipment=requestItem.getEquipment();
        System.out.printf("%s %s requests %s (%s) for %s to %s\n",member.getId(),member.getName()
                ,requestItem.getEquipmentSet().getEqSetCode(),equipment.getName(),requestItem.getStartDay()
                ,requestItem.getEndDay());
        System.out.println("Done.");
    }
    private void addRequestItem(RequestItem requestItem) throws ExDayOverlap, ExNoAvailableBorrow {
        requestItem.setStartDay(startDay);
        requestItem.setEndDay(endDay);
        for(BorrowItems requestItem1:requestItem.getMember().getBorrowItems()){
            if(requestItem1.getEquipment()==requestItem.getEquipment()&&!(requestItem1.getEndDay().compareTo(requestItem.getStartDay()) < 0 ||
                    requestItem1.getStartDay().compareTo(requestItem.getEndDay()) > 0)){
                throw new ExDayOverlap();
            }
        }
        for(RequestItem requestItem1:requestItem.getMember().getRequestItemList()){
            if(requestItem1.getEquipment()==requestItem.getEquipment()&&!(requestItem1.getEndDay().compareTo(requestItem.getStartDay()) < 0 ||
                    requestItem1.getStartDay().compareTo(requestItem.getEndDay()) > 0)){
                throw new ExDayOverlap();
            }
        }
        for(EquipmentSet equipmentSet1:requestItem.getEquipment().getAllEquipmentSets()){
            if(!equipmentSet1.isUndo()){
                if(equipmentSet1.getBorrowMember()==null||
                        (equipmentSet1.getBorrowItems().getEndDay().compareTo(requestItem.getStartDay()) < 0 ||
                                equipmentSet1.getBorrowItems().getStartDay().compareTo(requestItem.getEndDay()) > 0)){
                    boolean overlap=false;
                    for(RequestItem requestItem1:equipmentSet1.getRequestItemArrayList()){
                        if(!(requestItem1.getEndDay().compareTo(requestItem.getStartDay()) < 0 ||
                                requestItem1.getStartDay().compareTo(requestItem.getEndDay()) > 0)){
//                            if(equipmentSet1.getBorrowMember()!=null)throw new ExDayOverlap();
                            overlap=true;
                        }
                    }
                    if(overlap){
                        continue;
                    }
                    requestItem.setEquipmentSet(equipmentSet1);
                    equipmentSet1.getRequestItemArrayList().add(requestItem);
                    equipmentSet1.getRequestItemArrayList().sort((a,b)->{
                        if(a.getEndDay().compareTo(b.getStartDay())<0) return -1;
                        else if (a.getStartDay().compareTo(b.getEndDay())>0) return 1;
                        else return 0;
                    });
                    equipmentSet=equipmentSet1;
                    member.getRequestItemList().add(requestItem);
                    return;
                }
//                throw new ExDayOverlap();
            }
        }
        throw new ExNoAvailableBorrow();
    }
    @Override
    public void undoMe(){
        equipmentSet.getRequestItemArrayList().remove(requestItem);
        member.getRequestItemList().remove(requestItem);
        addRedoCommand(this);
    }

    public void redoMe(){
        try {
            addRequestItem(requestItem);
        }catch (Exception e){
            // do nothing
        }
        addUndoCommand(this);
    }
}
