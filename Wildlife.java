enum Wildlife {
    FOX("\033[38;5;11m"),
    ELK("\033[0;30m"),
    BEAR("\033[38;5;8m"),
    HAWK("\033[38;5;26m"),
    SALMON("\033[38;5;1m");

    private String animalColour;

    Wildlife(String animalColour) {
        this.animalColour = animalColour;
    }

    public String toString() {
        return this.animalColour;
    }
}