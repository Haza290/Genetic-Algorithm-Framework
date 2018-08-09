public interface Chromosome extends Comparable<Chromosome> {

    public Chromosome mate(Chromosome partner);
    public Chromosome buildChromosome();
    public void mutate();
    public float getFitness();

    // TODO come back and check this for cases where the int casting of the float operation causes the wrong ordering of chromosomes
    // Think this currently sets the first index to be the chromosome with the lowest fittness.
    @Override
    public default int compareTo(Chromosome chromosome) {
        return (int) (this.getFitness() - chromosome.getFitness());
    }

}
