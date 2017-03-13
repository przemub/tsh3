package pl.przemub.tsh3;

import com.googlecode.lanterna.gui2.AnimatedLabel;

public class HackingBar extends AnimatedLabel {
    private Listener listener;

    private static final int length = 30, msg = 10;

    private int currentFrame = 0;

    public HackingBar(Listener defListener) {
        super(generateFrame(0));

        for (int i = 1; i < length; i++)
            addFrame(generateFrame(i));
        for (int i = 0; i < msg; i++) {
            String frame = "";
            final String hack = "SHAKOWANO";

            for (int j = 0; j < (length-hack.length())/2; j++)
                frame += " ";
            frame += hack;
            for (int j = 0; j < (length-hack.length())/2; j++)
                frame += " ";

            addFrame(frame);
        }

        listener = defListener;
    }

    @Override
    public synchronized void nextFrame() {
        super.nextFrame();

        if (currentFrame++ == length+msg) {
            listener.onHacked(this);
        }
    }

    private static String generateFrame(int n) {
        String frame = "[";
        for (int j = 0; j < n; j++)
            frame += "+";
        for (int j = n; j < length; j++)
            frame += "-";
        frame += "]";

        return frame;
    }

    public interface Listener {
        void onHacked(HackingBar hacked);
    }
}
