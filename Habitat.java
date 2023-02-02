public enum Habitat {
    WOODLAND(ViewColours.D_GREEN_BG),
    RIVER(ViewColours.BLUE_BG),
    PRAIRIE(ViewColours.YELLOW_BG),
    MOUNTAINS(ViewColours.GREY_BG),
    WETLAND(ViewColours.GREEN_BG);

    private String placeColour;

    Habitat(String placeColour) {
        this.placeColour = placeColour;
    }

    public String toString() {
        return this.placeColour;
    }

}