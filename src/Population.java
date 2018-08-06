import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {
	
	int populationSize = 100;
	int mutationChance = 100;

	ArrayList<ArrayList<Chromosome>> populationHistory = new ArrayList<>();
    ArrayList<Chromosome> currentPopulation = new ArrayList<>();

    public Population(Chromosome populationType){
		for(int i = 0; i < populationSize; i++){
			currentPopulation.add(new HelloWorldChromosome());
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
}
