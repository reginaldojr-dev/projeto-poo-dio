package jogoEpic;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== EPIC MINI 3x3 ===");

        Team t1 = Team.randomTeam("Time Azul");
        Team t2 = Team.randomTeam("Time Vermelho");

        TeamBattle tb = new TeamBattle();
        Team vencedor = tb.fight(t1, t2);          // <<< usa o retorno
        System.out.println("\n>>> Vencedor: " + vencedor.getName());
    }
}
