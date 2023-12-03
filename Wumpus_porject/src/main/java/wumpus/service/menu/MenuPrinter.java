package wumpus.service.menu;

public final class MenuPrinter {

    private MenuPrinter() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static void printDefault(String userName) {
        System.out.println("Kérem a menüből válasszon!");
        printHeader(userName);
    }

    public static void printLeaderboardHeader() {
        System.out.println("RANK, USERNAME, WINS");
    }

    public static void printLoadGame() {
        System.out.println("Add meg a jatek szamat: ");
        System.out.println("Uj jatek [X]");
    }

    public static void printSavedGames() {
        System.out.println("Elmentett jatekaid: ");
    }
    public static void printHeader(String userName) {
        System.out.println("Sikeres bejelentkezés " + userName + " felhasználóval! Kérem válasszon:");
        System.out.println("[1] Mentett betöltése adatbaziosbol");
        System.out.println("[2] Mentett betöltése fájlból");
        System.out.println("[3] Uj játék indítása");
        System.out.println("[4] Kilépés");
        System.out.println("[5] Toplista");
    }

    public static void printUsernameRequest() {
        System.out.print("Kérem adja meg a felhasználónevét: ");
    }

    public static void printGiveUp() {
        System.out.print("Jatek vege!");
    }

    public static void printDirectionRequest() {
        System.out.println("Forgast valaszottal add meg az iranyt (L/R): ");
    }

    public static void printOutOfArrow() {
        System.out.println("Nincs nyilvesszod!");
    }

    public static void printInvalid() {
        System.out.println("A hos nem tud lepni!");
    }

    public static void printGameOver() {
        System.out.println("A hos meghalt! Game over!");
    }

    public static void printVictory(Integer numberOfSteps) {
        System.out.println("Nyertel! Sikeresen teljseiteted a jatekot " + numberOfSteps+ " lepesbol!");
    }

    public static void printOptions() {
        System.out.println("Kérem válasszon akciot: ");
        System.out.println("[1] Step");
        System.out.println("[2] Rotate");
        System.out.println("[3] Collect");
        System.out.println("[4] Shoot");
        System.out.println("[5] Save and Exit");
        System.out.println("[6] Surrender");
    }

}
