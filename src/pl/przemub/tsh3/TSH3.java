package pl.przemub.tsh3;

import java.io.IOException;

public class TSH3 {
    private static HackingInterface miedzymordzie;

    public static void main(String[] args) {
        miedzymordzie = new Login();
        try {
           do {
               miedzymordzie = miedzymordzie.run();
           } while (miedzymordzie != null);
        } catch (IOException e) {
            System.err.println("Coś się wybitnie spierdoliło.");
            e.printStackTrace();
        }

        System.exit(0);
    }
}
