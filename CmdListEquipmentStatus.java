import java.util.ArrayList;
import java.util.List;

public class CmdListEquipmentStatus implements Command{
    @Override
    public void execute(String[] cmdParts) throws ExInsufficientCmdArgs, ExEquipmentCodeInUse, ExEquipmentCodeNotFound, ExMemberIdInUse, ExInvalidNewDay, ExInvalidDayFormat, ExMemberNotFound, ExNoAvailableBorrow, ExExistBorrowMember {
        Club c = Club.getInstance();
        for(Equipment equipment:c.getAllEquipments()){
            System.out.printf("[%s %s]\n",equipment.getEqCode(),equipment.getName());
            int count=0;
            for(EquipmentSet equipmentSet: equipment.getAllEquipmentSets()){
                if(!equipmentSet.isUndo()){
                    System.out.printf("  %s\n",equipmentSet.getEqSetCode());
                    System.out.printf("    Current status: %s\n",equipmentSet.getBorrowMember()==null?
                            "Available":String.format("%s %s borrows for %s to %s",equipmentSet.getBorrowMember().getId(),
                            equipmentSet.getBorrowMember().getName(),equipmentSet.getBorrowItems().getStartDay(),
                            equipmentSet.getBorrowItems().getEndDay()));
                    List<String> dayList=new ArrayList<>();
                    for(RequestItem requestItem: equipmentSet.getRequestItemArrayList()){
                        dayList.add(requestItem.getStartDay()+" to "+requestItem.getEndDay());
                    }
                    if(dayList.size()>0){
                        System.out.printf("    Requested period(s): %s\n",String.join(", ",dayList));
                    }
                    count++;
                }
            }
            if(count==0){
                System.out.println("We do not have any sets for this equipment.");
            }
            System.out.println();
        }
    }
}
