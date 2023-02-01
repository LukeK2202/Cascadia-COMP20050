public enum Habitat {
    WOODLAND("\033[48;5;22m"),
    RIVER("\033[48;5;6m"),
    PRAIRIE("\033[48;5;26m"),
    MOUNTAINS("\033[48;5;11m"),
    WETLAND("\033[48;5;8m");

    private String placeColour;

    Habitat(String placeColour) {
        this.placeColour = placeColour;
    }

    public String toString() {
        return this.placeColour;
    }

}