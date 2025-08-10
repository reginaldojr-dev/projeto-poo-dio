package jogoEpic;

import java.util.concurrent.ThreadLocalRandom;

// Heróis com nomes de Epic Seven <3
public class HeroFactory {

    // Nomes por classe (os que você passou)
    private static final String[] WARRIOR_NAMES = {
            "Choux", "Cermia", "Ravi", "Sigret", "Zahhak", "Ken"
    };
    private static final String[] MAGE_NAMES = {
            "Araminta", "Aria", "Eda", "Bassar", "Ludwig", "Vivian"
    };
    private static final String[] ARCHER_NAMES = {
            "Robin", "Cerise", "Flan", "Lua", "Iseria", "Nakwol"
    };

    // Estrutura simples pra guardar stats
    private static class Stats {
        final int atk, def, hp;
        Stats(int atk, int def, int hp) { this.atk = atk; this.def = def; this.hp = hp; }
    }

    // TABELAS: [estrelas] -> stats por classe
    // Índice: 3,4,5
    private static final Stats[] WARRIOR_STATS = new Stats[6];
    private static final Stats[] MAGE_STATS    = new Stats[6];
    private static final Stats[] ARCHER_STATS  = new Stats[6];
    static {
        // Warrior: tank / dano estável
        WARRIOR_STATS[3] = new Stats(16, 6, 110);
        WARRIOR_STATS[4] = new Stats(18, 7, 120);
        WARRIOR_STATS[5] = new Stats(21,9, 135);

        // Mage: alto dano / pouca defesa/vida
        MAGE_STATS[3] = new Stats(20, 3, 85);
        MAGE_STATS[4] = new Stats(24, 4, 95);
        MAGE_STATS[5] = new Stats(27, 5, 105);

        // Archer: equilibrado / leve variação de dano
        ARCHER_STATS[3] = new Stats(18, 4, 92);
        ARCHER_STATS[4] = new Stats(21, 5, 104);
        ARCHER_STATS[5] = new Stats(24, 6, 115);
    }

    private static final ThreadLocalRandom RND = ThreadLocalRandom.current();

    private static String pick(String[] pool) {
        return pool[RND.nextInt(pool.length)];
    }

    private static int pickStars() {
        // sorteio simples entre 3 e 5
        return RND.nextInt(3, 6); // 3,4,5
    }

    // Cria um herói aleatório SEM informar classe (sorteia tudo)
    public static Hero createRandomHero() {
        int r = RND.nextInt(3);
        return switch (r) {
            case 0 -> createRandomWarrior();
            case 1 -> createRandomMage();
            default -> createRandomArcher();
        };
    }

    // Cria um herói aleatório informando classe por string
    public static Hero createRandomHero(String clazz) {
        switch (clazz.toLowerCase()) {
            case "warrior": return createRandomWarrior();
            case "mage":    return createRandomMage();
            case "archer":  return createRandomArcher();
            default: throw new IllegalArgumentException("Classe desconhecida: " + clazz);
        }
    }

    // Abaixo eu separo por classe (fica mais legível)
    public static Warrior createRandomWarrior() {
        int stars = pickStars();
        Stats s = WARRIOR_STATS[stars];
        return new Warrior(pick(WARRIOR_NAMES), stars, s.atk, s.def, s.hp);
    }

    public static Mage createRandomMage() {
        int stars = pickStars();
        Stats s = MAGE_STATS[stars];
        return new Mage(pick(MAGE_NAMES), stars, s.atk, s.def, s.hp);
    }

    public static Archer createRandomArcher() {
        int stars = pickStars();
        Stats s = ARCHER_STATS[stars];
        return new Archer(pick(ARCHER_NAMES), stars, s.atk, s.def, s.hp);
    }
}
