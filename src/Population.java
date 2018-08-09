import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Math.abs;

public class Population {
	
	int populationSize = 100000;
	int mutationChance = 10;

	ArrayList<Chromosome> currentPopulation = new ArrayList<>();
	ArrayList<ArrayList<Chromosome>> populationHistory = new ArrayList<>();
	ArrayList<Chromosome> bestChromosomesHistory = new ArrayList<>();
	ArrayList<Chromosome> averageChromosomesHistory = new ArrayList<>();
	ArrayList<Chromosome> worstChromosomesHistory = new ArrayList<>();

    public Population(Chromosome chromosomeType){
		for(int i = 0; i < populationSize; i++){
			currentPopulation.add(chromosomeType.buildChromosome());
		}
    }

    public void nextGeneration(){
		
		// add a record of currentPopulation to history array
		populationHistory.add(currentPopulation);
		
		// Pick breeding chomosomes and mate them
		Tornament tornament = new Tornament();
        ArrayList<Chromosome> parents = tornament.selectWinners(currentPopulation);
		currentPopulation = mate(parents);
		
		// Mutate 1 in mutateChance chromosomes
		Random rand = new Random();
		for(Chromosome chromosome : currentPopulation){
			if(rand.nextInt(mutationChance) == 0){
				chromosome.mutate();
			}
		}

		// Should sort currentPopulation from lowest to highest fitness sort
		Collections.sort(this.currentPopulation);

		System.out.println("Next gen average is: " + String.valueOf(getAverageScore()));
		System.out.println("Next gen best is:    " + String.valueOf(getCurrentBestChromosome().getFitness()));

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
			if(count >= populationSize) {
				offset++;
				count = 0;
			}
		}
		return offspring;
	}

	public Chromosome getCurrentBestChromosome(){
    	Chromosome currentBestChromosome = null;
    	float currentBestChromosomeScore = 0;
    	for(int i = 0; i < this.currentPopulation.size(); i++) {
			Chromosome chromosome = this.currentPopulation.get(i);
			if(currentBestChromosome == null || chromosome.getFitness() < currentBestChromosomeScore) {
    			currentBestChromosome = chromosome;
    			currentBestChromosomeScore = chromosome.getFitness();
			}
		}
		return currentBestChromosome;
	}

	public Chromosome getCurrentWorstChromosome(){
		Chromosome currentWorstChromosome = null;
		float currentBestChromosomeScore = 0;
		for(int i = 0; i < this.currentPopulation.size(); i++) {
			Chromosome chromosome = this.currentPopulation.get(i);
			if(currentWorstChromosome == null || chromosome.getFitness() > currentBestChromosomeScore) {
				currentWorstChromosome = chromosome;
				currentBestChromosomeScore = chromosome.getFitness();
			}
		}
		return currentWorstChromosome;
	}

	public float getAverageScore(){
    	float totalScore = 0;
		for (Chromosome chromosome: this.currentPopulation) {
			totalScore += chromosome.getFitness();
		}

		return totalScore/this.currentPopulation.size();
	}

	public Chromosome getCurrentAverageChromosome() {

    	float averageScore = getAverageScore();
    	Chromosome closestChromosomeToAverage = null;
    	float distanceFromAverage = 0;

		for(int i = 0; i < this.currentPopulation.size(); i++) {

			Chromosome currentChromosome = this.currentPopulation.get(i);
			float absCurrentChromosomeDistanceFromAverage = abs(currentChromosome.getFitness() - averageScore);
			if(closestChromosomeToAverage == null || absCurrentChromosomeDistanceFromAverage < distanceFromAverage) {
				closestChromosomeToAverage = currentChromosome;
				distanceFromAverage = absCurrentChromosomeDistanceFromAverage;
			}
		}

		return closestChromosomeToAverage;
	}
}
