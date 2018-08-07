public interface Chromosome {

    public Chromosome mate(Chromosome partner);
    public Chromosome buildChromosome();
    public void mutate();
    public float getFitness();

}
