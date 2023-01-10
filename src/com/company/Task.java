package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Task {
    public final double MIN = -10000;
    public final double MAX = 10000;
    public final int SAMPLE_SIZE = 4;
    public final int POPULATION_SIZE = 100;
    public final int ITERATIONS = 1;
    public final double F = 0.3;
    public final double CR = 0.8;
    public List<Double[]> population = new ArrayList<>(POPULATION_SIZE);
    public double bestValue;
    public Double[] bestPopulation;

    public void run(){
        initializePopulation();
        bestValue = F(population.get(0));
        bestPopulation = population.get(0);
        for(int a = 0; a < ITERATIONS; a ++){
            fullCycle();
        }

        System.out.println("Output of the algorithm");
        System.out.println("The best value:");
        System.out.println(bestValue);
        System.out.println("The best genes:");
        printSample(bestPopulation);
    }
    public void fullCycle(){
        for(int i = 0; i < POPULATION_SIZE; i++){
            System.out.println("****");
            List<Double[]> currentPopulation = select(i);
            printPopulation(currentPopulation);
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
    public Double[] generateSample(){
        Double[] sample = new Double[SAMPLE_SIZE];
        for (int i = 0; i < sample.length; i++) {
            sample[i] = ThreadLocalRandom.current().nextDouble(MIN, MAX);
        }
        return sample;
    }

    public void initializePopulation(){
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(generateSample());
        }
    }
    public Double[] mutate(List<Double[]> currentGenotypes){
        Double[] mutationScore = new Double[SAMPLE_SIZE];
        for(int i = 0; i<SAMPLE_SIZE; i++){
            mutationScore[i] = currentGenotypes.get(0)[i] + F * (currentGenotypes.get(1)[i] - currentGenotypes.get(2)[i] - currentGenotypes.get(3)[i]);
        }
        for (Double aDouble : mutationScore) {
            System.out.print(aDouble + " ");
        }
        System.out.print("\n");
        return mutationScore;
    }
    public Double[] crossOver(List<Double[]> currentGenotypes, Double[] newGenotypes){
        Double[] crossedGenotype = new Double[SAMPLE_SIZE];
        for(int i = 0; i < newGenotypes.length; i++){
            double x = ThreadLocalRandom.current().nextDouble(0, 1);
            if(x <= CR) crossedGenotype[i] = newGenotypes[i];
            else crossedGenotype[i] = currentGenotypes.get(0)[i];
        }
        System.out.println("After crossover:");
        for (int i = 0; i < SAMPLE_SIZE; i++) {
            System.out.print(crossedGenotype[i] + " ");
        }
        System.out.print("\n");
        return crossedGenotype;
    }
    public List<Double[]> select(int currentIndex){
        Random r = new Random();
        int secondSample;
        int thirdSample;
        int fourthSample;
        do{
            secondSample = r.nextInt(POPULATION_SIZE);
            thirdSample = r.nextInt(POPULATION_SIZE);
            fourthSample = r.nextInt(POPULATION_SIZE);
        }while(currentIndex == secondSample || currentIndex == thirdSample || currentIndex == fourthSample || secondSample == thirdSample || thirdSample == fourthSample);
        List<Double[]> output = new ArrayList<>();
        output.add(population.get(currentIndex));
        output.add(population.get(secondSample));
        output.add(population.get(thirdSample));
        output.add(population.get(fourthSample));
        return output;
    }

    public Double F(Double[] x){
        return Math.pow(x[0],2) - 2 * Math.pow(x[1],2) * Math.pow(x[2],2) + 3 * Math.pow(x[0],2) * Math.pow(x[3], 2);
    }


    public void printPopulation(List<Double[]> populationToDisplay){
        for (Double[] doubles : populationToDisplay) {
            for (Double aDouble : doubles) {
                System.out.print(aDouble + " ");
            }
            System.out.println();
        }
    }
    public void printSample(Double[] genes){
        for (Double gen : genes) {
            System.out.print(gen + " ");
        }
        System.out.print("\n");
    }
}

