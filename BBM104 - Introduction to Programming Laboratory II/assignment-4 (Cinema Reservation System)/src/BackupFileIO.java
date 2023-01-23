import java.io.*;
import java.util.Scanner;

/**
 * A class to read "backup.dat" file.
 */
public class BackupFileIO {

    private final CinemaDataManager cinemaDataManager = new CinemaDataManager();
    private final UserDataManager userDataManager = new UserDataManager();
    private final File file;

    /**
     * Reads "backup.dat" file, and creates instances of User, Film, Hall, and Seat.
     * @param filePath path of the "backup.dat" file to read.
     */
    public BackupFileIO(String filePath){
        file = new File(filePath);

        try {
            FileReader fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);

            while (scanner.hasNext()) {
                String[] lineArr = scanner.nextLine().split("\t");

                String inputType = lineArr[0];

                switch (inputType) {
                    case "user": createUser(lineArr); break;
                    case "film": createFilm(lineArr); break;
                    case "hall": createHall(lineArr); break;
                    case "seat": createSeat(lineArr); break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public CinemaDataManager getCinemaDataManager() {
        return cinemaDataManager;
    }

    public UserDataManager getUserManager() {
        return userDataManager;
    }

    // TODO CHANGE NAME:
    public void writeToFile(){
        try {
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(userDataManager.toString());
            fileWriter.write(cinemaDataManager.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // TODO ADD COMMENTS

    /**
     * Creates User instance and add to the UserDataManager
     * @param lineArr read line from file that is for User.
     */
    private void createUser(String[] lineArr) {
        String username = lineArr[1];
        String hashedPassword = lineArr[2];
        boolean isClubMember = Boolean.parseBoolean(lineArr[3]);
        boolean isAdmin = Boolean.parseBoolean(lineArr[4]);

        User user = new User(username, hashedPassword, isClubMember, isAdmin);

        try {
            userDataManager.addUser(user);
        } catch (AppException ignored) {}
    }

    /**
     * Creates Film instance and add to the CinemaDataManager
     * @param lineArr read line from file that is for Film.
     */
    private void createFilm(String[] lineArr) {
        String filmName = lineArr[1];
        String trailerPath = lineArr[2];
        int duration = Integer.parseInt(lineArr[3]);

        Film film = new Film(filmName, trailerPath, duration);
        // TODO IMPORTANT
        try {
            cinemaDataManager.addFilm(film);
        } catch (AppException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates Hall instance and add to the CinemaDataManager
     * @param lineArr read line from file that is for Hall.
     */
    private void createHall(String[] lineArr) {
        String filmName = lineArr[1];
        String hallName = lineArr[2];
        double pricePerSeat = Double.parseDouble(lineArr[3]);
        int rowNumber = Integer.parseInt(lineArr[4]);
        int columnNumber = Integer.parseInt(lineArr[5]);

        Hall hall = new Hall(hallName, pricePerSeat, rowNumber, columnNumber);
        Film film = cinemaDataManager.getFilmByName(filmName);
        cinemaDataManager.addHall(film, hall);
    }

    //TODO Write Comments
    private void createSeat(String[] lineArr) {
        String hallName = lineArr[2];
        int row = Integer.parseInt(lineArr[3]);
        int column = Integer.parseInt(lineArr[4]);
        String ownerName = lineArr[5];
        double priceBought = Double.parseDouble(lineArr[6]);

        User owner;
        if (ownerName.equals("null"))
            owner = null;
        else  owner = getUserManager().getUser(ownerName);

        Seat seat = new Seat(row, column, owner);
        Hall hall = cinemaDataManager.getHallByName(hallName);
        cinemaDataManager.addSeat(hall, seat);
    }
}
