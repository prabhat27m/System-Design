package LLD.DesignPatterns.Facade;

// Subsystem 1
class Amplifier {
    public void on() {
        System.out.println("Amplifier is on");
    }

    public void off() {
        System.out.println("Amplifier is off");
    }
}

// Subsystem 2
class DvdPlayer {
    public void on() {
        System.out.println("DVD Player is on");
    }

    public void off() {
        System.out.println("DVD Player is off");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }
}

// Subsystem 3
class Projector {
    public void on() {
        System.out.println("Projector is on");
    }

    public void off() {
        System.out.println("Projector is off");
    }
}

// Subsystem 4
class Screen {
    public void down() {
        System.out.println("Screen is down");
    }

    public void up() {
        System.out.println("Screen is up");
    }
}

// Facade class
class HomeTheaterFacade {
    private Amplifier amplifier;
    private DvdPlayer dvdPlayer;
    private Projector projector;
    private Screen screen;

    public HomeTheaterFacade(Amplifier amplifier, DvdPlayer dvdPlayer, Projector projector, Screen screen) {
        this.amplifier = amplifier;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.screen = screen;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        screen.down();
        projector.on();
        amplifier.on();
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down the theater...");
        screen.up();
        projector.off();
        amplifier.off();
        dvdPlayer.off();
    }
}

// Main class to test the Facade Pattern
public class HomeTheater{
    public static void main(String[] args) {
        // Creating subsystems
        Amplifier amplifier = new Amplifier();
        DvdPlayer dvdPlayer = new DvdPlayer();
        Projector projector = new Projector();
        Screen screen = new Screen();

        // Creating the Facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amplifier, dvdPlayer, projector, screen);

        // Using the Facade to simplify operations
        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}
