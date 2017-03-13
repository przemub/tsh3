package pl.przemub.tsh3;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.Arrays;

public class Login implements HackingInterface, Button.Listener, HackingBar.Listener {
    private Panel panel;
    private HackingBar hakowanie;
    private BasicWindow window;

    private HackingInterface nextInterface = this;

    private static final LayoutData CENTRE = GridLayout.createLayoutData(GridLayout.Alignment.CENTER,
            GridLayout.Alignment.CENTER);

    @Override
    public HackingInterface run() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();

        panel = new Panel();
        panel.setLayoutManager(new GridLayout(1));

        Label witaj = new Label("WITAJ W TAJNEJ SIECI HAKERSKIEJ");
        witaj.addStyle(SGR.BOLD);
        panel.addComponent(witaj);

        Label warn = new Label("Hakowanie jest przestępstwem karanym w co najmniej\ntrzech stanach grzywną " +
            "do miliona dolarów lub\n300 latami więzienia federalnego.");
        Label warn2 = new AnimatedLabel("Czy chcesz kontynuować?");
        warn.setLabelWidth(50);
        warn2.addStyle(SGR.ITALIC);

        panel.addComponent(warn);
        panel.addComponent(warn2);

        Panel guziki = new Panel();
        guziki.setLayoutManager(new GridLayout(2));
        Button hakuj = new Button("Czerwona piguła");
        Button niehakuj = new Button("Niebieska piguła");

        hakuj.addListener(this);
        niehakuj.addListener(this);

        guziki.addComponent(hakuj, CENTRE);
        guziki.addComponent(niehakuj, CENTRE);
        panel.addComponent(guziki, CENTRE);

        window = new BasicWindow();
        window.setHints(Arrays.asList(Window.Hint.CENTERED));
        window.setComponent(panel);

        MultiWindowTextGUI gui = new MultiWindowTextGUI(screen, new DefaultWindowManager(),
                new EmptySpace(TextColor.Factory.fromString("#054910")));

        gui.addWindowAndWait(window);

        Panel lpanel = new Panel();
        lpanel.setLayoutManager(new GridLayout(2));

        lpanel.addComponent(new Label("Nazwa użytkownika"));
        lpanel.addComponent(new TextBox());

        lpanel.addComponent(new Label("Hasło"));
        lpanel.addComponent(new TextBox());

        lpanel.addComponent(new EmptySpace(new TerminalSize(0,0)));
        lpanel.addComponent(new Button("Zaloguj się"));

        BasicWindow lwindow = new BasicWindow();
        lwindow.setHints(Arrays.asList(Window.Hint.CENTERED));
        lwindow.setComponent(lpanel);
        gui.addWindowAndWait(lwindow);

        return null;

    }

    @Override
    public void onTriggered(Button button) {
        switch (button.getLabel()) {
            case "Czerwona piguła":
                hakowanie = new HackingBar(this);
                panel.addComponent(hakowanie, CENTRE);
                hakowanie.startAnimation(100);
                break;
            case "Niebieska piguła":
                nextInterface = null;
                window.close();
        }
    }

    @Override
    public void onHacked(HackingBar hacked) {
        System.out.println("SHAKOWANO");
        window.close();
    }
}
