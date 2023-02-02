public enum Wildlife {
    FOX(ViewColours.ORANGE + ViewColours.WHITE_BG + " F "+ ViewColours.RESET),
    ELK(ViewColours.BLACK + ViewColours.WHITE_BG + " E "+ ViewColours.RESET),
    BEAR(ViewColours.BROWN + ViewColours.WHITE_BG + " B "+ ViewColours.RESET),
    HAWK(ViewColours.BLUE + ViewColours.WHITE_BG + " H "+ ViewColours.RESET),
    SALMON(ViewColours.PINK + ViewColours.WHITE_BG + " S "+ ViewColours.RESET);

    private String animalColour;

    Wildlife(String animalColour) {
        this.animalColour = animalColour;
    }

    public String toString() {
        return this.animalColour;
    }
}