import javax.swing.*;

public class HelloWorldPanel extends ChromosomePanel{

    public HelloWorldPanel(HelloWorldChromosome chromosome) {
        panel.add(new JLabel(chromosome.getTextGene()));
    }

}
