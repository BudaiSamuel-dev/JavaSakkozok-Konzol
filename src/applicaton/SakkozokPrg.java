package applicaton;

import classes.Sakkozo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class SakkozokPrg {

    private static final File FILE = new File("C:\\Dev\\FeladatForrasok\\BudaiS\\sakkozok-2024-05.txt");
    private static final File FILE_OUT = new File("C:\\Dev\\FeladatForrasok\\BudaiS\\sakkozok-2800+.txt");
    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);

        ArrayList<Sakkozo> sakkozok = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(FILE);

            while (fileScanner.hasNextLine()){
                String record = fileScanner.nextLine().trim();

                if (!record.isEmpty() && !record.startsWith("#") &&!record.startsWith("\t")){
                    String[] data = record.split(";");

                    int maxEloPont = Integer.parseInt(data[2].trim());

                    Sakkozo sakkozo = new Sakkozo(data[0].trim(), data[1].trim(), maxEloPont, data[3].trim());

                    if (data.length > 4){
                        sakkozo.setMegjegyzes(data[4]);
                    }

                    sakkozok.add(sakkozo);
                }
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("A fájl nem található!");
            System.exit(0);
        }

        System.out.printf("%d sakkozó adata került beolvasásra.\n", sakkozok.size());

        int magyarSakkozo = 0;
        int franciaSakkozo = 0;

        int osszPont = 0;
        
        for (Sakkozo sakkozo : sakkozok){
            if (sakkozo.getNemzetiseg().contains("Magyarország")){
                magyarSakkozo++;
            } else if (sakkozo.getNemzetiseg().contains("Franciaország")) {
                franciaSakkozo++;
            }
            osszPont += sakkozo.getMaxEloPont();

        }

        System.out.printf("%d magyar és %d francia sakkozó szerepel a listán.\n", magyarSakkozo, franciaSakkozo );

        double atlag = (double)osszPont / sakkozok.size();
        System.out.printf("A pontszámok átlaga: %.2f\n",atlag);

        System.out.println("2011-ben vagy júliusban érték el max. pontszámukat:");
        for (Sakkozo sakkozo : sakkozok){
            int ev = Integer.parseInt(sakkozo.getMaxEloPontDatum().split("-")[0]);
            int honap = Integer.parseInt(sakkozo.getMaxEloPontDatum().split("-")[1]);

            if (ev == 2011 || honap == 7){
                System.out.println(sakkozo.getAdat());
            }
        }

        try {
            FileWriter fileWriter = new FileWriter(FILE_OUT);

            for (Sakkozo sakkozo : sakkozok){
                if (sakkozo.getMaxEloPont() >= 2800){
                    fileWriter.write(String.format("%d - %s\n", sakkozo.getMaxEloPont(), sakkozo.getNev()));
                }
            }

            fileWriter.flush();
            fileWriter.close();
            System.out.println("A fájl kiírása sikeres volt!");
        } catch (IOException e) {
            System.out.println("A fájl kiírása sikertelen volt!");
        }
    }
}
