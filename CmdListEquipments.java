public class CmdListEquipments implements Command{
    public void execute(String[] cmdParts){
        Club c = Club.getInstance();
        c.listClubEquipments();
    }
}
