import java.util.*;

public class CommentManager {
    private Map<String, List<String>> titleToComments;

    public CommentManager() {
        titleToComments = new HashMap<>();
    }

    // Add multiple comments to a title
    public void addComments(String title, List<String> comments) {
        if (!titleToComments.containsKey(title)) {
            titleToComments.put(title, new ArrayList<>());
        }
        titleToComments.get(title).addAll(comments);
    }

    // Query for titles based on comments (case-insensitive), matching all comments
    public List<String> getTitlesByComments(List<String> queryComments) {
        List<String> matchingTitles = new ArrayList<>();

        // Check if any of the query comments is a title (case-insensitive)
        boolean queryContainsTitle = false;
        for (String queryComment : queryComments) {
            for (String title : titleToComments.keySet()) {
                if (title.equalsIgnoreCase(queryComment)) {
                    queryContainsTitle = true;
                    List<String> comments = titleToComments.get(title);
                    matchingTitles.add(ANSI_CYAN + title);
                    matchingTitles.addAll(comments);
                }
            }
        }

        if (!queryContainsTitle) {
            // If none of the query comments is a title, perform the regular query
            for (Map.Entry<String, List<String>> entry : titleToComments.entrySet()) {
                List<String> comments = entry.getValue();
                boolean allMatch = true;

                // Check if all query comments are present in the title's comments
                for (String queryComment : queryComments) {
                    if (!comments.stream().anyMatch(comment -> comment.equalsIgnoreCase(queryComment))) {
                        allMatch = false;
                        break;
                    }
                }

                if (allMatch) {
                    matchingTitles.add(entry.getKey());
                }
            }
        }

        return matchingTitles;
    }

    // ANSI color codes
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        CommentManager manager = new CommentManager();

        // ... (Adding multiple entries with titles and comments)
        manager.addComments("Beavis and Butthead: Do America", Arrays.asList("1996", "Mike Judge", "MTV", "Highland", "California",
                "Las Vegas", "Hoover Dam", "Nevada", "Yellowstone", "Old Faithful", "Petrified Forest", "Arizona", "Washington DC",
                "summer", "stoner", "90s", "*"));
        manager.addComments("Pee Wee's Big Adventure", Arrays.asList("1985", "Tim Burton", "Warner Bros", "California", "Alamo", "psychic",
                "Texas", "Cabazon", "Dinosaurs", "rainy day", "stoner", "creepy", "scary", "Halloween", "classic", "cult classic", "80s", "*"));
        manager.addComments("Jurassic Park", Arrays.asList("1993", "Steven Spielberg", "Universal", "Samuel L Jackson", "Jeff Goldblum",
                "dinosaurs", "Hawaii", "Costa Rica", "rainy day", "90s", "classic", "all time", "*"));
        manager.addComments("The Lost World: Jurassic Park", Arrays.asList("1997", "Steven Spielberg", "Universal", "Jeff Goldblum",
                "Vince Vaughn", "Julianne Moore", "dinosaurs", "Hawaii", "Costa Rica", "San Diego", "rainy day", "90s", "classic", "all time", "*"));
        manager.addComments("Jurassic Park III", Arrays.asList("2001", "Universal", "dinosaurs", "Hawaii", "Costa Rica",
                "rainy day", "*"));
        manager.addComments("Gone in 60 Seconds", Arrays.asList("2000", "Touchstone", "Nicolas Cage", "cars",
                "heist", "Los Angeles", "Long Beach", "w Karen", "*"));
        manager.addComments("Raiders of the Lost Ark", Arrays.asList("1981", "Steven Spielberg", "Paramount", "George Lucas",
                "Harrison Ford", "Nazis", "history", "World War II", "1940s", "Germany", "Europe", "Africa", "classic", "all time", "80s",
                "w Karen", "*"));
        manager.addComments("Indiana Jones and the Temple of Doom", Arrays.asList("1984", "Steven Spielberg", "Paramount", "George Lucas",
                "Harrison Ford", "India", "history", "classic", "all time", "80s", "w Karen", "*"));
        manager.addComments("Indiana Jones and the Last Crusade", Arrays.asList("1989", "Steven Spielberg", "Paramount", "George Lucas",
                "Harrison Ford", "history", "Venice", "Italy", "Europe", "holy grail", "classic", "all time", "80s", "w Karen", "*"));
        manager.addComments("The Silence of the Lambs", Arrays.asList("1991", "Orion", "Jodie Foster", "Anthony Hopkins", "killer", "scary",
                "Halloween", "serial killer", "creepy", "thriller", "90s", "*"));
        manager.addComments("Hannibal", Arrays.asList("2001", "Orion", "Gary Oldman", "Anthony Hopkins", "killer", "scary",
                "Halloween", "serial killer", "creepy", "thriller", "w Karen", "*"));
        manager.addComments("Red Dragon", Arrays.asList("2002", "Orion", "Edward Norton", "Anthony Hopkins", "killer", "scary",
                "Halloween", "serial killer", "creepy", "thriller", "w Karen", "*"));
        manager.addComments("The Lost Boys", Arrays.asList("1987", "Warner Bros", "Kiefer Sutherland", "Corey Feldman", "Santa Cruz",
                "California", "vampires", "scary", "Halloween", "creepy", "thriller", "80s", "cult classic", "w Karen", "*"));
        manager.addComments("Halloween: Resurrection", Arrays.asList("2002", "Dimension", "Miramax", "Michael Myers", "slasher",
                "killer", "serial killer", "scary", "Halloween", "creepy", "thriller", "w Karen", "*"));
        manager.addComments("Scream 2", Arrays.asList("1997", "Dimension", "Wes Craven", "Ghost Face", "Courtney Cox", "David Arquette",
                "Neve Campbell", "slasher", "serial killer", "killer", "scary", "Halloween", "creepy", "thriller", "90s", "w Karen", "*"));
        manager.addComments("Aliens", Arrays.asList("1986", "20th Century Fox", "James Cameron", "Sigourney Weaver", "aliens",
                "sci fi", "space", "scary", "Halloween", "thriller", "classic", "cult classic", "all time", "80s", "w Karen", "*"));
        manager.addComments("Dawn of the Dead", Arrays.asList("2004", "Universal", "Zack Snyder", "mall", "zombies", "scary",
                "Milwaukee", "Wisconsin", "Halloween", "thriller", "w Karen", "*", "100 mins"));
        manager.addComments("Fargo", Arrays.asList("1996", "Steve Buscemi", "North Dakota", "Minnesota", "Minneapolis",
                "St Paul", "midwest", "winter", "snow", "crime", "thriller", "cult classic", "90s", "w Karen", "*"));
        manager.addComments("Robocop", Arrays.asList("1987", "Orion", "Detroit", "Michigan", "midwest", "action", "comics", "cyberpunk",
                "sci fi", "crime", "heist", "android", "cyborg", "80s", "cult classic", "w Karen", "*"));
        manager.addComments("Kids", Arrays.asList("1995", "Rosario Dawson", "New York", "drama", "heavy", "teens", "drugs", "stoner",
                "90s", "w Karen", "*"));
        manager.addComments("The 5th Element", Arrays.asList("1997", "Luc Besson", "Bruce Willis", "Chris Tucker", "Milla Jovovich",
                "sci fi", "space", "future", "futuristic", "aliens", "action", "Egypt", "90s", "w Karen", "*"));
        manager.addComments("The Godfather", Arrays.asList("1972", "Paramount", "Francis Ford Coppola", "Marlon Brando", "Al Pacino",
                "mafia", "crime", "all time", "classic", "Italian", "New York", "Italy", "rainy day", "70s", "w Karen", "*"));
        manager.addComments("The Breakfast Club", Arrays.asList("1985", "Universal", "John Hughes", "Emilio Estevez", "teens",
                "high school", "comedy", "saturday", "sunday", "lazy day", "rainy day", "weekend", "80s", "w Karen", "*"));
        manager.addComments("Blade Runner", Arrays.asList("1982", "Warner Bros", "Ridley Scott", "Harrison Ford", "future", "cyborg",
                "sci fi", "futuristic", "dystopian", "Los Angeles", "thriller", "space", "android", "classic", "cult classic", "80s",
                "w Karen", "*"));
        manager.addComments("Citizen Kane", Arrays.asList("1941", "RKO Radio Pictures", "RKO", "Orson Welles", "classic", "all time",
                "40s", "w Karen", "*"));
        manager.addComments("Giant", Arrays.asList("1956", "Warner Bros", "James Dean", "Elizabeth Taylor", "Rock Hudson",
                "western", "classic", "all time", "Texas", "50s", "w Karen", "*"));
        manager.addComments("Rebel Without a Cause", Arrays.asList("1955", "Warner Bros", "James Dean", "Natalie Wood",
                "Griffith Observatory", "teens", "classic", "all time", "Los Angeles", "50s", "w Karen", "*"));
        manager.addComments("Barry Lyndon", Arrays.asList("1975", "Warner Bros", "Columbia", "Stanley Kubrick", "all time", "70s",
                "w Karen", "*"));
        manager.addComments("Children of Men", Arrays.asList("2006", "Universal", "Clive Owen", "Julianne Moore", "dystopian",
                "future", "sci fi", "all time", "cult classic", "action", "thriller", "futuristic", "2000s", "w Karen", "*"));
        manager.addComments("La Haine", Arrays.asList("1995", "Paris", "France", "foreign", "all time", "90s", "w Karen", "*"));
        manager.addComments("Mulholland Drive", Arrays.asList("2001", "Universal", "David Lynch", "Los Angeles", "Hollywood",
                "Beverly Hills", "all time", "California", "mystery", "creepy", "2000s", "w Karen", "*"));
        manager.addComments("Zodiac", Arrays.asList("2007", "Warner Bros", "Paramount", "Jake Gyllenhaal", "Robert Downey Jr",
                "Mark Ruffalo", "San Francisco", "Bay Area", "California", "Napa", "Sonoma", "Vallejo", "Riverside", "thriller", "mystery",
                "scary", "creepy", "Halloween", "serial killer", "rainy day", "2000s", "60s", "70s", "*"));
        manager.addComments("Good Will Hunting", Arrays.asList("1997", "Miramax", "Ben Affleck", "Matt Damon", "Robin Williams",
                "Boston", "MIT", "Massachusetts", "all time", "drama", "November", "fall", "90s", "w Karen", "*"));
        manager.addComments("Cinema Paradiso", Arrays.asList("1988", "Rome", "Italy", "foreign", "all time", "80s", "w Karen", "*"));
        manager.addComments("Old Boy", Arrays.asList("2003", "Korea", "foreign", "action", "thriller", "all time", "w Karen", "*"));
        manager.addComments("Apocalypse Now", Arrays.asList("1979", "United Artists", "Francis Ford Coppola", "Marlon Brando", "war",
                "Vietnam War", "Vietnam", "classic", "all time", "70s", "w Karen", "*"));
        manager.addComments("Saving Private Ryan", Arrays.asList("1998", "DreamWorks", "Paramount", "Steven Spielberg", "Tom Hanks",
                "Matt Damon", "war", "World War II", "France", "Europe", "Nazis", "classic", "all time", "rainy day", "40s", "90s",
                "w Karen", "*"));
        manager.addComments("Full Metal Jacket", Arrays.asList("1987", "Warner Bros", "Columbia", "Stanley Kubrick", "war",
                "Vietnam War", "Vietnam", "classic", "all time", "70s", "80s", "w Karen", "*"));
        manager.addComments("Memories of Murder", Arrays.asList("2003", "Korean", "foreign", "crime", "thriller",
                "classic", "all time", "2000s", "80s", "w Karen", "*"));
        manager.addComments("12 Angry Men", Arrays.asList("1957", "United Artists", "crime", "drama", "rainy day",
                "classic", "all time", "New York", "50s", "w Karen", "*"));
        manager.addComments("13 Ghosts", Arrays.asList("2001", "Warner Bros", "Columbia", "TriStar", "rainy day",
                "Halloween", "horror", "thriller", "paranormal", "ghosts", "scary", "creepy", "psychic", "ghost hunter", "2000s",
                "w Karen", "*", "90 mins"));
        manager.addComments("Hollow Man", Arrays.asList("2000", "Columbia", "Sony Pictures", "Kevin Bacon", "Josh Brolin",
                "Halloween", "scary", "thriller", "horror", "Invisible Man", "sci fi", "rainy day", "2000s", "w Karen", "*"));
        manager.addComments("Seven", Arrays.asList("1995", "New Line Cinema", "Brad Pitt", "Morgan Freeman", "Gwyneth Paltrow",
                "crime", "drama", "rainy day", "classic", "all time", "thriller", "serial killer", "90s", "w Karen", "*"));
        manager.addComments("Little Nicky", Arrays.asList("2000", "New Line Cinema", "Happy Madison", "Adam Sandler", "comedy",
                "hell", "devil", "Halloween", "New York City", "New York", "2000s", "w Karen", "M List", "MAX", "*"));
        manager.addComments("Constantine", Arrays.asList("2005", "Warner Bros", "Village Roadshow", "Keanu Reeves", "Shia Lebouf",
                "superhero", "comics", "rainy day", "Halloween", "horror", "thriller", "DC Comics", "DC", "angels", "demons", "hell", "religion",
                "Los Angeles", "2000s", "w Karen", "*"));
        manager.addComments("Young Frankenstein", Arrays.asList("1974", "20th Century Fox", "Mel Brooks", "Gene Wilder", "comedy",
                "horror", "Halloween", "rainy day", "classic", "70s", "w Karen", "M List", "MAX", "*"));
        manager.addComments("They Came Together", Arrays.asList("2014", "Lionsgate", "Paul Rudd", "Amy Poehler", "comedy",
                "romance", "romantic comedy", "New York City", "New York", "2010s", "w Karen", "*"));
        manager.addComments("Ghost", Arrays.asList("1990", "Paramount", "Patrick Swayze", "Demi Moore", "romance", "New York City",
                "New York", "Manhattan", "ghosts", "Halloween", "90s", "w Karen", "*"));
        manager.addComments("The Sixth Sense", Arrays.asList("1999", "Hollywood Pictures", "Spyglass", "M Night Shyamalan", "Bruce Willis",
                "ghosts", "psychological thriller", "thriller", "Philadelphia", "Halloween", "rainy day", "90s", "w Karen", "*"));
        manager.addComments("Jeepers Creepers", Arrays.asList("2001", "United Artists", "MGM", "horror", "Halloween",
                "Spring Break", "killer", "Florida", "slasher", "2000s", "w Karen", "*"));
        manager.addComments("Meet the Fockers", Arrays.asList("2004", "Universal", "DreamWorks", "Tribeca", "Jay Roach", "Ben Stiller",
                "Robert De Niro", "Dustin Hoffman", "romance", "comedy", "romantic comedy", "Florida", "Miami", "New York", "2000s", "w Karen",
                "*"));
        manager.addComments("Fahrenheit 451", Arrays.asList("2018", "HBO Films", "Michael B Jordan", "dystopian", "future", "drama",
                "Cleveland", "midwest", "2000s", "w Karen", "*"));
        manager.addComments("Armageddon", Arrays.asList("1998", "Touchstone", "Jerry Bruckheimer Films", "Michael Bay", "JJ Abrams",
                "Jerry Bruckheimer", "Bruce Willis", "Billy Bob Thornton", "Ben Affleck", "Steve Buscemi", "space", "sci fi", "disaster",
                "NASA", "drama", "90s", "w Karen", "*"));
        manager.addComments("The Flash", Arrays.asList("2023", "DC Studios", "Warner Bros", "DC comics", "DC", "superhero", "action",
                "M List", "MAX", "20s", "*"));
        manager.addComments("Ready Player One", Arrays.asList("2018", "Amblin", "Warner Bros", "Village Roadshow", "Steven Speilberg",
                "sci fi", "dystopian", "future", "virtual reality", "stoner", "video games", "pop culture", "nostalgia", "action", "M List",
                "HULU", "10s", "*"));
        manager.addComments("The Iron Giant", Arrays.asList("1999", "animation", "Warner Bros", "Jennifer Aniston", "Harry Connick Jr",
                "Vin Diesel", "drama", "kids", "snow", "winter", "robot", "Cold War", "50s", "90s", "w Karen", "*"));
        manager.addComments("Who Framed Roger Rabbit?", Arrays.asList("1988", "animation", "Touchstone", "Amblin", "Buena Vista",
                "Robert Zemekis", "Christopher LLoyd", "Bob Hoskins", "Los Angeles", "Hollywood", "kids", "comedy", "Disney", "Acme",
                "cartoons", "80s", "40s", "w Karen", "*"));
        manager.addComments("Tarzan", Arrays.asList("1999", "animation", "Disney", "Buena Vista", "jungle",
                "comedy", "romance", "kids", "Africa", "90s", "w Karen", "*"));
        manager.addComments("The Flinstones", Arrays.asList("1994", "Universal", "Amblin", "Hanna-Barbera", "John Goodman", "Rick Moranis",
                "Elizabeth Taylor", "Halle Berry", "Rosie O'Donnell", "family", "comedy", "dinosaurs", "California", "90s", "w Karen", "*"));
        manager.addComments("Shaun of the Dead", Arrays.asList("2004", "Universal", "United", "zombies", "horror", "apocalypse",
                "England", "beer", "stoner", "Halloween", "comedy", "2000s", "w Karen", "*", "99 mins"));
        manager.addComments("A Christmas Story", Arrays.asList("1983", "MGM", "United Artists", "Christmas", "comedy", "holidays",
                "midwest", "Indiana", "Lake Michigan", "classic", "40s", "80s", "w Karen", "*", "94 mins"));

        // Query for titles based on comments (case-insensitive), without duplicates
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Ask the user to input comments separated by commas
            System.out.print(ANSI_RESET+"Enter comments separated by commas: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("end")) {
                System.out.println(ANSI_RED+"Enjoy the Show!!");
                break; // Exit the loop
            }

            // Split the input into an array of query comments
            List<String> queryComments = Arrays.asList(input.split(", "));

            // Query for titles based on user-input comments, including titles and their comments
            List<String> results = manager.getTitlesByComments(queryComments);

            // Print header in regular font
            System.out.println("Movies with matching comments:");

            // Flag to indicate if the next result is a title
            boolean nextIsTitle = false;

            // Print the results based on whether they are titles or comments
            for (String result : results) {
                if (nextIsTitle) {
                    // Print title in cyan (next line)
                    System.out.println(ANSI_YELLOW + result);
                    nextIsTitle = false;
                } else if (result.startsWith(ANSI_CYAN)) {
                    nextIsTitle = true;
                    System.out.println(result); // Print title in teal + (next line)
                } else {
                    System.out.println(ANSI_YELLOW + result); // Print regular text in yellow color
                }
            }
        }
    }
}