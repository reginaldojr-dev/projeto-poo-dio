package jogoEpic;

// Classe do arqueiro, herda da classe base Hero
public class Archer extends Hero {

    // Construtor usado pela fábrica
    public Archer(String name, int stars, int attack, int defense, int hp) {
        super(name, "Archer", stars, attack, defense, hp);
    }

    // Dano do arqueiro com variação leve (0..3)
    @Override
    public int attackDamage() {
        int variacao = (int)(Math.random() * 4); // 0,1,2,3
        return getAttack() + variacao;
    }
}
