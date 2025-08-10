package jogoEpic;

// Classe do guerreiro usando herança de hero
public class Warrior extends Hero {

    // aqui ja vai valores fixos de atributos da classe
    public Warrior(String name) {
        super(name, "Warrior", 15, 7, 120);
    }


    // Warrior vai ter bônus fixo de +3 no ataque (independente de item)
    @Override
    public int attackDamage() {
        return getAttack() + 3;
    }
}