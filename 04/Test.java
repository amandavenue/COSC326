package iota;

public class Test {

    public static void main(String[] args) {

        Manager m = new Manager();
        Player p1 = new Dave(m);
        Player p2 = new Derrick(m);

        m.addPlayers(p1, p2);
        m.setup();
        m.play();
        IotaDisplay d = new IotaDisplay();
    }

}
