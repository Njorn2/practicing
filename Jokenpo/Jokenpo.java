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

        Gesture pedra = new Gesture("PEDRA");
        Gesture papel = new Gesture("PAPEL");
        Gesture tesoura = new Gesture("TESOURA");
        
        pedra.setLoses(Arrays.asList(papel));
        papel.setLoses(Arrays.asList(tesoura));
        tesoura.setLoses(Arrays.asList(pedra));
        
        tesoura.setWins(Arrays.asList(papel));
        pedra.setWins(Arrays.asList(tesoura));
        papel.setWins(Arrays.asList(pedra));
        
        addGesture(pedra);
        addGesture(papel);
        addGesture(tesoura);
    }
    
    public void addGesture(Gesture gesture) {
        gestures.put(gesture.gesture, gesture);
    }

    public Gesture getGesture(GestureEnum gestureName) {
        return gestures.get(gestureName.toString());
    }
}

enum GestureEnum {
    PEDRA { 
        @Override
        public String toString() {
          return "PEDRA";
        }
      },
    PAPEL { 
        @Override
        public String toString() {
          return "PAPEL";
        }
      },
    TESOURA { 
        @Override
        public String toString() {
          return "TESOURA";
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
    GANHA,
    PERDE,
    EMPATA;
}

class Game {
    Player player1;
    Player player2;
    
    public Game() {
        player1 = new Player();
        player2 = new Player();
    }

    public void play() {
        player1.setGesture(GestureEnum.PEDRA);
        player2.setGesture(GestureEnum.PAPEL);
        System.out.println("Round 1: Player 1 " + compare(player1, player2));
        
        player1.setGesture(GestureEnum.PEDRA);
        player2.setGesture(GestureEnum.TESOURA);
        System.out.println("Round 2: Player 1 " + compare(player1, player2));
        
        player1.setGesture(GestureEnum.PEDRA);
        player2.setGesture(GestureEnum.PEDRA);
        System.out.println("Round 3: " + compare(player1, player2));
    }

    private Result compare(Player player1, Player player2) {
        Gestures gestures = new Gestures();
        Gesture gesture1 = gestures.getGesture(player1.getGesture());
        Gesture gesture2 = gestures.getGesture(player2.getGesture());

        if (gesture1.getLoses().contains(gesture2)) {
            return Result.PERDE;
        }
        if (gesture1.getWins().contains(gesture2)) {
            return Result.GANHA;
        }
        return Result.EMPATA;
    }
}

public class Jokenpo {
    public static void main(String[] args) {
        new Game().play();
    }
}
