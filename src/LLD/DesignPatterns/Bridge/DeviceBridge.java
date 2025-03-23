package LLD.DesignPatterns.Bridge;

//public class Device {
//}


/**
 * Simple Bridge Design Pattern Example
 *
 * The Bridge pattern separates an abstraction from its implementation
 * so that both can vary independently.
 */

// Implementation interface
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
    void setChannel(int channel);
}

// Concrete Implementation A
class TV implements Device {
    private boolean isOn = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("TV turned on");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("TV turned off");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("TV volume set to " + volume);
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("TV channel set to " + channel);
    }
}

// Concrete Implementation B
class Radio implements Device {
    private boolean isOn = false;
    private int volume = 20;
    private int channel = 1;

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Radio turned on");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Radio turned off");
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Radio volume set to " + volume);
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("Radio station set to " + channel);
    }
}

// Abstraction
abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public abstract void power();
    public abstract void volumeUp();
    public abstract void volumeDown();
    public abstract void channelUp();
    public abstract void channelDown();
}

// Refined Abstraction
class BasicRemoteControl extends RemoteControl {
    private boolean isOn = false;
    private int volume = 0;
    private int channel = 1;

    public BasicRemoteControl(Device device) {
        super(device);
    }

    @Override
    public void power() {
        if (isOn) {
            device.turnOff();
            isOn = false;
        } else {
            device.turnOn();
            isOn = true;
        }
    }

    @Override
    public void volumeUp() {
        volume += 10;
        device.setVolume(volume);
    }

    @Override
    public void volumeDown() {
        if (volume >= 10) {
            volume -= 10;
        } else {
            volume = 0;
        }
        device.setVolume(volume);
    }

    @Override
    public void channelUp() {
        channel++;
        device.setChannel(channel);
    }

    @Override
    public void channelDown() {
        if (channel > 1) {
            channel--;
            device.setChannel(channel);
        }
    }
}

// Main class to demonstrate the Bridge pattern
public class DeviceBridge {
    public static void main(String[] args) {
        // Create TV with basic remote
        Device tv = new TV();
        RemoteControl tvRemote = new BasicRemoteControl(tv);

        System.out.println("Testing TV with remote:");
        tvRemote.power();
        tvRemote.volumeUp();
        tvRemote.volumeUp();
        tvRemote.channelUp();
        tvRemote.channelUp();
        tvRemote.power();

        System.out.println("\n--------------------------\n");

        // Create Radio with basic remote
        Device radio = new Radio();
        RemoteControl radioRemote = new BasicRemoteControl(radio);

        System.out.println("Testing Radio with remote:");
        radioRemote.power();
        radioRemote.volumeUp();
        radioRemote.channelUp();
        radioRemote.channelDown();
        radioRemote.power();
    }
}