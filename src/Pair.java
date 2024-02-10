public class Pair implements Comparable<Pair> {

    //instance variables.
    private char value;
    private double prob;


    //constructor
    public Pair(char value, double prob) {
        this.value = value;
        this.prob = prob;
    }

    //setters
    public void setProb(double prob) {
        this.prob = prob;
    }

    public void setValue(char value) {
        this.value = value;
    }


    //getters
    public char getValue() {
        return value;
    }

    public double getProb() {
        return prob;
    }


    //toString
    @Override
    public String toString() {
        return "Value: " + value + "/n prob: " + prob;
    }

    //compareTo method.
    public int compareTo(Pair p) {
        return Double.compare(this.getProb(), p.getProb());
    }
}
