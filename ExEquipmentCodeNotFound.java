public class ExEquipmentCodeNotFound extends Exception{
    public ExEquipmentCodeNotFound(String EqCode){
        super("Missing record for Equipment "+EqCode+".  Cannot mark this item arrival.");
    }
    public ExEquipmentCodeNotFound(){
        super("Equipment record not found.");
    }
}
