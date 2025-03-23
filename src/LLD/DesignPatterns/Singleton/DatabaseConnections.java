package LLD.DesignPatterns.Singleton;

/**
 * Singleton Design Pattern Example
 *
 * This pattern ensures that a class has only one instance and provides a global
 * point of access to it.
 */

// The Singleton class
class DatabaseConnection {
    // The single instance - static and private
    private static DatabaseConnection instance;

    // Database connection details
    private String url;
    private String username;
    private String password;
    private boolean isConnected;

    // Private constructor to prevent instantiation from outside
    private DatabaseConnection() {
        // Default connection parameters
        this.url = "jdbc:mysql://localhost:3306/mydb";
        this.username = "admin";
        this.password = "secure_password";
        this.isConnected = false;

        System.out.println("DatabaseConnection instance created");
    }

    // Public method to get the single instance
    public static DatabaseConnection getInstance() {
        // Create the instance if it doesn't exist (lazy initialization)
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Connect to the database
    public void connect() {
        if (!isConnected) {
            // In a real app, actual connection code would go here
            System.out.println("Connecting to database at " + url);
            System.out.println("Using credentials: " + username + "/********");

            // Simulate connection
            try {
                Thread.sleep(1000); // Simulate connection time
                isConnected = true;
                System.out.println("Database connection established successfully!");
            } catch (InterruptedException e) {
                System.out.println("Connection interrupted");
            }
        } else {
            System.out.println("Already connected to the database");
        }
    }

    // Disconnect from the database
    public void disconnect() {
        if (isConnected) {
            // In a real app, actual disconnection code would go here
            System.out.println("Disconnecting from database");

            // Simulate disconnection
            try {
                Thread.sleep(500); // Simulate disconnection time
                isConnected = false;
                System.out.println("Database connection closed");
            } catch (InterruptedException e) {
                System.out.println("Disconnection interrupted");
            }
        } else {
            System.out.println("Not connected to any database");
        }
    }

    // Get connection status
    public boolean isConnected() {
        return isConnected;
    }
}

// Main class to demonstrate the Singleton pattern
public class DatabaseConnections {
    public static void main(String[] args) {
        System.out.println("Singleton Pattern Demonstration\n");

        // Get the database connection instance
        System.out.println("Getting first database connection instance...");
        DatabaseConnection connection1 = DatabaseConnection.getInstance();
        connection1.connect();

        System.out.println("\nTrying to get another database connection instance...");
        DatabaseConnection connection2 = DatabaseConnection.getInstance();

        // Check if both references point to the same object
        System.out.println("\nChecking if both references point to the same instance:");
        System.out.println("connection1 == connection2: " + (connection1 == connection2));

        // Both references call methods on the same object
        System.out.println("\nUsing the second reference to check connection status:");
        System.out.println("Connection status: " + (connection2.isConnected() ? "Connected" : "Disconnected"));

        System.out.println("\nUsing the second reference to disconnect:");
        connection2.disconnect();

        // Check connection status using the first reference
        System.out.println("\nChecking connection status using the first reference:");
        System.out.println("Connection status: " + (connection1.isConnected() ? "Connected" : "Disconnected"));
    }
}