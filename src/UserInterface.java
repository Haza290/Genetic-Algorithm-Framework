import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {

    Population population = new Population();
    JPanel bestWorstAveragePanel;
    JFrame mainFrame = new JFrame();
    JLabel bestChromosomeString = new JLabel();

    public static void main(String[] args){
        new UserInterface();
    }

    public UserInterface() {

        HelloWorldChromosome test = (HelloWorldChromosome)population.getCurrentBestChromosome();
        System.out.println("best text: " + test.getTextGene());


        mainFrame.setLayout(new GridLayout(2,2));

        bestWorstAveragePanel = updateBestWorstAveragePanel();
        JPanel graphPanel = new JPanel();
        JPanel populationPanel = new JPanel();
        JPanel controlsPanel = new JPanel();

        mainFrame.add(bestWorstAveragePanel);
        mainFrame.add(graphPanel);
        mainFrame.add(populationPanel);
        mainFrame.add(updateControlsPanel());

        mainFrame.pack();
        mainFrame.setVisible(true);
    }


    private JPanel updateBestWorstAveragePanel(){

        JPanel bestWorstAverageFrame = new JPanel();
        bestWorstAverageFrame.setLayout(new FlowLayout());

        HelloWorldChromosome bestChromosome    = (HelloWorldChromosome) population.getCurrentBestChromosome();
        HelloWorldChromosome averageChromosome = (HelloWorldChromosome) population.getCurrentAverageChromosome();
        HelloWorldChromosome worstChromosome   = (HelloWorldChromosome) population.getCurrentWorstChromosome();

        JPanel bestPanel = new JPanel(new GridLayout(3,1));
        JLabel bestTitleLabel = new JLabel("Best Chromosome");
        bestChromosomeString.setText(bestChromosome.getTextGene());
        JLabel bestChromosomeScore = new JLabel(String.valueOf(bestChromosome.getFitness()));
        bestPanel.add(bestTitleLabel);
        bestPanel.add(bestChromosomeString);
        bestPanel.add(bestChromosomeScore);

        JPanel averagePanel = new JPanel(new GridLayout(3,1));
        JLabel averageTitleLabel = new JLabel("Average Chromosome");
        JLabel averageChromosomeString = new JLabel(averageChromosome.getTextGene());
        JLabel averageChromosomeScore = new JLabel(String.valueOf(averageChromosome.getFitness()));
        averagePanel.add(averageTitleLabel);
        averagePanel.add(averageChromosomeString);
        averagePanel.add(averageChromosomeScore);

        JPanel worstPanel = new JPanel(new GridLayout(3,1));
        JLabel worstTitleLabel = new JLabel("Worst Chromosome");
        JLabel worstChromosomeString = new JLabel(worstChromosome.getTextGene());
        JLabel worstChromosomeScore = new JLabel(String.valueOf(worstChromosome.getFitness()));
        worstPanel.add(worstTitleLabel);
        worstPanel.add(worstChromosomeString);
        worstPanel.add(worstChromosomeScore);

        bestWorstAverageFrame.add(bestPanel);
        bestWorstAverageFrame.add(averagePanel);
        bestWorstAverageFrame.add(worstPanel);

        return bestWorstAverageFrame;
    }

    private JPanel updateControlsPanel() {
        JPanel controlsPanel = new JPanel();

        JButton nextGenerationButton = new JButton("Next Generation");
        nextGenerationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                population.nextGeneration();
                //bestWorstAveragePanel = updateBestWorstAveragePanel();

                HelloWorldChromosome bestChromosome = (HelloWorldChromosome) population.getCurrentBestChromosome();
                bestChromosomeString.setText(bestChromosome.getTextGene());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

        controlsPanel.add(nextGenerationButton);

        return controlsPanel;
    }

}
