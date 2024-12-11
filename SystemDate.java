public class SystemDate extends Day{
    private static SystemDate instance;

    public SystemDate(String sDay) throws ExInvalidDayFormat{
        super(sDay);
    }

    public static SystemDate getInstance(){
        return instance;
    }

    public static void createTheInstance(String sDay){
        try {
            if(instance==null){
                instance = new SystemDate(sDay);
            }
            else{
                System.out.println("Can not create one more system date instance.");
            }
        } catch (ExInvalidDayFormat e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void set(SystemDate sd){
        instance = sd;
    }
    
    public static void setToNULL(){
        instance = null;
    }
    
}