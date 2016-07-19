import java.io.*;
import java.util.LinkedList;

/**
 * Created by Felix on 30.05.2014.
 */
public class Parser {
    private static LinkedList<TestProblem> data = new LinkedList<TestProblem>();
    public static LinkedList<TestProblem> ParseFile(String src){
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(src));
            String line = null;
            line = in.readLine();
            line = line.replaceAll(" ","");
            int K = Integer.parseInt(line);
            for (int i = 0; i < K; i++) {
                data.add(new TestProblem(i));
            }
            // Iterate through all test problems
            for (int k = 0; k < K; k++) {
                line = in.readLine();
                String[] array = line.split(" ");
                data.get(k).setNumberOfProfitValues(Integer.parseInt(array[1]));
                data.get(k).setNumberOfWeightSets(Integer.parseInt(array[2]));
                data.get(k).setOptimalSolutionValue(Integer.parseInt(array[3]));

                // Iterate through all profit values and add them to the list
                while(data.get(k).getProfitValues().size() != data.get(k).getNumberOfProfitValues()) {
                    line = in.readLine();
                    for(String value : line.split(" ")){
                        if(!value.equals("")) {
                            data.get(k).addCostValue(Integer.parseInt(value));
                        }
                    }
                }
                // Iterate through all weight values and add them to a weight set
                for (int m = 0; m < data.get(k).getNumberOfWeightSets(); m++) {
                    while(data.get(k).getWeightSet(m).getWeights().size() != data.get(k).getNumberOfProfitValues()) {
                        line = in.readLine();
                        for(String value : line.split(" ")){
                            if(!value.equals("")) {
                                data.get(k).getWeightSet(m).addWeight(Integer.parseInt(value));
                            }
                        }
                    }
                }
                line = in.readLine();
                int index = 0;
                // Iterate through all constraints and add them to the related weight set
                for(String value : line.split(" ")){
                    if(!value.equals("")) {
                        data.get(k).getWeightSet(index).setConstraint(Integer.parseInt(value));
                        index++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
        }
        return data;
    }
}
