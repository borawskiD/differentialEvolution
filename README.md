## Differential Evolution
It is a bit different algorithm than PSO. It is based on similar rules (a population of points randomly placed in space) but the evolution works more randomly. For every iteration for every sample we randomly choose 2 different samples and mix the values. After that there is one more random layer - we generate float in a range of 0 to 1 which decides if the sample will go to the population or be rejected. It is one of the simplest optimization algorithms, but it perfectly presents every aspect of Evolutionary Algorithms.
<br />
![Differential Evolution visualisation](https://upload.wikimedia.org/wikipedia/commons/e/e0/Ackley.gif) <br />
[By Pablormier - https://pablormier.github.io/2017/09/05/a-tutorial-on-differential-evolution-with-python, CC BY-SA 4.0, https://commons.wikimedia.org/w/index.php?curid=62208829]
## How to run it?
Code can be found in /src/ directory, download it and run using IntelliJ IDE or another IDE w/ Java compilator.
