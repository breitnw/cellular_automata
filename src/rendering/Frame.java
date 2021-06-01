package rendering;

import main.Runner;
import simulation.GridManager;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Frame extends JFrame
{
	private Container pane;
	private RenderPanel renderPanel;

    // How many pixels a cell takes up on the screen
	private final int pixelScale = 2;

	public Frame()
    {
		super();

        // Instantiate and configure a new JFrame and its content pane
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pane = getContentPane();
        pane.setLayout(new BorderLayout());
        setResizable(false);

        // Set the size of the content pane
        int reqWidth = GridManager.getMapWidth() * pixelScale;
        int reqHeight = GridManager.getMapHeight() * pixelScale;
        pane.setPreferredSize(new Dimension(reqWidth, reqHeight));
        pack();

        // Request focus in the pane so that key presses can be detected
        pane.requestFocusInWindow();

        // Add a mouse listener to listen for input
        AddListeners();

        // Instantiate a RenderPanel object to render the image
        renderPanel = new RenderPanel(pixelScale);
        pane.add(renderPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    public void AddListeners()
    {
        pane.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) { Runner.getGrid().paint(e.getX() / pixelScale, e.getY() / pixelScale, renderPanel, 2); }
        });

        pane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { Runner.getGrid().paint(e.getX() / pixelScale, e.getY() / pixelScale, renderPanel, 2); }
        });

        pane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { Runner.getGrid().keyPressed(e.getKeyChar()); }
        });
    }

    public RenderPanel getRenderPanel() { return renderPanel; }
}
