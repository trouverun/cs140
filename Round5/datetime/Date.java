public class Date {
    private final int year;
    private final int month;
    private final int day;

    public Date(int year, int month, int day) throws DateException {
        this.year = year;
        this.month = month;
        this.day = day;

        if (year < 0 || month < 0 || month > 12 || day < 0) {
            throw new DateException("Illegal date " + this);
        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String toString() {
        return  String.format("%2d, %2d, %d", day, month, year);
    }
}
