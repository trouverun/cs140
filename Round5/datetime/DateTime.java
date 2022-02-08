public class DateTime extends Date {
    final int hour;
    final int minute;
    final int second;

    DateTime(int year, int month, int day, int hour, int minute, int second) throws DateException {
        super(year, month, day);

        if (second < 0 || second > 59 || minute < 0 || minute > 59 || hour < 0 || hour > 23) {
            throw new DateException(String.format("Illegal time: %2d:%2d:%2d", hour, minute, second));
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public String toString() {
        return super.toString() + String.format(" %2d:%2d:%2d", hour, minute, second);
    }
}
