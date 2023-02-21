public class User {

    private int score;
    //future code to store the score of the individual user after the game finishes
    private String name;
    //name of the user entered from the console
    private int orderNumber;
    //integer number to store the order? Possible reworks may occur

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
