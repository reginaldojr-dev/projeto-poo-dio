package jogoEpic;

//herda da classe base Hero
public class Archer extends Hero {

    // atributos base do arqueiro
    public Archer(String name) {
        super(name, "Archer", 23, 5, 100);
    }

    //Ideia: dano consistente com uma variação leve (0 a 3) como att unico
    @Override
    public int attackDamage() {
        int variacao = (int)(Math.random() * 4); // 0, 1, 2 ou 3
        return getAttack() + variacao; // retorna o atk + bonus (ou n no caso de 0)
    }
}
