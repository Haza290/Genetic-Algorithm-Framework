public interface Chromosome {

    public Chromosome mate(Chromosome partner);
    public void mutate();
    public float getFitness();

}
