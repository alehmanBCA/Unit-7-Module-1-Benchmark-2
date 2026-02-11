import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class DogBoarder {

    ArrayList<Dictionary<String, String>> dogs = new ArrayList<>();



    int total_slots = 10;

    double daily_rate = 3.50;

    int slots_occupied = 0;

    public void main(String[] args) {

        // Testing the 'board' function.
        board("John", "Golden Retriever", "Bob");
        System.out.println("Slots: " + slots_occupied());

        board("Another", "Labrador", "Max");
        System.out.println("Slots: " + slots_occupied());
    }

    public ArrayList<Dictionary<String, String>> all_dogs() {return dogs;}

    public boolean is_full() {return slots_occupied >= total_slots;}

    public int slots_occupied() {
        return slots_occupied;
    }

    public String board(String dog, String breed, String name) {
        Dictionary<String, String> boarded_dogs = new Hashtable<>();
        if (!is_full()) {
            slots_occupied++;
            boarded_dogs.put("dog", dog);
            boarded_dogs.put("breed", breed);
            boarded_dogs.put("name", name);

            dogs.add(boarded_dogs);
            System.out.println(dogs);
            return dog + " has been boarded";
        }
        return "Error: No available slots.";
    }

    public String pick_up(String dog, String breed, String name, int days) {
        double price = days * daily_rate;
        for (int i = 0; i < dogs.size(); i++) {
            if (dogs.get(i).get("dog") == dog) {
                dogs.remove(i);
                slots_occupied--;
                return "You owe $" + price + ".";
            }
        }
        return "Error: Dog not found.";
    }

}
