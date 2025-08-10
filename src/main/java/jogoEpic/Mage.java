package jogoEpic;

// Classe do mago, herda da classe base Hero
public class Mage extends Hero {

    // Construtor usado pela fábrica
    public Mage(String name, int stars, int attack, int defense, int hp) {
        super(name, "Mage", stars, attack, defense, hp);
    }

    // Dano do mago com crítico 25% x1.5
    @Override
    public int attackDamage() {
        boolean crit = Math.random() < 0.25;
        int base = getAttack();
        return crit ? (int)(base * 1.5) : base;
    }
}
