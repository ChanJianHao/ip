import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";

    private static final String NEKOBOT_LOGO =
            "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n"
                    + "░░░░░░░░░░▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄░░░░░░░░░\n"
                    + "░░░░░░░░▄▀░░░░░░░░░░░░▄░░░░░░░▀▄░░░░░░░\n"
                    + "░░░░░░░░█░░▄░░░░▄░░░░░░░░░░░░░░█░░░░░░░\n"
                    + "░░░░░░░░█░░░░░░░░░░░░▄█▄▄░░▄░░░█░▄▄▄░░░\n"
                    + "░▄▄▄▄▄░░█░░░░░░▀░░░░▀█░░▀▄░░░░░█▀▀░██░░\n"
                    + "░██▄▀██▄█░░░▄░░░░░░░██░░░░▀▀▀▀▀░░░░██░░\n"
                    + "░░▀██▄▀██░░░░░░░░▀░██▀░░░░░░░░░░░░░▀██░\n"
                    + "░░░░▀████░▀░░░░▄░░░██░░░▄█░░░░▄░▄█░░██░\n"
                    + "░░░░░░░▀█░░░░▄░░░░░██░░░░▄░░░▄░░▄░░░██░\n"
                    + "░░░░░░░▄█▄░░░░░░░░░░░▀▄░░▀▀▀▀▀▀▀▀░░▄▀░░\n"
                    + "░░░░░░█▀▀█████████▀▀▀▀████████████▀░░░░\n"
                    + "░░░░░░████▀░░███▀░░░░░░▀███░░▀██▀░░░░░░\n"
                    + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n";



    public static void main(String[] args) {
        // Print logo
        System.out.println(NEKOBOT_LOGO);

        // DUKE Greet
        System.out.println(HORIZONTAL_LINE);
        System.out.println(" Hello! I'm Neko-bot *meow*");
        System.out.println(" What can I do for you?");
        System.out.println(HORIZONTAL_LINE);


        Scanner userInput = new Scanner(System.in);
        while (true) {
            String input = userInput.nextLine();

            if (input.equals("bye")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);

                return;
            } else {
                System.out.println(HORIZONTAL_LINE);
                System.out.println(input);
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}
