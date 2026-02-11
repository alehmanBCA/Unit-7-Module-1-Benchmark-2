public class DogBoarderTest {
    private static int testsRun = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        testInitialState();
        testBoardDog();
//        testBoardDogWhenFull();
//        testPickUpDogPart1();
//        testPickUpDogPart2();
//        testPickUpDogThatDoesNotExist();
//        testPickUpFromEmptyBoard();
//        testSlotsOccupied();

        if (testsFailed > 0) {
            throw new AssertionError(testsFailed + " tests failed out of " + testsRun);
        }
        System.out.println("All tests passed (" + testsRun + " total).");
    }

    private static void assertTrue(boolean condition, String message) {
        testsRun++;
        if (!condition) {
            testsFailed++;
            System.err.println("FAIL: " + message);
        }
    }

    private static void testInitialState() {
        DogBoarder boarder = new DogBoarder();
        assertTrue(boarder.total_slots == 10, "Total slots should start at 10.");
        assertTrue(boarder.daily_rate == 3.50, "Daily rate should start at 3.50");
        assertTrue(boarder.slots_occupied() == 0, "Initial slots occupied should be 0");
        assertTrue(!boarder.is_full(), "Boarder should not be full initially");
    }

    private static void testBoardDog() {
        DogBoarder boarder = new DogBoarder();
        String response = boarder.board("Buddy", "Golden Retriever", "Alice");
        assertTrue(response.equals("Buddy has been boarded"), "Should be able to board a dog");
        assertTrue(boarder.slots_occupied() == 1, "Slots occupied should be 1 after boarding");
    }

    private static void testBoardDogWhenFull() {
        DogBoarder boarder = new DogBoarder();
        for (int i = 0; i < boarder.total_slots; i++) {
            boarder.board("Dog" + i, "Breed" + i, "Owner" + i);
        }
        String response = boarder.board("Max", "Bulldog", "Bob"); // Attempt to board when full
        assertTrue(response.equals("Error: No available slots."), "Should not be able to board a dog when full");
        assertTrue(boarder.slots_occupied() == 10, "Should be 10 slots occupied still");
        assertTrue(boarder.is_full(), "Boarder should be full if all the slots are occupied");
    }

    private static void testPickUpDogPart1() {
        DogBoarder boarder = new DogBoarder();
        boarder.board("Rex", "German Shepherd", "Charlie");
        String response = boarder.pick_up("Rex", "German Shepherd", "Charlie", 3);
        assertTrue(response.equals("You owe $10.50."), "Cost should be calculated correctly for pick-up");
        assertTrue(boarder.slots_occupied() == 0, "Slots occupied should be 0 after pick-up");
    }

    private static void testPickUpDogPart2() {
        DogBoarder boarder = new DogBoarder();
        boarder.board("Red", "Red Heeler / Australian Shepherd", "RJay");
        boarder.board("Bailey", "Pomeranian", "Samantha");
        assertTrue(boarder.slots_occupied() == 2, "Slots occupied should be 2");
        String response = boarder.pick_up("Bailey", "Pomeranian", "Samantha", 4);
        assertTrue(response.equals("You owe $14.00."), "Cost should be calculated correctly for pick-up");
        assertTrue(boarder.slots_occupied() == 1, "Slots occupied should change after pick-up");
    }

    private static void testPickUpDogThatDoesNotExist() {
        DogBoarder boarder = new DogBoarder();
        boarder.board("Bella", "Beagle", "Diana");
        String response = boarder.pick_up("Lucky", "Beagle", "Diana", 2); // Non-existing dog
        assertTrue(response.equals("Error: Dog not found."), "Should return dog not found error");
    }

    private static void testPickUpFromEmptyBoard() {
        DogBoarder boarder = new DogBoarder();
        String response = boarder.pick_up("Buddy", "Golden Retriever", "Alice", 2); // No dogs to pick up
        assertTrue(response.equals("No dogs are currently boarded."), "Should inform that no dogs are boarded");
    }

    private static void testSlotsOccupied() {
        DogBoarder boarder = new DogBoarder();
        assertTrue(boarder.slots_occupied() == 0, "Should initially have 0 occupied slots");
        boarder.board("Milo", "Poodle", "Emma");
        assertTrue(boarder.slots_occupied() == 1, "Should have 1 occupied slot after boarding a dog");
    }
}
