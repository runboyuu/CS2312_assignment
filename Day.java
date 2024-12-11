import java.time.Month;

public class Day implements Cloneable, Comparable<Day>{
	
	private int year;
	private int month;
	private int day;
	private static final String MonthNames="JanFebMarAprMayJunJulAugSepOctNovDec";

	//Constructor
	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}
	
	@Override
	public int compareTo(Day anoDay) {
		int thisDayInt = this.toInteger();
		int anoDayInt = anoDay.toInteger();
		if(thisDayInt<anoDayInt) return -1;
		else if(thisDayInt==anoDayInt) return 0;
		else return 1;
	}

	// check if a given year is a leap year
	static public boolean isLeapYear(int y) {
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d) {
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

	// Return a string for the day like dd MMM yyyy
	public String toString() {
		return day+"-"+ MonthNames.substring((month-1)*3,month*3) + "-"+ year;
	}

	public int toInteger(){
		String yString = Integer.toString(year);
		String mString = Integer.toString(month);
		if(mString.length()==1) mString="0"+mString;
		String dString = Integer.toString(day);
		if(dString.length()==1)  dString="0"+dString;
		return Integer.parseInt(yString+mString+dString);
	}
	
    public void set(String sDay) throws ExInvalidDayFormat{
        try {
			String[] sDayParts = sDay.split("-");

			if(sDayParts.length<3) throw new ExInvalidDayFormat();
			int day_ = Integer.parseInt(sDayParts[0]);
			int year_ = Integer.parseInt(sDayParts[2]);
			int month_ = MonthNames.indexOf(sDayParts[1])/3+1;

			if(MonthNames.indexOf(sDayParts[1])==-1||
			!valid(year_,month_,day_)){
				throw new ExInvalidDayFormat();
			}

			this.day = day_;
			this.year = year_;
			this.month = month_;

		} catch (NumberFormatException e) {
			throw new ExInvalidDayFormat();
		}
    }

    public Day(String sDay) throws ExInvalidDayFormat{
        set(sDay);
    }

    @Override
    public Day clone(){
        Day copy = null;
        try {
            copy = (Day) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return copy;
    }
	public Day addDay(int daysToAdd) {
		int newDay = this.day + daysToAdd;
		int newMonth = this.month;
		int newYear = this.year;
		while (newDay > getDaysInMonth(newYear, newMonth)) {
			newDay -= getDaysInMonth(newYear, newMonth);
			newMonth++;
			if (newMonth > 12) {
				newMonth = 1;
				newYear++;
			}
		}
		return new Day(newYear, newMonth, newDay);
	}

	private int getDaysInMonth(int year, int month) {
		switch (month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
				return 31;
			case 4: case 6: case 9: case 11:
				return 30;
			case 2:
				return isLeapYear(year) ? 29 : 28;
			default:
				throw new IllegalArgumentException("Invalid month: " + month);
		}
	}
}