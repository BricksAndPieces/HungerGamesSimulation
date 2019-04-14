# HungerGamesSimulation
This is a hungergames.simulation created in java of the Hunger Games in the popular series by Suzanne Collins  
Based of [BrantSteele's Hunger Games Simulator](https://brainsteele.net/hungergames/)

Feel free to contribute if you notice any bugs or want to help this project grow!

**Usage**
```java
// Create as many Tribute objects as needed
Tribute tribute = new Tribute("NAME", new BufferedImage("IMAGE"));

// create a hunger games object using the builder

HungerGamesBuilder hgb = new HungerGamesBuilder()
  .addTribute(tribute);
  
HungerGames hg = hgb.build();

while(hg.getWinner() == null) {
  System.out.println(hg.nextEvent().getText())
}

// More code docs here once project finished
```
