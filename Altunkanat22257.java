import javax.swing.*;
import java.awt.*;
class SquarePanel extends JPanel{
    private int radius=50;
    public SquarePanel(){
        setPreferredSize(new Dimension(500,300));
        setBackground(Color.WHITE);
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillRect(getWidth() / 2 - radius,
                        getHeight() / 2 - radius,
                        2 * radius, 2 * radius);

            }
        }
public class LittleProject2 {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Resizable square" );
        JSlider slider = createSlider();
        SquarePanel SquarePanel = new SquarePanel();
        slider.addChangeListener(changeEvent -> {
            JSlider s = (JSlider) changeEvent.getSource();
            SquarePanel.setRadius(s.getValue());
        });
        frame.add(SquarePanel);
        frame.add(slider, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static JSlider createSlider() {
        JSlider slider = new JSlider();
        slider.setBorder(BorderFactory.createTitledBorder("Size of square"));
        slider.setMaximum(100);
        slider.setValue(50);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }
}
