package jogoEpic;

// Classe base para todos os herois
public abstract class Hero {

    // Atributos dos herois (clazz por motivos obvios está com zz)
    private final String name;   // nome do herói
    private final String clazz;  // tipo de herói (Warrior, Mage, Archer)
    private final int stars;     // quantas estrelas (3 a 5)
    private final int attack;    // poder de ataque
    private final int defense;   // defesa
    private final int hp;        // vida máxima (base)

    // Vida atual (essa que vai caindo na batalha)
    private int currentHp;       // começo a luta com vida cheia

    // Construtor para criar os herois
    // Protected para apenas as subclasses chamarem
    protected Hero(String name, String clazz, int stars, int attack, int defense, int hp) {
        this.name = name;
        this.clazz = clazz;
        this.stars = stars;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.currentHp = hp; // vida atual começa cheia
    }

    // Aqui vai servir para calcular o dano do ataque (cada classe faz do seu jeito)
    public abstract int attackDamage();

    // Método pra "tomar dano" com segurança (aplico defesa e tiro da vida atual)
    public int takeDamage(int rawDamage) {
        int efetivo = Math.max(1, rawDamage - defense); // nunca menos que 1
        currentHp = Math.max(0, currentHp - efetivo);   // não deixo passar de 0
        return efetivo; // devolvo quanto realmente entrou
    }

    // Diz se o herói ainda está de pé
    public boolean isAlive() {
        return currentHp > 0;
    }

    // Encho a vida de novo (uso antes de começar uma luta)
    public void reset() {
        currentHp = hp;
    }

    // Getters - só leitura
    public String getName()   { return name; }
    public String getClazz()  { return clazz; }
    public int getStars()     { return stars; }
    public int getAttack()    { return attack; }
    public int getDefense()   { return defense; }
    public int getHp()        { return hp; }
    public int getCurrentHp() { return currentHp; }

    // Só pra montar um visual bonitinho das estrelas *-* (ex.: ★★★★)
    private String starsStr() {
        return "★".repeat(stars);
    }

    // Aqui imprime o heroi (mostro classe, estrelas e vida atual)
    @Override
    public String toString() {
        return "%s (%s %s) ATK:%d DEF:%d HP:%d/%d"
                .formatted(name, clazz, starsStr(), attack, defense, currentHp, hp);
    }
}
