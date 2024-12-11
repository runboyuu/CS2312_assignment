public class ExMemberIdInUse extends Exception{
    public ExMemberIdInUse(Member mem){
        super("Member ID already in use: "+mem.toString());
    }
   
}