import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static java.lang.Math.abs;

public class Population {
	
	int populationSize = 100000;
	int mutationChance = 10;

	ArrayList<ArrayList<Chromosome>> populationHistory = new ArrayList<>();

    public Population(Chromosome chromosomeType){
		ArrayList<Chromosome> currentPopulation = new ArrayList<>();
		for(int i = 0; i < populationSize; i++){
			currentPopulation.add(chromosomeType.buildChromosome());
		}
		populationHistory.add(currentPopulation);
    }

    public void nextGeneration(){

    	// Select winners
        ArrayList<Chromosome> parents = Settings.selection.selectWinners(this.populationHistory.get(this.populationHistory.size() - 1));
		ArrayList<Chromosome> currentPopulation = mate(parents);
		
		// Mutate 1 in mutateChance chromosomes
		Random rand = new Random();
		for(Chromosome chromosome : currentPopulation){
			if(rand.nextInt(mutationChance) == 0){
				chromosome.mutate();
			}
		}

		// Should sort currentPopulation from lowest to highest fitness sort
		currentPopulation.sort(Comparator.naturalOrder());

		populationHistory.add(currentPopulation);
    }
	
	private ArrayList<Chromosome> mate(ArrayList<Chromosome> parents) {
		
		ArrayList<Chromosome> offspring = new ArrayList<>();
		
		int offset = 1;
		int count = 0;
		
		while(offspring.size() < populationSize){
			
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
