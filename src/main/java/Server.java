import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Server {

    private static int port;

    public static int longueur(String line) {
        return line.length();
    }

    public static String tri(String line) {

        char[] stringtoChar = line.toCharArray();
        Arrays.sort(stringtoChar);

        String SortedString = new String(stringtoChar);

        return new String(stringtoChar);

    }

    public static void main (String [] args) throws Exception {

        boolean boucle = true;
        Reader reader;
        PrintStream sortie = null;
        Socket soc;
        String line;

        if (args.length != 1) {
            System.out.println("usage : java serveur port");
            System.exit(0);
        }

        port = Integer.parseInt(args[0]);

        ServerSocket s = new ServerSocket (port);
        System.out.println("La socket serveur est cree");

        while (true) {

            boucle = true; soc = s.accept();
            System.out.println("Connexion realise a " + soc.toString());
            reader = new InputStreamReader(soc.getInputStream());
            sortie = new PrintStream(soc.getOutputStream());
            BufferedReader keyboard = new BufferedReader (reader);

            while (boucle) {

                int choix = Integer.parseInt(keyboard.readLine());
                System.out.println("Choix : " + choix);

                switch(choix) {

                    case 1:

                        line = keyboard.readLine();
                        System.out.println("Vous avez saisi : " + line);

                        sortie.println(longueur(line));

                        break;
                    case 2:

                        line = keyboard.readLine();
                        System.out.println("Vous avez saisi : " + line);

                        sortie.println(tri(line));

                        int a;

                        break;
                    case 3:
                    default:
                        boucle = false;
                        soc.close();
                        break;

                }

            }

        }

    }

}