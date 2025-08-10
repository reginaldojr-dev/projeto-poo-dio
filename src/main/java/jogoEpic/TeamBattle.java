package jogoEpic;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

// Batalha 3x3: times alternam turnos; cada turno 1 herói ataca 1 alvo aleatório do outro time
public class TeamBattle {

    public Team fight(Team A, Team B) {
        // todos de vida cheia
        A.resetAll();
        B.resetAll();

        // só pra variar quem começa
        boolean aComeca = ThreadLocalRandom.current().nextBoolean();
        int round = 1;

        System.out.println("\n=== BATALHA 3x3 ===");
        System.out.println(A);
        System.out.println(B);
        System.out.println("Quem começa: " + (aComeca ? A.getName() : B.getName()));

        // enquanto ambos tiverem alguém vivo, segue a briga
        while (!A.alive().isEmpty() && !B.alive().isEmpty()) {
            Team atkTeam  = aComeca ? A : B;
            Team defTeam  = aComeca ? B : A;

            // escolhe atacante vivo do time da vez (ciclo simples pelo índice do round)
            List<Hero> vivosAtk = atkTeam.alive();
            Hero attacker = vivosAtk.get((round - 1) % vivosAtk.size());

            // escolhe alvo vivo aleatório do time oposto
            Hero defender = Team.pickRandomAlive(defTeam.alive());

            int bruto = attacker.attackDamage();
            int efetivo = defender.takeDamage(bruto);

            System.out.printf("Round %d: [%s] %s → %s [%s] (dano %d, HP alvo %d)%n",
                    round,
                    atkTeam.getName(), attacker.getName(),
                    defender.getName(), defTeam.getName(),
                    efetivo, defender.getCurrentHp());

            // troca a vez do time
            aComeca = !aComeca;
            round++;
        }

        Team winner = A.alive().isEmpty() ? B : A;

        int vivosWinner = winner.alive().size();
        int vivosLoser  = (winner == A ? B : A).alive().size();

        System.out.printf("Time vencedor: %s (sobreviventes: %d x %d)%n",
                winner.getName(), vivosWinner, vivosLoser);

        return winner;
    }
}
