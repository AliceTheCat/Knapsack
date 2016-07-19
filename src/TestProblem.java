import java.util.LinkedList;

/**
 * Created by Felix on 30.05.2014.
 */
public class TestProblem {
    private int numberOfProfitValues;
    private int numberOfWeightSets;
    private int optimalSolutionValue;
    private int id;

    private LinkedList<Integer> profitValues;
    private LinkedList<WeightsSet> weightSets;

    public TestProblem(int id){
        this.id = id;
        profitValues = new LinkedList<Integer>();
    }

    public int getNumberOfProfitValues() {
        return numberOfProfitValues;
    }

    public void setNumberOfProfitValues(int numberOfProfitValues) {
        this.numberOfProfitValues = numberOfProfitValues;
    }

    public int getNumberOfWeightSets() {
        return numberOfWeightSets;
    }

    public void setNumberOfWeightSets(int numberOfWeightSets) {
        this.numberOfWeightSets = numberOfWeightSets;
    }

    public int getOptimalSolutionValue() {
        return optimalSolutionValue;
    }

    public void setOptimalSolutionValue(int optimalSolutionValue) {
        this.optimalSolutionValue = optimalSolutionValue;
    }

    public LinkedList<Integer> getProfitValues() {
        return profitValues;
    }

    public void addCostValue(int c) {
        this.profitValues.addLast(c);
    }

    public WeightsSet getWeightSet(int m) {
        if(weightSets == null){
            weightSets = new LinkedList<WeightsSet>();
            for (int k = 0; k < numberOfWeightSets; k++) {
                weightSets.addLast(new WeightsSet(k));
            }
        }
        return weightSets.get(m);
    }

    public LinkedList<WeightsSet> getWeightSets() {
        return weightSets;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TestProblem{\n" +
                "id=" + id +
                ", \nnumberOfProfitValues=" + numberOfProfitValues +
                ", \nnumberOfWeightSets=" + numberOfWeightSets +
                ", \noptimalSolutionValue=" + optimalSolutionValue +
                ", \nprofitValues=" + profitValues +
                ", \nweightSets=" + weightSets +
                "}\n";
    }

    public class WeightsSet {
        private LinkedList<Integer> weights;
        private int constraint;
        private int id;

        public WeightsSet(int k){
            weights = new LinkedList<Integer>();
            id = k;
        }

        public void addWeight(int c){
            weights.addLast(c);
        }

        public LinkedList<Integer> getWeights(){
            return weights;
        }

        public int getConstraint() {
            return constraint;
        }

        public void setConstraint(int constraint) {
            this.constraint = constraint;
        }

        @Override
        public String toString() {
            return "WeightsSet{\n" +
                    "id=" + id +
                    ", \nweights=" + weights +
                    ", \nconstraint=" + constraint +
                    "}\n";
        }
    }
}
