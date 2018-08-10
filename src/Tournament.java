
import java.util.*;
public class Tournament implements Selection
{

	@Override
	public ArrayList<Chromosome> selectWinners(ArrayList<Chromosome> population)
	{
		
		int tournamentSize = 2;
        int numOfParents = population.size()/2;
        final ArrayList<Chromosome> tournamentWinners = new ArrayList<>();
        final ArrayList<Chromosome> paternalParents = new ArrayList<>(population);

        boolean returnWinner = false;
        boolean isFitness = false;

        Random rand = new Random();
        for (int i = 0; i < numOfParents; i++) {
            ArrayList<Chromosome> tournamentParticipants = new ArrayList<>();
            for (int j = 0; j < tournamentSize; j++) {

                int randomChromosomeIndex = rand.nextInt(paternalParents.size());
                while (tournamentParticipants.contains(paternalParents.get(randomChromosomeIndex))) {
                    randomChromosomeIndex = rand.nextInt(paternalParents.size());
                }
                tournamentParticipants.add(paternalParents.get(randomChromosomeIndex));
            }

            Chromosome currentWinner = null;
            double bestFitness = 0;
            for (int k = 0; k < tournamentSize; k++) {
                if (isFitness) {
                    if (tournamentParticipants.get(k).getFitness() > bestFitness || null == currentWinner) {
                        bestFitness = tournamentParticipants.get(k).getFitness();
                        currentWinner = tournamentParticipants.get(k);
                    }
                } else {
                    if (tournamentParticipants.get(k).getFitness() < bestFitness || null == currentWinner) {
                        bestFitness = tournamentParticipants.get(k).getFitness();
                        currentWinner = tournamentParticipants.get(k);
                    }
                }
            }
            tournamentWinners.add(currentWinner);

            // Remove the tournament winner from the pool if returnWinner flag isn't set
            if (!returnWinner) {
                paternalParents.remove(currentWinner);
            }
        }

        return tournamentWinners;
    }

}
