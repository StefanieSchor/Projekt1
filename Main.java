public class Main {
    public static void main(String[] args) {
        MySQL sqlhenter = new MySQL();
        sqlhenter.getSales();
        sqlhenter.getMovies();
        sqlhenter.sendMovies();
        sqlhenter.getShows();
        sqlhenter.sendShows();
        Gui gui = new Gui();
        gui.makeGui();
    }
}