import java.io.*;
import java.util.ArrayList;

public class FileCRUD {

    public static ArrayList<Card> getCards(){
        ArrayList <Card> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("resources/test.txt"));
            String line;
            while((line = br.readLine()) != null){
                String mas[] = line.split(" ");
                try {
                    Card card = new Card(Long.parseLong(mas[0]), mas[1], mas[2], Integer.parseInt(mas[3]));
                    list.add(card);
                }catch (NumberFormatException e){
                    System.out.println(e.getMessage());
                    System.out.println("Error parse number");
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("IO Exception");
        }
        return list;
    }

    public static void updateFile(ArrayList<Card> list){
        File oldF = new File("resources/test.txt");
        if(oldF.exists()) oldF.delete();

        File newF = new File("resources/test.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(newF, true));
        } catch (IOException e) {
            System.out.println("Error create BufferedWriter. "+e.getMessage());
        }
        String lineSeparator = System.getProperty("line.separator");
        for(Card card : list){
            try {
                writer.write(card.getId()+" "+card.getNumberCard()+" "+card.getPassword()+" "+card.getMoney() + lineSeparator );
            } catch (IOException e) {
                System.out.println("Error write file. "+e.getMessage());
            }
        }
        try {
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error close writer. "+e.getMessage());
        }

    }

}
