public class ExDayOverlap extends Exception{
    public ExDayOverlap(boolean request){
        super(String.format("The period overlaps with a current period that the member %s the equipment.",
                request?"requests":"borrows"));
    }
    public ExDayOverlap(){
        super("The period overlaps with a current period that the member borrows / requests the equipment. ");
    }
}
