public class User {

    private int score;
    //future code to store the score of the individual user after the game finishes
    private String name;
    //name of the user entered from the console
    private int orderNumber;
    //integer number to store the order? Possible reworks may occur
    private boolean optionalCullCarriedOut = false;
    //boolean to tell the main class if the user has carried out an otional cull or not

    //gets the boolean value, if false carries out cull, if true, tells user that cull was done then sets boolean back to false
    public boolean getOptionalCullCarriedOut() {
        return optionalCullCarriedOut;
    }
    //sets optionalCullCarriedOut to true when an optional cull was carried out
    public void setOptionalCullDoneNow() {
        optionalCullCarriedOut = true;
    }
    //sets optionalCullCarriedOut to false when an optional cull was carried out the previous round, this resets the value so the user
    //can carry it out again the next time their turn comes around
    public void setOptionalCullPreviouslyDone() {
        optionalCullCarriedOut = false;
    }

    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
    public int getOrderNumber() {
        return orderNumber;
    }
    private Board board;

    User(String name) {
        this.name = name;
        this.board = new Board();
    }

    public String toString() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    /* {


    code for the personal board of the user to be able to generate a starter tile and board
    on which to lay additional tiles and place wildlife token etc.

        }*/




}
