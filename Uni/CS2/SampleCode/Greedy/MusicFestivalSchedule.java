import java.util.*;

public class MusicFestivalSchedule {
    public static void main(String[] args) {
        ArrayList<Show> shows = new ArrayList<>();
        shows.add(new Show(1700, 1900)); // Green Day
        shows.add(new Show(1900, 2100)); // Weezer
        shows.add(new Show(1930, 2130)); // Gorillaz
        shows.add(new Show(2100, 2300)); // 311
        shows.add(new Show(2130, 2230)); // Coldplay
        shows.add(new Show(2300, 200));  // The Killers

        // sort the shows by their end times in ascending order
        Collections.sort(shows, Comparator.comparingInt(show -> show.end));

        int currentTime = 1700; // starting time
        int attendedShows = 0;

        for (Show show : shows) {
            if (show.start >= currentTime) {
                attendedShows++;
                currentTime = show.end;
            }
        }

        System.out.println("Max number of shows you can attend: " + attendedShows);
    }
}

class Show {
    int start;
    int end;

    public Show(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
