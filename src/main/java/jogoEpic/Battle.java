package jogoEpic;

import java.util.concurrent.ThreadLocalRandom;

// Mini sistema de batalha 1x1 (agora usando o HP real do herói)
public class Battle {

    // Esse metodo roda a luta e no final retorna o herói vencedor
    public Hero fight(Hero a, Hero b) {

        // Garante que os dois entram na luta com a vida cheia
        a.reset();
        b.reset();

        // Sorteio quem começa só pra não ficar sempre igual
        boolean aComeca = ThreadLocalRandom.current().nextBoolean();
        int turn = 1; // só pra eu controlar quem ataca e mostrar no log

        System.out.println("\n=== BATALHA 1x1 ===");
        System.out.println(a + "  vs  " + b);
        System.out.println("Quem começa: " + (aComeca ? a.getName() : b.getName()));

        // Enquanto os dois estiverem vivos, a luta continua
        while (a.isAlive() && b.isAlive()) {
            boolean aAtaca = aComeca ? (turn % 2 == 1) : (turn % 2 == 0);

            if (aAtaca) {
                int bruto = a.attackDamage();          // dano bruto do atacante
                int efetivo = b.takeDamage(bruto);     // o defensor aplica a própria defesa e perde vida
                System.out.printf("Turno %d: %s causa %d em %s (HP %s: %d)%n",
                        turn, a.getName(), efetivo, b.getName(), b.getName(), b.getCurrentHp());
            } else {
                int bruto = b.attackDamage();
                int efetivo = a.takeDamage(bruto);
                System.out.printf("Turno %d: %s causa %d em %s (HP %s: %d)%n",
                        turn, b.getName(), efetivo, a.getName(), a.getName(), a.getCurrentHp());
            }

            turn++; // próximo turno
        }

        // Quem ainda estiver vivo é o vencedor
        Hero winner = a.isAlive() ? a : b;
        System.out.println("Vencedor: " + winner.getName());
        return winner;
    }
}
