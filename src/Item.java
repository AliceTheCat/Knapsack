/**
 * Created by Felix on 01.06.2014.
 */
public class Item {
    private double weight;
    private double profit;
    private boolean taken;

    public Item(double weight, double profit) {
        this.weight = weight;
        this.profit = profit;
        this.taken = false;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    @Override
    public String toString() {
        return "{" +
                "weight=" + weight +
                ", profit=" + profit +
                ", taken=" + taken +
                '}';
    }
}
