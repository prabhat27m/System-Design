package LLD.DesignPatterns.Adapter;

/**
 * Simple Adapter Design Pattern Example
 *
 * The Adapter pattern allows incompatible interfaces to work together by creating a bridge between them.
 */

// Target interface that the client expects to use
interface MediaPlayer {
    void play(String filename);
}

// Adaptee (incompatible interface)
class AdvancedMediaPlayer {
    public void playMp4(String filename) {
        System.out.println("Playing MP4 file: " + filename);
    }

    public void playMkv(String filename) {
        System.out.println("Playing MKV file: " + filename);
    }
}

// Adapter class that makes AdvancedMediaPlayer compatible with MediaPlayer interface
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;

    public MediaAdapter() {
        this.advancedPlayer = new AdvancedMediaPlayer();
    }

    @Override
    public void play(String filename) {
        // Extract the file extension
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        // Adapt the call to the appropriate method
        switch(fileExtension) {
            case "mp4":
                advancedPlayer.playMp4(filename);
                break;
            case "mkv":
                advancedPlayer.playMkv(filename);
                break;
            default:
                System.out.println("Unsupported media format: " + fileExtension);
        }
    }
}

// Client class that uses the MediaPlayer interface
class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    public AudioPlayer() {
        this.mediaAdapter = new MediaAdapter();
    }

    @Override
    public void play(String filename) {
        // Extract the file extension
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

        // Native support for mp3 files
        if (fileExtension.equals("mp3")) {
            System.out.println("Playing MP3 file: " + filename);
        }
        // For other formats, use the adapter
        else {
            mediaAdapter.play(filename);
        }
    }
}

// Main class to demonstrate the Adapter pattern
public class MediaPlayerAdapter {
    public static void main(String[] args) {
        // Create an audio player
        AudioPlayer player = new AudioPlayer();

        System.out.println("Testing media player with different file formats:");

        // Play MP3 file (native support)
        player.play("song.mp3");

        // Play MP4 file (through adapter)
        player.play("movie.mp4");

        // Play MKV file (through adapter)
        player.play("video.mkv");

        // Try an unsupported format
        player.play("document.pdf");
    }
}