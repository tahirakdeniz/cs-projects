import java.util.List;

//TODO:  ██████╗ ███████╗    ██████╗ ███████╗███████╗██████╗  ██████╗ ███╗   ██╗███████╗██╗██████╗ ██╗     ███████╗██╗
//TODO:  ██╔══██╗██╔════╝    ██╔══██╗██╔════╝██╔════╝██╔══██╗██╔═══██╗████╗  ██║██╔════╝██║██╔══██╗██║     ██╔════╝██║
//TODO:  ██████╔╝█████╗      ██████╔╝█████╗  ███████╗██████╔╝██║   ██║██╔██╗ ██║███████╗██║██████╔╝██║     █████╗  ██║
//TODO:  ██╔══██╗██╔══╝      ██╔══██╗██╔══╝  ╚════██║██╔═══╝ ██║   ██║██║╚██╗██║╚════██║██║██╔══██╗██║     ██╔══╝  ╚═╝
//TODO:  ██████╔╝███████╗    ██║  ██║███████╗███████║██║     ╚██████╔╝██║ ╚████║███████║██║██████╔╝███████╗███████╗██╗
//TODO:  ╚═════╝ ╚══════╝    ╚═╝  ╚═╝╚══════╝╚══════╝╚═╝      ╚═════╝ ╚═╝  ╚═══╝╚══════╝╚═╝╚═════╝ ╚══════╝╚══════╝╚═╝

//TODO:  ██████╗  ██████╗     ███╗   ██╗ ██████╗ ████████╗     ██████╗██╗  ██╗ █████╗ ███╗   ██╗ ██████╗ ███████╗
//TODO:  ██╔══██╗██╔═══██╗    ████╗  ██║██╔═══██╗╚══██╔══╝    ██╔════╝██║  ██║██╔══██╗████╗  ██║██╔════╝ ██╔════╝
//TODO:  ██║  ██║██║   ██║    ██╔██╗ ██║██║   ██║   ██║       ██║     ███████║███████║██╔██╗ ██║██║  ███╗█████╗
//TODO:  ██║  ██║██║   ██║    ██║╚██╗██║██║   ██║   ██║       ██║     ██╔══██║██╔══██║██║╚██╗██║██║   ██║██╔══╝
//TODO:  ██████╔╝╚██████╔╝    ██║ ╚████║╚██████╔╝   ██║       ╚██████╗██║  ██║██║  ██║██║ ╚████║╚██████╔╝███████╗
//TODO:  ╚═════╝  ╚═════╝     ╚═╝  ╚═══╝ ╚═════╝    ╚═╝        ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚══════╝

//TODO:     ████████╗██╗  ██╗███████╗     ██████╗ ██╗██╗   ██╗███████╗███╗   ██╗     ██████╗ ██████╗ ██████╗ ███████╗██╗
//TODO:     ╚══██╔══╝██║  ██║██╔════╝    ██╔════╝ ██║██║   ██║██╔════╝████╗  ██║    ██╔════╝██╔═══██╗██╔══██╗██╔════╝██║
//TODO:        ██║   ███████║█████╗      ██║  ███╗██║██║   ██║█████╗  ██╔██╗ ██║    ██║     ██║   ██║██║  ██║█████╗  ██║
//TODO:        ██║   ██╔══██║██╔══╝      ██║   ██║██║╚██╗ ██╔╝██╔══╝  ██║╚██╗██║    ██║     ██║   ██║██║  ██║██╔══╝  ╚═╝
//TODO:        ██║   ██║  ██║███████╗    ╚██████╔╝██║ ╚████╔╝ ███████╗██║ ╚████║    ╚██████╗╚██████╔╝██████╔╝███████╗██╗
//TODO:        ╚═╝   ╚═╝  ╚═╝╚══════╝     ╚═════╝ ╚═╝  ╚═══╝  ╚══════╝╚═╝  ╚═══╝     ╚═════╝ ╚═════╝ ╚═════╝ ╚══════╝╚═╝
public class Main {
    public static void main(String[] args) {

        // Chapter 1
        missionHeader("The Path of the Warrior");

        TravelMap map = new TravelMap();
        map.initializeMap(args[0]);
        List<Trail> safestTrails = map.getSafestTrails();
        map.printSafestTrails(safestTrails);

        System.out.println();


        // Chapter 2
        missionHeader("Finding Hope in the Darkest of Times");

        Kingdom kingdom = new Kingdom();
        kingdom.initializeKingdom(args[1]);
        List<Colony> discoveredColonies = kingdom.getColonies();
        kingdom.printColonies(discoveredColonies);

        System.out.println();


        // Chapter 3
        missionHeader("Escaping the Time Trap Peril");

        TrapLocator locator = new TrapLocator(discoveredColonies);
        List<List<Integer>> traps = locator.revealTraps();
        locator.printTraps(traps);

        System.out.println();


    }

    public static void missionHeader(String title) {
        System.out.println(title);
        for (int i = 0; i < title.length(); i++) System.out.print("#");
        System.out.println();
    }
}
