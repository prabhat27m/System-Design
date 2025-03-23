package LLD.DesignPatterns.Proxy;

// Subject interface
interface Subject {
    void request();
}

// RealSubject: The actual class that performs the action
class RealSubject implements Subject {
    public void request() {
        System.out.println("RealSubject: Handling request.");
    }
}

// Proxy: Controls access to the RealSubject
class Proxy implements Subject {
    private RealSubject realSubject;

    public void request() {
        if (realSubject == null) {
            // Lazy initialization (only create the RealSubject when it's needed)
            realSubject = new RealSubject();
        }
        System.out.println("Proxy: Checking access before delegating request.");
        realSubject.request();
    }
}

// Main class to test the Proxy Pattern
public class ProxyRequest {
    public static void main(String[] args) {
        // Creating a proxy to access the RealSubject
        Subject proxy = new Proxy();

        // Using the proxy to make requests
        proxy.request();
        proxy.request(); // Second request, will not recreate the RealSubject
    }
}
