import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Member implements Comparable<Member>{
    private String id;
    private String name;
    private Day joinDate;
    private List<BorrowItems> borrowItems;
    private List<RequestItem> requestItemList;

    public List<BorrowItems> getBorrowItems() {
        return borrowItems;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String toString(){
        return id + " " + name;
    }
    public void addBorrowItems(BorrowItems borrowItem){
        borrowItems.add(borrowItem);
        Collections.sort(borrowItems, Comparator.comparing(a -> a.getEquipmentSet().getEqSetCode()));
    }

    public List<RequestItem> getRequestItemList() {
        return requestItemList;
    }

    @Override
    public int compareTo(Member another){
        if(this.id.equals(another.id)) return 0;
        else if (this.id.compareTo(another.id)>0) return 1;
        else return -1;
    }

    public Member(String id, String name) throws ExMemberIdInUse {
        this.id = id;
        this.name = name;
        this.joinDate = SystemDate.getInstance().clone();
        this.borrowItems=new ArrayList<>();
        requestItemList=new ArrayList<>();
        Club.getInstance().addMember(this);
        
    }

    public static void list(ArrayList<Member> allMembers) { 
    //Learn: "-" means left-aligned 
        System.out.printf("%-5s%-9s%11s%11s%13s\n", "ID", "Name",  
        "Join Date ", "#Borrowed", "#Requested"); 
    
        for(Member m: allMembers) { 
            System.out.printf("%-5s%-9s%11s%7d%13d\n", m.id, m.name,  
                m.joinDate, m.borrowItems.size(), m.requestItemList.size());
        }
    } 
}
