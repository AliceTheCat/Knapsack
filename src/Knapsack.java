import java.util.LinkedList;


/**
 * Created by Felix on 30.05.2014.
 */
public class Knapsack {
    private static LinkedList<TestProblem> testProblems;

    public static void main(String[] args){
        SimulatedAnnealing sa = new SimulatedAnnealing();
        // Parse OR data set file
        testProblems = Parser.ParseFile("mknapcb1.txt");
        // Iterate through all test instances in the first test problem
        TestProblem tp = testProblems.get(0);
        for(TestProblem.WeightsSet ws : tp.getWeightSets()) {
            LinkedList<Item> items = new LinkedList<Item>();
            // Iterate through all profit values and add them with their weight to the item list
            for (int i = 0; i < testProblems.get(0).getNumberOfProfitValues(); i++) {
                items.addLast(new Item(ws.getWeights().get(i), testProblems.get(0).getProfitValues().get(i)));
            }
            // Execute the simulated annealing algorithm
            LinkedList<Item> solution = sa.perform(items, ws.getConstraint());
            // Print the solution
            printSolution(solution, ws, tp);
        }
    }

    private static void printSolution(LinkedList<Item> solution, TestProblem.WeightsSet ws, TestProblem tp) {
        double costSum = 0;
        double weightSum = 0;
        System.out.println("Test problem #" + testProblems.indexOf(tp));
        System.out.println("Test instance #" + tp.getWeightSets().indexOf(ws) + " with constraint = " + ws.getConstraint() +  "     ");
        System.out.println("item" + "\t|\t" + "profit" + "\t|\t" + "weight");
        for (int n = 0; n < solution.size(); n++) {
            System.out.println(n + "\t\t\t" + solution.get(n).getProfit() + "\t\t" + solution.get(n).getWeight());
            costSum = costSum + solution.get(n).getProfit();
            weightSum = weightSum + solution.get(n).getWeight();
        }
        System.out.println("sum:\t\t"  + costSum + "\t\t" + weightSum + "\n\n");
    }
}
