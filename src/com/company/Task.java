package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Task {
    public double min = -10000;
    public double max = 10000;
    public final int ITERATIONS = 1;
    public final int populationSize = 100;
    public final double F = 0.3;
    public final double CR = 0.8;
    public List<Double[]> population = new ArrayList<>(populationSize);
    public double bestValue;
    public Double[] bestPopulation;

    public void run(){
        initializePopulation();
        bestValue = F(population.get(0));
        bestPopulation = population.get(0);
        for(int a = 0; a < ITERATIONS; a ++){
            fullCycle();
        }

        System.out.println("Output of the algorithm:");
        System.out.println("The best value:");
        System.out.println(bestValue);
        System.out.println("The best gens:");
        printPopulation(bestPopulation);
    }
    public void fullCycle(){
        for(int i = 0; i < populationSize; i++){
            System.out.println("****");
            List<Double[]> currentPopulation = select(i);
            displayPopulation(currentPopulation);
            Double[] newGenotypes = mutate(currentPopulation);
            Double[] crossedGenotype = crossOver(currentPopulation,newGenotypes);
            System.out.println("F_crossed = " + F(crossedGenotype));
            System.out.println("F_pop = " + F(population.get(i)));
            if (F(crossedGenotype) <= F(population.get(i))){
                population.set(i, crossedGenotype);
                System.out.println("Better");
            }
            if (F(population.get(i)) < bestValue){
                bestValue = F(population.get(i));
                bestPopulation = population.get(i);
            }
            System.out.println("****");
        }
    }
    public Double F(Double[] values){
        return values[0] + values[1] - 3 * values[2];
    }
    public void initializePopulation(){
        for (int i = 0; i < populationSize; i++) {
            population.add(generateSample());
        }
    }
    public Double[] mutate(List<Double[]> currentGenotypes){
        Double[] mutationScore = new Double[3];
        for(int i = 0; i<3; i++){
            mutationScore[i] = currentGenotypes.get(0)[i] + F * (currentGenotypes.get(1)[i] - currentGenotypes.get(1)[2]);
        }
        for (Double aDouble : mutationScore) {
            System.out.print(aDouble + " ");
        }
        System.out.print("\n");
        return mutationScore;
    }
    public Double[] crossOver(List<Double[]> currentGenotypes, Double[] newGenotypes){
        Double[] crossedGenotype = new Double[3];
        for(int i = 0; i < newGenotypes.length; i++){
            double x = ThreadLocalRandom.current().nextDouble(0, 1);
            if(x <= CR) crossedGenotype[i] = newGenotypes[i];
            else crossedGenotype[i] = currentGenotypes.get(0)[i];
        }
        System.out.println("Po krzyzowce: ");
        for (int i = 0; i < 3; i++) {
            System.out.print(crossedGenotype[i] + " ");
        }
        System.out.print("\n");
        return crossedGenotype;
    }
    public List<Double[]> select(int currentIndex){
        Random r = new Random();
        int secondSample;
        int thirdSample;
        do{
            secondSample = r.nextInt(populationSize);
            thirdSample = r.nextInt(populationSize);
        }while(currentIndex == secondSample || currentIndex == thirdSample || secondSample == thirdSample);
        List<Double[]> output = new ArrayList<>();
        output.add(population.get(currentIndex));
        output.add(population.get(secondSample));
        output.add(population.get(thirdSample));
        return output;
    }
    public void displayPopulation(List<Double[]> populationToDisplay){
        for (Double[] doubles : populationToDisplay) {
            int i = population.indexOf(doubles);
            System.out.println(i + ". " + doubles[0] + " " + doubles[1] + " " + doubles[2]);
        }
    }
    public Double[] generateSample(){
        double x1 = ThreadLocalRandom.current().nextDouble(min, max);
        double x2 = ThreadLocalRandom.current().nextDouble(min, max);
        double x3 = ThreadLocalRandom.current().nextDouble(min, max);
        return new Double[]{x1,x2,x3};
    }
    public void printPopulation(Double[] gens){
        for (Double gen : gens) {
            System.out.print(gen + " ");
        }
        System.out.print("\n");
    }
}

