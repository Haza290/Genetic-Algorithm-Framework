import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Population {
	
	private int POPULATION_SIZE = 100000;
	private int MUTATION_CHANCE = 10;

	private ArrayList<ArrayList<Chromosome>> populationHistory = new ArrayList<>();

    public Population(Chromosome chromosomeType){
		ArrayList<Chromosome> currentPopulation = new ArrayList<>();
		for(int i = 0; i < POPULATION_SIZE; i++){
			currentPopulation.add(chromosomeType.buildChromosome());
		}

		populationHistory.add(currentPopulation);

		// TODO return to parallelising the process
		// Parallel stream generating population
//		ArrayList<Chromosome> streamGeneratedArrayList = Stream.generate(chromosomeType::buildChromosome)
//				.limit(POPULATION_SIZE)
//				.parallel()
//				.collect(Collectors.toCollection(ArrayList::new));
//
//		populationHistory.add(streamGeneratedArrayList);

    }

    public void nextGeneration(){

    	// Select winners
        ArrayList<Chromosome> parents = Settings.selection.selectWinners(this.populationHistory.get(this.populationHistory.size() - 1));
		ArrayList<Chromosome> currentPopulation = mate(parents);
		
		// Mutate 1 in mutateChance chromosomes
		Random rand = new Random();
		for(Chromosome chromosome : currentPopulation){
			if(rand.nextInt(MUTATION_CHANCE) == 0){
				chromosome.mutate();
			}
		}

		// Should sort currentPopulation from lowest to highest fitness sort
		currentPopulation.sort(Comparator.naturalOrder());

		populationHistory.add(currentPopulation);
    }

    // TODO come back to parallelising this method
	private ArrayList<Chromosome> mate(ArrayList<Chromosome> parents) {
		
		ArrayList<Chromosome> offspring = new ArrayList<>();
		
		int offset = 1;
		int count = 0;
		
		while(offspring.size() < POPULATION_SIZE){
			
			Chromosome parent1 = parents.get(count);
			Chromosome parent2 = parents.get((count + offset) % parents.size());
			
			offspring.add(parent1.mate(parent2));
			offspring.add(parent2.mate(parent1));
			
			count++;
			if(count >= parents.size()) {
				offset++;
				count = 0;
				if(offset >= parents.size() - 1) {
					offset = 1;
				}
			}
		}

		return offspring;
	}


	public double getAverageScore(){
		return getAverageScore(this.populationHistory.size() - 1);
		}

	public double getAverageScore(int generation){
		return this.populationHistory.get(generation).stream().mapToDouble(Chromosome::getFitness).average().orElse(-1D);
	}

	public Chromosome getAverageChromosome() {
    	return getAverageChromosome(this.populationHistory.size() - 1);
	}

	public Chromosome getAverageChromosome(int generation) {

    	ArrayList<Chromosome> generationArray = this.populationHistory.get(generation);

    	int lowerIndex = 0;
    	int upperIndex = generationArray.size();
    	double averageFitness = getAverageScore();

		while (lowerIndex + 1 != upperIndex){
    		int midPoint = (upperIndex + lowerIndex) / 2;
    		if(generationArray.get(midPoint).getFitness() < averageFitness){
    			lowerIndex = midPoint;
			} else {
    			upperIndex = midPoint;
			}
		}

		if(averageFitness - generationArray.get(lowerIndex).getFitness() < generationArray.get(upperIndex).getFitness() - averageFitness) {
    		return generationArray.get(lowerIndex);
		} else {
    		return generationArray.get(upperIndex);
		}
	}
}
