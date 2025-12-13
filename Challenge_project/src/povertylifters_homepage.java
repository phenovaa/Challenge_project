import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

public class povertylifters_homepage {

    private static final Color PANEL_COLOR = new Color(70, 130, 180); // Blauwe achtergrond
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font SUBTITLE_FONT = new Font("Arial", Font.PLAIN, 14);
    private static final Color BACKGROUND_COLOR = new Color(221, 221, 221);

    static class RoundedPanel extends JPanel {
        private int radius;

        public RoundedPanel(int radius) {
            super();
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Povertylifters Prototype");
        frame.setSize(1000, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ================= Navbar =================
        JPanel navbar = new JPanel(new BorderLayout());
        navbar.setBackground(PANEL_COLOR);
        navbar.setPreferredSize(new Dimension(frame.getWidth(), 60));
        navbar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        navbar.setMinimumSize(new Dimension(frame.getWidth(), 60));

        JLabel logo = new JLabel("PovertyLifters");
        logo.setForeground(TEXT_COLOR);
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        navbar.add(logo, BorderLayout.WEST);

        JPanel navButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        navButtons.setOpaque(false);

        JButton sectiesButton = new JButton("Secties");
        JButton contactButton = new JButton("Contact / Aanmelding");
        String[] talen = {"Nederlands", "Arabisch", "Chinees", "Engels"};
        JComboBox<String> taalDropdown = new JComboBox<>(talen);
        taalDropdown.addActionListener(e -> System.out.println("Taal gekozen: " + taalDropdown.getSelectedItem()));

        navButtons.add(sectiesButton);
        navButtons.add(contactButton);
        navButtons.add(taalDropdown);
        navbar.add(navButtons, BorderLayout.EAST);

        frame.add(navbar, BorderLayout.NORTH);

        // ================= Content =================
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Eerste rij: 2 boxen
        JPanel firstRow = new JPanel();
        firstRow.setLayout(new BoxLayout(firstRow, BoxLayout.X_AXIS));
        firstRow.setBackground(BACKGROUND_COLOR);
        firstRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        RoundedPanel leftPanel = createBox("Een plek voor duidelijke hulp: taal, werk, zorg & geld",
                "<html><center>PovertyLifters helpt nieuwkomers en AZC bewoners om snel de juiste informatie te vinden - in begrijpelijke taal en met doorwijzing naar lokale instantie</center></html>",
                "Meer info");

        RoundedPanel rightPanel = createBox("Andere titel of info hier",
                "<html><center>Hier kun je extra informatie, links of andere content plaatsen.</center></html>",
                "Bekijk details");

        firstRow.add(leftPanel);
        firstRow.add(Box.createRigidArea(new Dimension(20, 0)));
        firstRow.add(rightPanel);

        contentPanel.add(firstRow);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Tweede rij: 3 boxen
        JPanel secondRow = new JPanel();
        secondRow.setLayout(new BoxLayout(secondRow, BoxLayout.X_AXIS));
        secondRow.setBackground(BACKGROUND_COLOR);
        secondRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        RoundedPanel box1 = createBox("Nederlands begrijpen in 3 stappen:",
                "<html>- Informatie op A1-niveau<br>- Uitleg van brieven en formulieren (gemeente, UWV, zorg)<br>- Doorverwijzing naar taalhuis en lokale cursussen</html>",
                null);

        RoundedPanel box2 = createBox("Van eerste baan naar zekerheid:",
                "<html>- CV-hulp en voorbeeldsollicitaties (eenvoudig)<br>- Werk dat past bij taalniveau en ervaring<br>- Stages & vrijwilligerswerk om te starten</html>",
                null);

        RoundedPanel box3 = createBox("Grip op gezondheid en geld:",
                "<html>- Basiszorg en huisarts - hoe regel je dat?<br>- Regelingen: bijzonder bijstand, toeslag, sportfonds<br>- Budgethulp met eenvoudige stappen en pictogrammen</html>",
                null);

        secondRow.add(box1);
        secondRow.add(Box.createRigidArea(new Dimension(20, 0)));
        secondRow.add(box2);
        secondRow.add(Box.createRigidArea(new Dimension(20, 0)));
        secondRow.add(box3);

        contentPanel.add(secondRow);

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        frame.add(scrollPane, BorderLayout.CENTER);

        // ================= Responsive behavior =================
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = frame.getWidth();

                firstRow.removeAll();
                secondRow.removeAll();

                // Eerste rij responsive
                if (width < 800) {
                    firstRow.setLayout(new BoxLayout(firstRow, BoxLayout.Y_AXIS));
                    firstRow.add(leftPanel);
                    firstRow.add(Box.createRigidArea(new Dimension(0, 15)));
                    firstRow.add(rightPanel);
                    leftPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    rightPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                } else {
                    firstRow.setLayout(new BoxLayout(firstRow, BoxLayout.X_AXIS));
                    firstRow.add(leftPanel);
                    firstRow.add(Box.createRigidArea(new Dimension(20, 0)));
                    firstRow.add(rightPanel);
                }

                // Tweede rij responsive
                if (width < 1200) {
                    secondRow.setLayout(new BoxLayout(secondRow, BoxLayout.Y_AXIS));
                    secondRow.add(box1);
                    secondRow.add(Box.createRigidArea(new Dimension(0, 15)));
                    secondRow.add(box2);
                    secondRow.add(Box.createRigidArea(new Dimension(0, 15)));
                    secondRow.add(box3);
                    box1.setAlignmentX(Component.CENTER_ALIGNMENT);
                    box2.setAlignmentX(Component.CENTER_ALIGNMENT);
                    box3.setAlignmentX(Component.CENTER_ALIGNMENT);
                } else {
                    secondRow.setLayout(new BoxLayout(secondRow, BoxLayout.X_AXIS));
                    secondRow.add(box1);
                    secondRow.add(Box.createRigidArea(new Dimension(20, 0)));
                    secondRow.add(box2);
                    secondRow.add(Box.createRigidArea(new Dimension(20, 0)));
                    secondRow.add(box3);
                }

                firstRow.revalidate();
                firstRow.repaint();
                secondRow.revalidate();
                secondRow.repaint();
            }
        });

        frame.setVisible(true);
    }

    private static RoundedPanel createBox(String title, String subtitle, String buttonText) {
        RoundedPanel panel = new RoundedPanel(15);
        panel.setLayout(new GridBagLayout());
        panel.setBackground(PANEL_COLOR);
        panel.setPreferredSize(new Dimension(250, 200));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(TEXT_COLOR);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(titleLabel);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(subtitleLabel);

        if (buttonText != null) {
            JButton button = new JButton(buttonText);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            content.add(Box.createRigidArea(new Dimension(0, 15)));
            content.add(button);
        }

        panel.add(content);
        return panel;
    }
}

