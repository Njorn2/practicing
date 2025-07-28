import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

class Gesture {
    String gesture;
    List<Gesture> loses;
    List<Gesture> wins;
    
    public Gesture(String gesture) {
        this.gesture = gesture;
    }

    public List<Gesture> getLoses() {
        return loses;
    }

    public void setLoses(List<Gesture> loses) {
        this.loses = loses;
    }

    public List<Gesture> getWins() {
        return wins;
    }

    public void setWins(List<Gesture> wins) {
        this.wins = wins;
    }
    
}

class Gestures {
    HashMap<String, Gesture> gestures;

    public Gestures() {
        gestures = new HashMap<>();

        Gesture rock = new Gesture(GestureEnum.ROCK.toString());
        Gesture paper = new Gesture(GestureEnum.PAPER.toString());
        Gesture scissors = new Gesture(GestureEnum.SCISSORS.toString());
        
        rock.setLoses(Arrays.asList(paper));
        paper.setLoses(Arrays.asList(scissors));
        scissors.setLoses(Arrays.asList(rock));
        
        scissors.setWins(Arrays.asList(paper));
        rock.setWins(Arrays.asList(scissors));
        paper.setWins(Arrays.asList(rock));
        
        addGesture(rock);
        addGesture(paper);
        addGesture(scissors);
    }
    
    public void addGesture(Gesture gesture) {
        gestures.put(gesture.gesture, gesture);
    }

    public Gesture getGesture(GestureEnum gestureName) {
        return gestures.get(gestureName.toString());
    }
}

enum GestureEnum {
    ROCK { 
        @Override
        public String toString() {
          return "ROCK";
        }
      },
    PAPER { 
        @Override
        public String toString() {
          return "PAPER";
        }
      },
    SCISSORS { 
        @Override
        public String toString() {
          return "SCISSORS";
        }
      };
}

class Player {
    GestureEnum gesture;

    public GestureEnum getGesture() {
        return gesture;
    }
    public void setGesture(GestureEnum gesture) {
        this.gesture = gesture;
    }
}

enum Result {
    WINS,
    LOSES,
    TIED;
}

class Game {
    Player player1;
    Player player2;
    
    public Game() {
        player1 = new Player();
        player2 = new Player();
    }

    public void play() {
        player1.setGesture(GestureEnum.ROCK);
        player2.setGesture(GestureEnum.PAPER);
        System.out.println("Round 1: Player 1 " + compare(player1, player2));
        
        player1.setGesture(GestureEnum.ROCK);
        player2.setGesture(GestureEnum.SCISSORS);
        System.out.println("Round 2: Player 1 " + compare(player1, player2));
        
        player1.setGesture(GestureEnum.ROCK);
        player2.setGesture(GestureEnum.ROCK);
        System.out.println("Round 3: " + compare(player1, player2));
    }

    private Result compare(Player player1, Player player2) {
        Gestures gestures = new Gestures();
        Gesture gesture1 = gestures.getGesture(player1.getGesture());
        Gesture gesture2 = gestures.getGesture(player2.getGesture());

        if (gesture1.getLoses().contains(gesture2)) {
            return Result.LOSES;
        }
        if (gesture1.getWins().contains(gesture2)) {
            return Result.WINS;
        }
        return Result.TIED;
    }
}

public class Jokenpo {
    public static void main(String[] args) {
        new Game().play();
    }
}
