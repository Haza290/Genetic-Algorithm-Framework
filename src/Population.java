import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Population {

    ArrayList<Chromosome> currentPopulation = new ArrayList<>();

    public Population(Chromosome populationType){

    }

    public void nextGeneration(){
		
		Tornament tornament = new Tornament();

        ArrayList<Chromosome> parents = tornament.selectWinners(currentPopulation);
	
    }


}
