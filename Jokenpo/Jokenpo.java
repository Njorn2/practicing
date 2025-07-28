import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

class Gesto {
    String gesto;
    List<Gesto> perde;
    List<Gesto> ganha;
    
    public Gesto(String gesto) {
        this.gesto = gesto;
    }

    public List<Gesto> getPerde() {
        return perde;
    }

    public void setPerde(List<Gesto> perde) {
        this.perde = perde;
    }

    public List<Gesto> getGanha() {
        return ganha;
    }

    public void setGanha(List<Gesto> ganha) {
        this.ganha = ganha;
    }
    
}

class Gestos {
    HashMap<String, Gesto> gestos;

    public Gestos() {
        gestos = new HashMap<>();

        Gesto pedra = new Gesto("PEDRA");
        Gesto papel = new Gesto("PAPEL");
        Gesto tesoura = new Gesto("TESOURA");
        
        pedra.setPerde(Arrays.asList(papel));
        papel.setPerde(Arrays.asList(tesoura));
        tesoura.setPerde(Arrays.asList(pedra));
        
        tesoura.setGanha(Arrays.asList(papel));
        pedra.setGanha(Arrays.asList(tesoura));
        papel.setGanha(Arrays.asList(pedra));
        
        addGesto(pedra);
        addGesto(papel);
        addGesto(tesoura);
    }
    
    public void addGesto(Gesto gesto) {
        gestos.put(gesto.gesto, gesto);
    }

    public Gesto getGesto(GestosEnum nome) {
        return gestos.get(nome.toString());
    }
}

enum GestosEnum {
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

class Jogador {
    GestosEnum gesto;

    public GestosEnum getGesto() {
        return gesto;
    }
    public void setGest(GestosEnum gesto) {
        this.gesto = gesto;
    }
}

enum Resultado {
    GANHA,
    PERDE,
    EMPATA;
}

class Jogo {
    Jogador jogador1;
    Jogador jogador2;
    
    public Jogo() {
        jogador1 = new Jogador();
        jogador2 = new Jogador();
    }

    public void jogar() {
        jogador1.gesto = GestosEnum.PEDRA;
        jogador2.gesto = GestosEnum.PAPEL;
        System.out.println("Jogo 1: Jogador 1 " + compare(jogador1, jogador2));
        
        jogador1.gesto = GestosEnum.PEDRA;
        jogador2.gesto = GestosEnum.TESOURA;
        System.out.println("Jogo 2: Jogador 1 " + compare(jogador1, jogador2));
        
        jogador1.gesto = GestosEnum.PEDRA;
        jogador2.gesto = GestosEnum.PEDRA;
        System.out.println("Jogo 3: " + compare(jogador1, jogador2));
    }

    private Resultado compare(Jogador jogador1, Jogador jogador2) {
        Gestos gestos = new Gestos();
        Gesto gesto1 = gestos.getGesto(jogador1.gesto);
        Gesto gesto2 = gestos.getGesto(jogador2.gesto);

        if (gesto1.perde.contains(gesto2)) {
            return Resultado.PERDE;
        }
        if (gesto1.ganha.contains(gesto2)) {
            return Resultado.GANHA;
        }
        return Resultado.EMPATA;
    }
}

public class Jokenpo {
    public static void main(String[] args) {
        new Jogo().jogar();
    }
}
