package algopractice;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFrame;
import javax.swing.JFrame;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.ArrayList;
import java.util.Random;
public class AlgoGraph {

	private SwapAlgo algo;
	
	public AlgoGraph() {
		algo = new SwapAlgo();
	}
	
	/**
	 * creates a barchart using JFreeChart and adds the data set to it for display
	 * @param size
	 * @param bounds
	 */
	public void graphAll(int size, int bounds) {
		DefaultCategoryDataset graph = run(size, bounds);
		
		JFreeChart jf  = ChartFactory.createBarChart("Algorithm Runtimes", "Algorithms", "Time", graph);
		ChartFrame cf = new ChartFrame("Bar Chart", jf);
		
		cf.setSize(1280, 720);
		cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cf.setVisible(true);
	}
	
	/**
	 * runs every sort algorithm from SwapAlgo and adds the time data to a dataset for graphing
	 * @param size
	 * @param bounds
	 * @return
	 */
	private DefaultCategoryDataset run(int size, int bounds) {
		Random rng = new Random();
		DefaultCategoryDataset graph = new DefaultCategoryDataset();
		ArrayList<Integer> bub = new ArrayList<>();
		ArrayList<Integer> sel = new ArrayList<>();
		ArrayList<Integer> ins = new ArrayList<>();
		ArrayList<Integer> she = new ArrayList<>();
		ArrayList<Integer> mer = new ArrayList<>();
		ArrayList<Integer> qui = new ArrayList<>();
		ArrayList<Integer> hea = new ArrayList<>();
		
		String time = "Array Length: " + size;
		double runtime;
		for (int i = 0; i < size; i++)
		{
			int tmp = rng.nextInt(bounds);
			bub.add(tmp);
			sel.add(tmp);
			ins.add(tmp);
			she.add(tmp);
			mer.add(tmp);
			qui.add(tmp);
			hea.add(tmp);
		}
		
		algo.pStart();
		algo.bubble(bub);
		runtime = algo.pStop();
		graph.addValue(runtime, "Bubble", time);
		
		algo.pStart();
		algo.selection(sel);
		runtime = algo.pStop();
		graph.addValue(runtime, "Selection", time);
		
		algo.pStart();
		algo.insertion(ins);
		runtime = algo.pStop();
		graph.addValue(runtime, "Insertion", time);
		
		algo.pStart();
		algo.shell(she);
		runtime = algo.pStop();
		graph.addValue(runtime, "Shell", time);
		
		algo.pStart();
		algo.mergeSort(mer);
		runtime = algo.pStop();
		graph.addValue(runtime, "Merge", time);
		
		algo.pStart();
		algo.quickSort(qui);
		runtime = algo.pStop();
		graph.addValue(runtime, "Quick", time);
		
		algo.pStart();
		algo.heapSort(hea);
		runtime = algo.pStop();
		graph.addValue(runtime, "Heap", time);
		
		return graph;
	}
}
