import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {

    ArrayList<Chromosome> currentPopulation = new ArrayList<>();

    public Population(Chromosome populationType){

    }

    public void nextGeneration(){

        ArrayList<Chromosome> parents = tournament(currentPopulation);
    }

    private ArrayList<Chromosome> tournament(ArrayList<Chromosome> population) {

        int tornamentSize = 5;
        int numOfParents = population.size()/2;
        final ArrayList<Chromosome> tornamentWinners = new ArrayList<>();
        final ArrayList<Chromosome> partentalParents = new ArrayList<>();
        Collections.copy(partentalParents, population);
        boolean returnWinner = false;
        boolean isFitness = false;

        Random rand = new Random();
        for (int i = 0; i < numOfParents; i++) {
            ArrayList<Chromosome> tournamentParticipants = new ArrayList<>();
            for (int j = 0; j < tornamentSize; j++) {

                int randomChromosomeIndex = rand.nextInt(partentalParents.size());
                while(tournamentParticipants.contains(tournamentParticipants.contains(partentalParents.get(randomChromosomeIndex)))) {
                    randomChromosomeIndex = rand.nextInt(partentalParents.size());
                }

                Chromosome currentWinner = null;
                float bestFitness = 0;
                for (int k = 0; k < tornamentSize; k++) {
                    if(isFitness) {
                        if (tournamentParticipants.get(k).getFitness() > bestFitness || null == currentWinner) {
                            bestFitness = tournamentParticipants.get(k).getFitness();
                            currentWinner = tournamentParticipants.get(k);
                        }
                    }
                }
                tornamentWinners.add(currentWinner);

                // Remove the tournament winner from the pool if returnWinner flag isn't set
                if (!returnWinner) {
                    partentalParents.remove(currentWinner);
                }
            }
        }


        return tornamentWinners;
    }

}
