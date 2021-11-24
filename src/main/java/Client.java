import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private static int port;

    public static int showMenu() {

        System.out.println("Menu Socket\n--------------------------");

        System.out.println("1 - Fonction longueur qui retourne la longueur totale de la phrase entrée, espaces compris");
        System.out.println("2 - Fonction tri qui permet de trier la phrase par ordre lexicographique");
        System.out.println("3 - Quitter\n");

        Scanner clavier = new Scanner(System.in);

        System.out.print("Votre choix : ");
        int choix = clavier.nextInt();

        switch (choix) {
            case 1:
                System.out.println("\nVous avez choisi la fonction longueur.");
                break;
            case 2:
                System.out.println("\nVous avez choisi la fonction tri");
                break;
            case 3:
                System.out.println("\nA bientot !");
                break;
            default:
                System.out.println("\nChoix inconnu, à bientot !");
                choix = 3;
                break;
        }

        return choix;

    }

    public static void main (String [] args) throws Exception {

        String adresse, line, response;
        Reader readSoc;
        PrintStream sortie = null;

        if (args.length != 2) {

            System.out.println("usage : java client nom_serveur port");
            System.exit(0);

        }

        adresse = args[0];
        port = Integer.parseInt(args[1]);
        Socket socket = new Socket(adresse, port);
        Reader reader = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(reader);
        sortie = new PrintStream(socket.getOutputStream());
        readSoc = new InputStreamReader(socket.getInputStream());
        BufferedReader keyboardSoc = new BufferedReader (readSoc);

        while (true) {

            int choix = showMenu();

            sortie.println(choix);
            if (choix == 3) break;

            System.out.print("Entrez une ligne de texte : ");
            line = keyboard.readLine();
            sortie.println(line);

            response = keyboardSoc.readLine();
            System.out.println("Réponse : " + response + "\n\n");

        }

        // fermeture de la socket
        socket.close();

    }
}
