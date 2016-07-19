import java.util.LinkedList;

/**
 * Created by Felix on 01.06.2014.
 */
public class SimulatedAnnealing {
   LinkedList<Item> data;
    int NeighbourHood;


    public SimulatedAnnealing() {
    }

    public LinkedList<Item> perform(LinkedList<Item> items, int constraint){
        // Init variables
        //Measure measure = new Measure("measure");
        data = items;
        NeighbourHood = 5;
        Solution solution = new Solution();
        solution.addItem(data.get(0));
        CoolingSchedule schedule = new CoolingSchedule(500,0.85,1.0*Math.pow(10,-5),1);
        Solution bestSolution = solution;

        //measure.startSample();
        // The algorithm stops when the system temperature hits the final temperature
        while(schedule.currentTemperature >= schedule.finalTemperature) {
            int repetition = 0;
            // Does iterations at the same temperature. repetitionSchedule is the limit
            while (repetition != schedule.repetitionSchedule) {
                // Look for new neighbours
                Item bestNeighbor = findBestNeighbour(solution);
                Solution newSolution = new Solution(solution.items);
                // Adds best neighbor to the temporary solution
                newSolution.addItem(bestNeighbor);
                Item lastDeletedItem = null;
                // Checks if the solution is infeasible. If so the worst item gets dropped out
                while(newSolution.isInfeasible(constraint)){
                    lastDeletedItem = dropWorstItem(newSolution);
                }
                // If the item which gets dropped out is the same that was added, the neighborhood increases
                if(lastDeletedItem == bestNeighbor){
                    NeighbourHood = NeighbourHood + 5;
                    if(NeighbourHood > 100){
                        NeighbourHood = 100;
                    }
                }
                // Calculates the evaluationDelta and checks if it is accepted or not
                double evaluationChange = newSolution.evaluate() - solution.evaluate();
                if(evaluationChange > 0){
                    solution = newSolution;
                }else{
                    if(Math.random() < Math.exp(-evaluationChange/schedule.currentTemperature)){
                        solution = newSolution;
                    }
                }
                // If the current solution is better than the best solution, the current solution gets assigned to the
                // best solution
                if(bestSolution.evaluate() < solution.evaluate()){
                    bestSolution = solution;
                }
                // Increases the repetition value
                repetition++;
                //measure.sample(bestSolution.evaluate(), solution.evaluate());
            }
            // Decreases the system temperature
            schedule.decreaseTemperature();
        }
        return bestSolution.getItems();
    }

    private Item dropWorstItem(Solution newSolution) {
        // Searches for the worst item, than delete it from the solution
        Item worst = newSolution.items.get(0);
        for(Item i : newSolution.items){
            if(i.getProfit() < worst.getProfit()){
                worst = i;
            }
        }
        worst.setTaken(false);
        newSolution.items.remove(worst);
        return worst;
    }


    private Item findBestNeighbour(Solution solution){
        // Searches for the item with the highest profit value
        Item best = new Item(1,0);
        int from = data.indexOf(solution.items.getLast())+1;
        LinkedList<Integer> indexList = new LinkedList<Integer>();
        for (int i = 0; i < NeighbourHood; i++) {
            indexList.addLast(from%data.size());
            from++;
        }
        for (Integer index : indexList) {
            if(data.get(index).getProfit() > best.getProfit() && !data.get(index).isTaken()){
                best = data.get(index);
            }
        }
        return best;
    }


    private class Solution{
        LinkedList<Item> items;

        private Solution() {
            this.items = new LinkedList<Item>();
        }

        private Solution(LinkedList<Item> items) {
            this.items = new LinkedList<Item>();
            for (Item i : items){
                Item newItem = new Item(i.getWeight(),i.getProfit());
                newItem.setTaken(true);
                this.items.addLast(newItem);
            }
        }

        private void addItem(Item i){
            i.setTaken(true);
            items.addLast(i);
        }

        private boolean isInfeasible(int constraint){
            // Sums the weight values up and checks if the sum is higher than the constraint
            double sum = 0;
            for (Item i: items){
                sum = sum + i.getWeight();
            }
            if (sum > constraint){
                return true;
            }
            return false;
        }

        private double evaluate(){
            // Sums the profit values up
            double sum = 0;
            for (Item i: items){
                sum = sum + i.getProfit();
            }
            return sum;
        }

        private LinkedList<Item> getItems(){
            return items;
        }
    }


    private class CoolingSchedule{
        private int initialTemperature;
        private double controlValue;
        private double finalTemperature;
        private int repetitionSchedule;
        private double currentTemperature;

        private CoolingSchedule(int initialTemperature, double controlValue, double finalTemperature, int repetitionSchedule) {
            this.initialTemperature = initialTemperature;
            this.controlValue = controlValue;
            this.finalTemperature = finalTemperature;
            this.repetitionSchedule = repetitionSchedule;
            this.currentTemperature = initialTemperature;
        }

        private void decreaseTemperature(){
            currentTemperature = controlValue * currentTemperature;
        }
    }
}
