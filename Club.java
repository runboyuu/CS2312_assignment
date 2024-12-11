import java.util.ArrayList; 
import java.util.Collections;
 //Provides sorting
public class Club {
    private ArrayList<Member> allMembers;
    private ArrayList<Equipment> allEquipments;
    private static Club instance = new Club();
    private Club(){
        allMembers = new ArrayList<>();
        allEquipments = new ArrayList<>();
    }

     public ArrayList<Member> getAllMembers() {
         return allMembers;
     }

     public ArrayList<Equipment> getAllEquipments() {
         return allEquipments;
     }

     public static Club getInstance(){
        return instance;
    }
    public void addMember(Member m) throws ExMemberIdInUse{
        for(Member mem:allMembers){
            if(mem.getId().equals(m.getId())){
                throw new ExMemberIdInUse(mem);
            }
        }
        allMembers.add(m);
        Collections.sort(allMembers);
    }

    public void addEquipment(Equipment e) throws ExEquipmentCodeInUse{
        for(Equipment eq:allEquipments){
            if(eq.getEqCode().equals(e.getEqCode())){
                throw new ExEquipmentCodeInUse(eq);
            }
        }
        allEquipments.add(e);

        //Collections.sort(allEquipments);
    }

    public void listClubMembers(){
        Member.list(this.allMembers);
    }

    public void listClubEquipments(){
        Equipment.list(this.allEquipments);
    } 

    public void removeMember(Member m){
        allMembers.remove(m);
    }
     public void removeEquipment(Equipment equipment){
         allEquipments.remove(equipment);
     }
    public Member findMember_n(String name) throws ExMemberNotFound {
        for(Member m:allMembers){
            if(m.getName().equals(name)){
                return m;
            }
        }
        throw new ExMemberNotFound();
    }

    public Member findMember_i(String id) throws ExMemberNotFound {
        for(Member m:allMembers){
            if(m.getId().equals(id)){
                return m;
            }
        }
        throw new ExMemberNotFound();
    }

    public Equipment findEquipment(String EqCode,boolean arrival) throws ExEquipmentCodeNotFound{
        for(Equipment eq:allEquipments){
            if(eq.getEqCode().equals(EqCode)){
                return eq;
            }
        }
        if(arrival) {
            throw new ExEquipmentCodeNotFound(EqCode);
        }else{
            throw new ExEquipmentCodeNotFound();
        }
    }   

}
