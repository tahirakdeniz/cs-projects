import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Class to keep films, halls and seats in a collection.
 */
public class CinemaDataManager {

    // TODO add special case exceptions like there exists film already. or like this. THINK ABOUT THAT

    private final Map<String, Film> filmMap = new HashMap<>();
    private final Map<String, Hall> hallMap = new HashMap<>();

    private final Map<Film, Set<Hall>> filmHallMap = new HashMap<>();
    private final Map<Hall, Seat[][]> hallSeatMap = new HashMap<>();

    public void addFilm(Film film) throws AppException {
        if(filmMap.containsKey(film.getName()) | filmHallMap.containsKey(film))
            throw new FilmNameAlreadyExist();

        filmMap.put(film.getName(), film);
        filmHallMap.put(film, new HashSet<>());
    }

    public void removeFilm(Film film) {
        // DELETE RELATED HALLS:
        Set<Hall> hallSet = filmHallMap.get(film);
        Set<Hall> hallToDelete = new HashSet<>();
        for (Hall hall: hallSet) {
            hallToDelete.add(hall);
        }
        hallSet.removeAll(hallToDelete);

        filmMap.remove(film.getName());
        filmHallMap.remove(film);

    }

    public Film getFilmByName(String filmName) {
        return filmMap.get(filmName);
    }

    public void addHall(Film film, Hall hall) {
        hallMap.put(hall.getName(), hall);
        hallSeatMap.put(hall, new Seat[hall.getRowNumber()][hall.getColumnNumber()]);

        Set<Hall> hallSet = filmHallMap.get(film);
        hallSet.add(hall);
    }

    public void removeHall(Film film, Hall hall) {
        hallMap.remove(hall.getName());
        hallSeatMap.remove(hall);

        Set<Hall> hallSet = filmHallMap.get(film);
        hallSet.remove(hall);
    }

    public Hall getHallByName(String hallName) {
        return hallMap.get(hallName);
    }

    public void addSeat(Hall hall, Seat seat) {
        Seat[][] seats = hallSeatMap.get(hall);
        seats[seat.getRow()][seat.getColumn()] = seat;
    }

    public Seat[][] getSeatArray(Hall hall) {
        return hallSeatMap.get(hall);
    }

    public ObservableList<Film> getFilmObservableList(){
        // TODO TEST HERE
        return FXCollections.observableList(new ArrayList<>(filmHallMap.keySet()));
    }

    public ObservableList<Hall> getHallObservableList(Film film) {
        Set<Hall> hallSet = filmHallMap.get(film);
        return FXCollections.observableList(new ArrayList<>(hallSet));
    }
}
