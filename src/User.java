
/*
COMP20050 Cascadia Project
Group: Group 13 (TaskGroup13)
Members:    Luke King (21327413) -      LukeK2202
            Alexey Budnyev (21339913) - Alexey-B0
            David Kenny (21727729) -    DavidKenny3
 */

public class User {

    private int score;
    //future code to store the score of the individual user after the game finishes
    private String name;
    //name of the user entered from the console
    private boolean optionalCullCarriedOut = false;
    //boolean to tell the main class if the user has carried out an otional cull or not, on their current turn
    private Board board;

    private int natureTokens = 0;

    User(String name) {
        this.name = name;
        this.board = new Board();
    }

    public void setNatureTokens(int n) {
        natureTokens = n;
    }

    public int getNatureTokens() {
        return natureTokens;
    }

    public void addNatureToken() {
        natureTokens++;
    }

    public void removeNatureToken() {
        natureTokens--;
    }

    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public void addScore(int n) {
        score += n;
    }

    public boolean isUser() {
        return this.getClass() == User.class;
    }

    public void setScore(int n) {
        score = n;
    }
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
    /* {


    code for the personal board of the user to be able to generate a starter tile and board
    on which to lay additional tiles and place wildlife token etc.

        }*/




}
