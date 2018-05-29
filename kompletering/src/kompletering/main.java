/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kompletering;

/**
 *
 * @author Antti
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class main {

    public static void main(String[] args) {

        boolean fortsatt = true;
        String indata;

        //List for the golfers
        ArrayList<Golfer> deltList = new ArrayList<Golfer>();
        List<String> parList = null;

        //A string array which contains the Joptionpane buttons for the GUI
        String[] screenOptions = new String[]{"Anmäl", "Ange par", "Börja runda", "Skriv ut resultat", "Sluta"};

        while (fortsatt) {

            //A JOptionspane component which simulates the GUI of the program
            int svar = JOptionPane.showOptionDialog(null,
                    "Välkommen till Golfsimulatorn",
                    "Golfsimulator 4000",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    screenOptions,
                    screenOptions[0]);

            if (svar == 0) {

                try {
                    System.out.println("du tryckte \"anmäl\"");

                    indata = JOptionPane.showInputDialog("Ange deltagarens namn samt förening separerat med ett komma"
                            + " t.ex. <Lisa, date>\n" + "OBS! Du måste ange alla deltagare för en tävling innan du börjar ange poäng "
                            + "för deltagare");
                    if (indata == null) {
                        JOptionPane.showMessageDialog(null, "Du tryckte på cancel.\n"
                                + "Har du angett alla deltagare?\n");
                        continue;
                    }
                    if (indata.equals("")) {
                        JOptionPane.showMessageDialog(null, "Du gav en tom sträng försökt på nytt");
                        continue;
                    } else if (indata.equals(","));
                    deltList.add(new Golfer(indata));

                    for (Golfer x : deltList) {
                        System.out.println("Skriver ut listan " + x.getInfo());

                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(null, "Du angav inte all data som behövdes för deltagaren");

                }

            }

            if (svar == 1) {
                System.out.println("du tryckte på \"Ange par\"");
                String[] par;
                indata = JOptionPane.showInputDialog("Mata in par som ska användas för golfrundan \n"
                        + "T.ex. 4,3,5,6,3,2,4,5,5,3,2,6,5,4,3,2,2,5");

                if (indata.equals("")) {
                    JOptionPane.showMessageDialog(null, "Du gav en tom sträng försökt på nytt");
                    continue;
                } else if (indata.length() == 6) {
                    par = indata.split(",");
                    parList = Arrays.asList(par);
                    System.out.println(parList);

                }
            }
            /**
             * This large if-statement processes all the point and hit
             * operations in the program: It counts the points for each player
             * during every round It also keep a count of the number of hits a
             * player performs
             */
            if (svar == 2) {
                System.out.println("Du tryckte på \"Börja runda\"");

                String input = "";
                int hits;
                int parseFirst;
                int parseLast;
                int pointsParse;

                if (deltList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Du måste ange deltagare först");
                    continue;
                }
                for (Golfer g : deltList) { //resets all the players points
                    g.resetPoints();
                }
                //if (parList.size() == 35) {
                //  JOptionPane.showMessageDialog(null, "Du måste ange par för rundan");
                // continue;
                if (!deltList.isEmpty() && !parList.isEmpty()) {
                    try {
                        int parListParser;

                        for (int i = 0; i <= 2; i++) { // 3 holes (18 holes)

                            parListParser = Integer.parseInt(parList.get(i));
                            System.out.println("parListParser " + parListParser);

                            for (Golfer g : deltList) {

                                if (g.getState() == false) {
                                    g.setDNF(input);
                                    System.out.println("Spelare " + g.getNamn() + " poäng blir " + g.getPoints());
                                    continue;
                                }

                                input = JOptionPane.showInputDialog("Ange poäng för " + g.getNamn() + " för hål " + (i + 1) + "\n"
                                        + " ifall du vill ange att en deltagare avslutar mitt i tävlingen, skriv dnf för den rundan \n "
                                        + "t.ex. för rundan 5 ange poäng: <dnf>\n"
                                        + "Om du vill ge straffslag åt en spelare skriv 'poäng' 'X' 'Straffslag', t.ex"
                                        + " 5X1");

                                parseFirst = Integer.parseInt(Character.toString(input.charAt(0)));

                                if (input.equals("dnf")) {
                                    g.setState(false);
                                } else if (parListParser == parseFirst && !input.contains("X")) {
                                    g.setPoints(0);
                                    System.out.println("Slag för användare " + g.getNamn() + " är " + g.getPoints());

                                    hits = Integer.parseInt(input);
                                    g.addSlag(hits);
                                    System.out.println("Slag för användare " + g.getNamn() + " är " + g.getSlag());

                                } else if (parseFirst < parListParser && !input.contains("X")) { //Alltid slag - Par
                                    int pointLessThan = parseFirst - parListParser;
                                    g.setPoints(pointLessThan);
                                    System.out.println("Slag för användare " + g.getNamn() + " är " + g.getPoints());

                                    hits = Integer.parseInt(input);
                                    g.addSlag(hits);
                                    System.out.println("slag " + g.getNamn() + " är " + g.getSlag());

                                } else if (parseFirst > parListParser && !input.contains("X")) {
                                    int pointMoreThan = parseFirst - parListParser;
                                    g.setPoints(pointMoreThan);
                                    System.out.println("slag för användare " + g.getNamn() + " är " + g.getPoints());

                                    hits = Integer.parseInt(input);
                                    g.addSlag(hits);
                                    System.out.println("slag för användare " + g.getNamn() + " är " + g.getSlag());
                                }

                                if (input.contains("X") && parseFirst > 0) {
                                        parseLast = Integer.parseInt(Character.toString(input.charAt(2)));
                                        pointsParse = parseFirst + parseLast;

                                        if (parseLast > parseFirst) {
                                            JOptionPane.showMessageDialog(null, "Du har gett ett felaktigt input.\n"
                                                    + "Du kan inte ge ett out of bounds antal som är högre än antalet slag ");
                                            continue;
                                        }

                                        if ((parseFirst > 0) && (parseLast >= 0)) {

                                            if (parseFirst > 0 && parseLast >= 0) {

                                                if (parListParser == pointsParse) {
                                                    g.setPoints(0);
                                                    System.out.println("poäng för användare " + g.getNamn() + " är " + g.getPoints());

                                                    g.addSlag(pointsParse);
                                                    System.out.println("Slag för användare " + g.getNamn() + " är " + g.getSlag());
                                                } else if (pointsParse < parListParser) { //Alltid slag - Par
                                                    int pointLessThan = pointsParse - parListParser;
                                                    g.setPoints(pointLessThan);
                                                    System.out.println("poäng för användare " + g.getNamn() + " är " + g.getPoints());

                                                    g.addSlag(pointsParse);
                                                    System.out.println("slag " + g.getNamn() + " är " + g.getSlag());
                                                } else if (pointsParse > parListParser) {
                                                    int pointMoreThan = pointsParse - parListParser;
                                                    g.setPoints(pointMoreThan);
                                                    System.out.println("poäng för användare " + g.getNamn() + " är " + g.getPoints());

                                                    g.addSlag(pointsParse);
                                                    System.out.println("slag för användare " + g.getNamn() + " är " + g.getSlag());
                                                }
                                            }
                                        }
                                    }
                                }
                            

                            JOptionPane.showMessageDialog(null, showResult(deltList));
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Nullpointer");
                    }
                }

            } else if (svar == 4) {
                System.out.println("du tryckte \"sluta\"");
                break;
            }

            if (svar == -1) {
                System.out.println("du tryckte krysset uppe till höger");
                break;
            }

        }

    }

    public static ArrayList<String> showResult(ArrayList<Golfer> deltList) {

        String result = "";
        ArrayList<String> printList = new ArrayList<>();
        for (Golfer g : deltList) {

            result = "Deltagare " + g.getNamn() + " poäng är " + g.getPoints()
                    + " och har slagit " + g.getSlag() + " slag \n";
            printList.add(result);
        }

        return printList;
    }

}
