import java.util.ArrayList;

public class BorrowUtil {
    private static ArrayList<BorrowItems> borrowItems=new ArrayList<>();
    public static void addBorrow(BorrowItems borrowItem,int day) throws ExExistBorrowMember, ExNoAvailableBorrow, ExDayOverlap {
        borrowItem.setStartDay(SystemDate.getInstance().clone());
        borrowItem.setEndDay(SystemDate.getInstance().clone().addDay(day));
        Equipment equipment=borrowItem.getEquipment();
        for(RequestItem requestItem1:borrowItem.getMember().getRequestItemList()){
            if(requestItem1.getEquipment()==borrowItem.getEquipment()&&!(requestItem1.getEndDay().compareTo(borrowItem.getStartDay()) < 0 ||
                    requestItem1.getStartDay().compareTo(borrowItem.getEndDay()) > 0)){
                throw new ExDayOverlap(true);
            }
        }
        for(EquipmentSet equipmentSet1:equipment.getAllEquipmentSets()){
            Member member=borrowItem.getMember();
            if(equipmentSet1.getBorrowMember()==member){
                throw new ExExistBorrowMember();
            }
            boolean overlap=false;
            for(RequestItem requestItem1: equipmentSet1.getRequestItemArrayList()){
                if(!(requestItem1.getEndDay().compareTo(borrowItem.getStartDay()) < 0 ||
                        requestItem1.getStartDay().compareTo(borrowItem.getEndDay()) > 0)){
                    overlap=true;
                }
            }
            if(overlap){
                continue;
            }
            if(equipmentSet1.getBorrowMember()==null){
                equipmentSet1.setBorrowMember(member);
                equipmentSet1.setBorrowItems(borrowItem);
                borrowItem.setEquipmentSet(equipmentSet1);
                member.addBorrowItems(borrowItem);
                borrowItems.add(borrowItem);
                return;
            }
        }
        throw new ExNoAvailableBorrow();

    }
    public static void removeBorrow(BorrowItems borrowItem) {
        borrowItem.setStartDay(SystemDate.getInstance().clone());
        borrowItem.setEndDay(SystemDate.getInstance().clone().addDay(7));
        borrowItem.getMember().getBorrowItems().remove(borrowItem);
        borrowItem.getEquipmentSet().setBorrowMember(null);
        borrowItem.getEquipmentSet().setBorrowItems(null);
        borrowItems.remove(borrowItem);
    }
}
