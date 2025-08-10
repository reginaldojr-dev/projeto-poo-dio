package jogoEpic;

// Classe do guerreiro, herda da classe base Hero
public class Warrior extends Hero {

    // Construtor usado pela fábrica (stars e stats definidos fora)
    public Warrior(String name, int stars, int attack, int defense, int hp) {
        super(name, "Warrior", stars, attack, defense, hp);
    }


    // Cálculo do dano do guerreiro (bônus fixo de +3)
    @Override
    public int attackDamage() {
        return getAttack() + 3;
    }
}
