import javax.swing.*;
import java.awt.*;

public class BestAverageWorstPanel {

    JPanel bestAverageWorstPanel = new JPanel();

    public BestAverageWorstPanel(Chromosome bestChromosome, Chromosome averageChromosome, Chromosome worstChromosome) {
        this.bestAverageWorstPanel.setLayout(new GridLayout(1,3));

        JPanel bestPanel = new JPanel();
        bestPanel.setLayout(new GridLayout(3,1));
        bestPanel.add(new JLabel("Best Chromosome"));
        if(null != bestChromosome.getJPanel()) {
            bestPanel.add(bestChromosome.getJPanel());
        }
        bestPanel.add(new JLabel(Double.toString(bestChromosome.getFitness())));

        JPanel averagePanel = new JPanel();
        averagePanel.setLayout(new GridLayout(3,1));
        averagePanel.add(new JLabel("Average Chromosome"));
        if(null != averageChromosome.getJPanel()) {
            averagePanel.add(averageChromosome.getJPanel());
        }
        averagePanel.add(new JLabel(Double.toString(averageChromosome.getFitness())));

        JPanel worstPanel = new JPanel();
        worstPanel.setLayout(new GridLayout(3,1));
        worstPanel.add(new JLabel("Worst Chromosome"));
        if(null != worstChromosome.getJPanel()) {
            worstPanel.add(worstChromosome.getJPanel());
        }
        worstPanel.add(new JLabel(Double.toString(worstChromosome.getFitness())));

        this.bestAverageWorstPanel.add(bestPanel);
        this.bestAverageWorstPanel.add(averagePanel);
        this.bestAverageWorstPanel.add(worstPanel);
    }

    public JPanel getBestAverageWorstPanel() {
        return bestAverageWorstPanel;
    }

}
