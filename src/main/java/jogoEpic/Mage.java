package jogoEpic;

// Classe do mago herdando a base de  Hero
public class Mage extends Hero {


    // atributos base do mago
    public Mage(String name) {
        super(name, "Mage", 25, 4, 90);
    }


    // mago vai ter uma chance de critico (25%) como atributo unico (dano multiplica por 1.5)
    @Override
    public int attackDamage() {
        boolean crit = Math.random() < 0.25;      // se cair nesse 25%, é crítico
        int base = getAttack();                    // pego o ataque base do mago
        return crit ? (int)(base * 1.5) : base;    // se critar, multiplica; senão, dano normal
    }
}