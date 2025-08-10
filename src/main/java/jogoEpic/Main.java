package jogoEpic;

public class Main {
    public static void main(String[] args) {
        Hero h1 = HeroFactory.createRandomHero();
        Hero h2 = HeroFactory.createRandomHero();

        // evita que as classes sejam iguais
        while (h1.getClazz().equals(h2.getClazz())) {
            h2 = HeroFactory.createRandomHero();
        }

        new Battle().fight(h1, h2);
    }
}
