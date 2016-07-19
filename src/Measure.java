import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.LinkedList;

public class Measure {
	
	
	private LinkedList<Measure> measures;
	private PrintStream logStream = null;


    private String name;
    private long start;
    private int counter;
    DecimalFormat f = new DecimalFormat("#0.000");
    DecimalFormat f1 = new DecimalFormat("#0");

    public Measure(String name){
        this.name=name;
        counter = 0;
        try {
            int index = 0;
            while((new File(name+index+".txt").exists())){
                index++;
            }

            logStream = new PrintStream(new File(name+index+".txt"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logStream.println("Iteration:\tTime [ms]:\tBestSolution:\tSolution:");
    }

    private String getName() {
        return name;
    }

    public void startSample(){
        start = System.nanoTime();
    }

    public void sample(double bestSolutionValue, double solutionValue){
        logStream.println(counter + ";\t\t\t" + f.format((System.nanoTime() - start) / 1000000.0) + ";\t\t" + f1.format(bestSolutionValue) + ";\t\t\t" + f1.format(solutionValue) + ";");
        counter++;
    }



}
