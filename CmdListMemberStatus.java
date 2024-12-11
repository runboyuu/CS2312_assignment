import java.util.ArrayList;

public class CmdListMemberStatus implements Command {
    @Override
    public void execute(String[] cmdParts){
        Club c = Club.getInstance();
        ArrayList<Member> memberArrayList=c.getAllMembers();
        for(Member member:memberArrayList){
            System.out.printf("[%s %s]\n",member.getId(),member.getName());
            int count=0;
            for(BorrowItems borrowItems: member.getBorrowItems()){
                if(!borrowItems.getEquipmentSet().isUndo()){
                    count++;
                }
            }
            if(count==0&&member.getRequestItemList().size()==0){
                System.out.println("No record.");
            }else{
                for(BorrowItems borrowItems: member.getBorrowItems()){
                    if(!borrowItems.getEquipmentSet().isUndo()){
                        System.out.printf("- borrows %s (%s) for %s to %s\n",borrowItems.getEquipmentSet().getEqSetCode()
                                ,borrowItems.getEquipment().getName(),borrowItems.getStartDay(),borrowItems.getEndDay());

                    }

                }
                for(RequestItem requestItem: member.getRequestItemList()){
                    System.out.printf("- requests %s (%s) for %s to %s\n",requestItem.getEquipmentSet().getEqSetCode()
                            ,requestItem.getEquipment().getName(),requestItem.getStartDay(),requestItem.getEndDay());

                }
            }
            System.out.println();
        }
    }
}
