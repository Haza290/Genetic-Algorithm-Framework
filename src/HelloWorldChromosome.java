import java.util.Random;

import static java.lang.Math.abs;

public class HelloWorldChromosome implements Chromosome {

    private final String textGene;
    private static String TARGET = "helloworld";

    @Override
    public Chromosome mate(Chromosome partner) {

        // takes the first half of this chromosomes textGene and combines it with the second half of the partners textGene
        final String childTextGene = textGene.substring(0,textGene.length()/2) +
                ((HelloWorldChromosome) partner).getTextGene().substring(textGene.length()/2);

        return new HelloWorldChromosome(childTextGene);
    }

    @Override
    public void mutate() {
        final char[] textGeneCharArray = this.textGene.toCharArray();
        final Random rand = new Random();
        int offset = 1;
        if (rand.nextBoolean()){
            offset = -1;
        }
        final int randomGeneIndex = textGeneCharArray.length;

        // Adds or subtracts 1 char value from one of the letters
        textGeneCharArray[rand.nextInt(randomGeneIndex)] = (char)((textGeneCharArray[rand.nextInt(randomGeneIndex)] + offset) % 26);
    }

    @Override
    public float getFitness() {
        return generateFitnessScore();
    }

    private float generateFitnessScore() {
        final char[] textGeneCharArray = this.textGene.toCharArray();
        final char[] targetCharArray = this.TARGET.toCharArray();

        float score = 0;
        for(int i = 0; i < textGeneCharArray.length; i++) {
            score += abs((int) textGeneCharArray[i] - (int) targetCharArray[i]);
        }

        return score;
    }


    public HelloWorldChromosome(){
        this.textGene = generateRandomString();
    }

    public HelloWorldChromosome(final String text){
        this.textGene = text;
    }

    private String generateRandomString() {
        final char[] charArray = new char[TARGET.length()];
        final Random rand = new Random();
        for(int i = 0; i < charArray.length; i++) {
            charArray[i] = (char)(rand.nextInt(26) + 97);
        }
        return String.valueOf(charArray);
    }

    public String getTextGene() {
        return textGene;
    }
}
