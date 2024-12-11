public class ExNoAvailableBorrow extends Exception {
    public ExNoAvailableBorrow(){
        super("There is no available set of this equipment for the command.");
    }
}
