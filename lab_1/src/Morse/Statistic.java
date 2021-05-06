package Morse;

public class Statistic implements Comparable<Statistic> {

    private Character value;
    private int freq;
    public Statistic(Character value) {
        this.value = value;
        this.freq = 1;
    }
    public String getValue() {
        return value.toString();
    }
    public int getFreq() {
        return freq;
    }
    public void increaseOccurrence() {
        freq++;
    }
    @Override
    public String toString() {
        return (value + " - " + freq);
    }
    @Override
    public int compareTo(Statistic other) {
        return Integer.compare(freq, other.freq);
    }
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Statistic)) {
            return false;
        }
        Statistic otherSymbol = (Statistic) other;
        if (value == otherSymbol.value) {
            otherSymbol.increaseOccurrence();
            return true;
        }
        return false;
    }
    @Override
    public int hashCode(){
        return (int)value;
    }
}