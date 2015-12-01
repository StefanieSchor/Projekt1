public class Main {
    public static void main(String[] args) {
        MySQL sqlhenter = new MySQL();
        sqlhenter.getMovies();
        sqlhenter.sendMovies();
        sqlhenter.getShows();
        sqlhenter.sendShows();
        Movie.printMovies();
    }
}