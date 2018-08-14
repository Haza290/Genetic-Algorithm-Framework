import javax.swing.*;

public interface Chromosome extends Comparable<Chromosome> {

    public Chromosome mate(Chromosome partner);
    public Chromosome buildChromosome();
    public void mutate();
    public double getFitness();
    public JPanel getJPanel();

    // Used in sorting arrays of Chromosomes
    @Override
    public default int compareTo(Chromosome otherChromosome) {
        return Double.compare(this.getFitness(), otherChromosome.getFitness());
    }


}
