package jogoEpic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// Time é só uma lista de 3 heróis e um nome
public class Team {
    private final String name;
    private final List<Hero> members;

    public Team(String name, List<Hero> members) {
        this.name = name;
        this.members = members;
    }

    public String getName() { return name; }
    public List<Hero> getMembers() { return members; }

    public List<Hero> alive() {
        List<Hero> vivos = new ArrayList<>();
        for (Hero h : members) if (h.isAlive()) vivos.add(h);
        return vivos;
    }

    public void resetAll() {
        for (Hero h : members) h.reset();
    }

    // Time aleatório (3 heróis misturados)
    public static Team randomTeam(String name) {
        List<Hero> list = new ArrayList<>();
        list.add(HeroFactory.createRandomHero());
        list.add(HeroFactory.createRandomHero());
        list.add(HeroFactory.createRandomHero());
        return new Team(name, list);
    }

    @Override
    public String toString() {
        return name + " " + members;
    }

    // Só pra escolher um alvo vivo aleatório
    public static Hero pickRandomAlive(List<Hero> vivos) {
        return vivos.get(ThreadLocalRandom.current().nextInt(vivos.size()));
    }
}
