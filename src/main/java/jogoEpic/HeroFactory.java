package jogoEpic;

import java.util.concurrent.ThreadLocalRandom;

// Herois com nomes baseados no Epic Seven <3
public class HeroFactory {

    // Lista de nomes por classe
    private static final String[] WARRIOR_NAMES = {
            "Choux", "Cermia", "Ravi", "Sigret", "Zahhak", "Ken"
    };
    private static final String[] MAGE_NAMES = {
            "Araminta", "Aria", "Eda", "Bassar", "Ludwig", "Vivian"
    };
    private static final String[] ARCHER_NAMES = {
            "Robin", "Cerise", "Flan", "Lua", "Iseria", "Nakwol"
    };

    // Sorteio de nome dentro da lista
    private static String pick(String[] pool) {
        int i = ThreadLocalRandom.current().nextInt(pool.length);
        return pool[i];
    }

    // Criar herói de classe específica
    public static Hero createRandomHero(String clazz) {
        switch (clazz.toLowerCase()) {
            case "warrior":
                return new Warrior(pick(WARRIOR_NAMES));
            case "mage":
                return new Mage(pick(MAGE_NAMES));
            case "archer":
                return new Archer(pick(ARCHER_NAMES));
            default:
                throw new IllegalArgumentException("Classe desconhecida: " + clazz);
        }
    }

    // Criar herói de classe aleatória
    public static Hero createRandomHero() {
        int r = ThreadLocalRandom.current().nextInt(3); // 0..2
        if (r == 0) return new Warrior(pick(WARRIOR_NAMES));
        if (r == 1) return new Mage(pick(MAGE_NAMES));
        return new Archer(pick(ARCHER_NAMES));
    }

    // Versões diretas
    public static Warrior createWarrior() { return new Warrior(pick(WARRIOR_NAMES)); }
    public static Mage createMage()       { return new Mage(pick(MAGE_NAMES)); }
    public static Archer createArcher()   { return new Archer(pick(ARCHER_NAMES)); }
}
