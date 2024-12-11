import java.util.ArrayList;
import java.util.List;

public class Equipment {
    private String EqCode;
    private String name;
    private ArrayList<EquipmentSet> allEquipmentSets;

    public Equipment(String EqCode, String name) throws ExEquipmentCodeInUse{
        this.EqCode = EqCode;
        this.name = name;
        allEquipmentSets = new ArrayList<>();

        Club.getInstance().addEquipment(this);
    }
    public ArrayList<EquipmentSet> getAllEquipmentSets(){
        return allEquipmentSets;
    }

    public String getName() {
        return name;
    }

    public String getEqCode(){
        return EqCode;
    }

    public EquipmentSet addEquipmentSet(){
        String EqSetCode = EqCode +"_" +Integer.toString(allEquipmentSets.size()+1);
        EquipmentSet EqSet = new EquipmentSet(EqSetCode);
        allEquipmentSets.add(EqSet);
        return EqSet;
    }

    public static void list(ArrayList<Equipment> allEquipments){
        System.out.printf("%-5s%-18s%5s\n", "Code", "Name",
        "#sets");

        for(Equipment e: allEquipments) {
            List<String> borrowName=new ArrayList<>();
            for(EquipmentSet equipmentSet: e.getAllEquipmentSets()){
                String s= equipmentSet.getAvailableName();
                if(s!=null){
                    borrowName.add(s);
                }
            }
            int count=0;
            for(EquipmentSet equipmentSet:e.allEquipmentSets){
                if(!equipmentSet.isUndo()){
                    count++;
                }
            }
            System.out.printf("%-5s%-18s  %d  %s\n", e.EqCode, e.name,
                count,borrowName.size()==0?"":"(Borrowed set(s): "+String.join(", ",borrowName)+")");

        }
    }

    public String toString(){
        return EqCode+" "+name;
    }

}

