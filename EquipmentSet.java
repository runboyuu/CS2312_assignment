import java.util.ArrayList;

public class EquipmentSet {
    private boolean available;
    private boolean undo;
    private Member borrowMember;
    private BorrowItems borrowItems;
    private ArrayList<String> reservations;
    private ArrayList<RequestItem> requestItemArrayList;
    private String EqSetCode;

    public EquipmentSet(String EqSetCode){
        this.available = true;
        this.reservations = new ArrayList<>();
        this.EqSetCode = EqSetCode;
        requestItemArrayList=new ArrayList<>();
    }

    public Member getBorrowMember() {
        return borrowMember;
    }

    public String getEqSetCode() {
        return EqSetCode;
    }

    public boolean isUndo() {
        return undo;
    }

    public void setUndo(boolean undo) {
        this.undo = undo;
    }

    public ArrayList<RequestItem> getRequestItemArrayList() {
        return requestItemArrayList;
    }

    public void setBorrowMember(Member member){
        this.borrowMember=member;
    }
    public boolean CheckAvailable(){
        return available;
    }
    public String getAvailableName(){
        if(borrowMember==null||undo){
            return null;
        }
        return String.format("%s(%s)",EqSetCode,borrowMember.getId());
    }

    public BorrowItems getBorrowItems() {
        return borrowItems;
    }

    public void setBorrowItems(BorrowItems borrowItems) {
        this.borrowItems = borrowItems;
    }
}
