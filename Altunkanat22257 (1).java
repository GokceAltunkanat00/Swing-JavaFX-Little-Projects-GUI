import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import javax.swing.*;

public class s22257 {

    public static void main(String[] args) {

        var frame = new JFrame("Color selector");

        frame.setSize( new Dimension( 600,  400 ) );

        frame.setLayout( new BorderLayout() );

        var drawBoard 		= new MyDrawPanel();
        var rgbBackground 	= new MyRgbPanel( "Background", drawBoard, false );
        var rgbForground 	= new MyRgbPanel( "Foreground", drawBoard, true );

        frame.add( rgbBackground 	,BorderLayout.WEST 		);
        frame.add( drawBoard		,BorderLayout.CENTER 	);
        frame.add( rgbForground		,BorderLayout.EAST 		);

        frame.setVisible( true );


    }
}
class MyRgbPanel extends JPanel {

    private  JLabel hexLabel;

    private final MySliderBox  red 		= new MySliderBox("R");
    private final MySliderBox  green 	= new MySliderBox("G");
    private final MySliderBox  blue 	= new MySliderBox("B");

    private final MyDrawPanel drawBord;

    private boolean isForeground = true;

    public MyRgbPanel( String title, MyDrawPanel drawBord,  boolean isForeground ) {

        this.setBorder( BorderFactory.createTitledBorder( title ) );

        this.drawBord = drawBord;

        this.isForeground = isForeground;

        if( isForeground )this.blue.slider.setValue( 150 );

        this.setLayout( new BorderLayout());

        this.hexLabel = this.createHexLabel();

        this.add( this.createSliders()	,BorderLayout.CENTER	);
        this.add( this.hexLabel			,BorderLayout.SOUTH		);

        this.setNewValue();

        this.setPreferredSize( new Dimension( 120, 600 ));
    }


    private JPanel createSliders() {
        var sliders = new JPanel();

        sliders.setLayout( new GridLayout( 1, 3 ) );

        sliders.add( red	);
        sliders.add( green	);
        sliders.add( blue	);

        red		.slider.addChangeListener( event -> {  setNewValue(); } );
        green	.slider.addChangeListener( event -> {  setNewValue(); } );
        blue	.slider.addChangeListener( event -> {  setNewValue(); } );

        return sliders;
    }

    private void setNewValue() {

        int r = red		.slider.getValue();
        int g = green	.slider.getValue();
        int b = blue	.slider.getValue();

        if( isForeground) {
            this.drawBord.setForeground( new Color(r,g,b) );
        } else {
            this.drawBord.setBackground( new Color(r,g,b) );
        }

        this.drawBord.repaint();

        var hex = String.format("#%02X%02X%02X", r,g,b );

        this.hexLabel.setText( hex );
    }

    private JLabel createHexLabel() {

        var label = new JLabel();

        label.setHorizontalAlignment( JLabel.CENTER );
        label.setBorder( BorderFactory.createTitledBorder("Hex") );
        label.setPreferredSize( new Dimension( 0, 35 ) );

        return label;
    }

}
class MySliderBox extends JPanel {

    public final JSlider 	slider;
    private final JLabel 	label;

    public MySliderBox( String titel ) {

        this.setBorder( BorderFactory.createTitledBorder( titel ) );

        this.label 	= new JLabel();
        this.slider = this.createSlider();

        this.setLayout( new BorderLayout() );

        this.add( this.slider	,BorderLayout.CENTER  	);
        this.add( this.label	,BorderLayout.SOUTH 	);

        this.setLabelValue( this.slider.getValue());
    }

    private JSlider createSlider() {
        var slider = new JSlider( 0, 255, 0 );

        slider.setOrientation( JSlider.VERTICAL );

        slider.addChangeListener( event -> {
            this.setLabelValue( slider.getValue());
        });

        return slider;
    }

    private void setLabelValue( int value ) {
        this.label.setText( String.format("%03d", value ) );
    }

}
class MyDrawPanel extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        var d = this.getSize();

        int rx = 80, ry = 80;

        int x = d.width/2;
        int y = d.height/2;

        var dateTime =  ZonedDateTime.now().format( DateTimeFormatter.RFC_1123_DATE_TIME);

        g.setFont( new Font( Font.DIALOG, Font.PLAIN, 15));
        int strWidth = g.getFontMetrics().stringWidth( dateTime );
        g.drawString( dateTime, x-strWidth/2, y-100 );

        g.fillArc( x-rx/2, y-ry/2, rx, ry, 0, 360 );

        rx = 120; ry = 120;

        g.drawArc( x-rx/2, y-ry/2, rx, ry, 0, 360 );

        g.setFont( new Font( Font.DIALOG, Font.BOLD, 15));
        strWidth = g.getFontMetrics().stringWidth( dateTime );
        g.drawString( dateTime, x-strWidth/2, y+100 );
    }

}
