/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation.mappanel;

import mapTest.GpsSim2;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author Soren V. Jorgensen.
 */
public class ShowMap extends JComponent implements ActionListener, MouseListener, MouseMotionListener {

    //List of active ships!
    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private Map<Integer, Thread> GpsThread =
	    new HashMap<Integer, Thread>(50);
    private int gpsThreadIndex = 1;
    //Thread to start the aninmator, pause and running booleans
    private BufferedImage img = null;
    private PixelHandler pxhandler = new PixelHandler();
    boolean Zoomflag = false;
    private double longmin;
    private double longmax;
    private double latmin;
    private double latmax;
    private ZoomHandler zoom = new ZoomHandler();
    Timer timer;

    public ShowMap() {
	//Setting up the mapwindow
	timer = new Timer(100, this);
	//initial delay while window gets set up
	timer.setInitialDelay(100);
	timer.start();

	addMouseListener(this);
	for (int i = 0; i < 50; i++) {
	    addShip();
	}

    }

    private void mapRender(Graphics2D g) {
	//Setting up scaling optimizations:

	g.setComposite(AlphaComposite.Src);
	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
		RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		RenderingHints.VALUE_ANTIALIAS_ON);
	//drawing the image and scale it to fit panel:
	g.drawImage(img, 0, 0, getWidth(), getHeight(), null);

    }

    @Override
    public void paintComponent(Graphics g) {
	Graphics2D g2 = (Graphics2D) g.create();
	mapRender(g2);
	shipRender(g2);
    }

    private void shipRender(Graphics g) {

	int f = 1;



	double sx = pxhandler.scalingX(longmin, longmax, getWidth());
	double sy = pxhandler.scalingY(latmin, latmax, getHeight());

	for (Ship ship : ships) {
	    //int py = ship.latitudeToPixels(MapAquaintance.getLatitude(f), getHeight());
	    //int px = ship.longditudeToPixels(MapAquaintance.getLongditude(f), getWidth());
	    int px = pxhandler.zoomLongToPixels(MapAquaintance.getLongditude(f), sx, longmin, longmax);
	    int py = pxhandler.zoomLatToPixels(MapAquaintance.getLatitude(f), sy, latmin, latmax);
	    //System.out.println(px + "and" + py);
	    ship.move(py, px);
	    ship.draw(g);
	    ship.drawCoor(g, getWidth(), getHeight());
	    f++;
	}
    }

    public void addShip() {
	//get current gpsMap.
	int gpsNr = MapAquaintance.getGpsMap();
	//add a Gps.
	MapAquaintance.addGps();
	//Make the gps thread add it to a hashmap and start it.
	Thread thread = new Thread(new GpsSim2(gpsNr));
	GpsThread.put(gpsThreadIndex, thread);
	GpsThread.get(gpsThreadIndex).start();
	ships.add(new Ship(1, gpsNr));
	gpsThreadIndex++;
    }

    public void removeShip(int gpsNr) {
    }

    public void actionPerformed(ActionEvent ae) {
	//paint whatever is in the buffer:
	repaint();
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
	//look through active ships and set coordinate showing for the one
	//clicked at:

	if (e.getButton() == 1) {
	    int f = 1;
	    for (Ship ship : ships) {
		if (ship.nearShip(e.getX(), e.getY()) == true) {
		    if (ship.getShow() == true) {
			ship.changeShow(false);
		    } else {
			ship.changeShow(true);
		    }
		}
		f++;
	    }
	}

	if (e.getButton() == 2) {
	    System.out.println(pxhandler.pixelToLat(getHeight(), e.getY()));
	    System.out.println(pxhandler.pixelToLong(getWidth(), e.getX()));
	    
	}

	if (e.getButton() == 3) {
	    if (Zoomflag == true) {
		longmin = -180;
		longmax = 180;
		latmin = -90;
		latmax = 90;
		try {
		    img = ImageIO.read(new File("Images/mainmap.jpg"));
		} catch (IOException ex) {
		    System.out.println("Images/mainmap.jpg not found");
		}
		Zoomflag = false;

	    } else {
		img = zoom.loadMap(pxhandler.pixelToLong(getWidth(), e.getX()),
			pxhandler.pixelToLat(getHeight(), e.getY()));

		longmin = zoom.getLongLat()[0];
		longmax = zoom.getLongLat()[1];
		latmin = zoom.getLongLat()[2];
		latmax = zoom.getLongLat()[3];

		//System.out.println(longmin + "and" + longmax + "and"+ latmin+"and" + latmax);

		Zoomflag = true;
	    }

	}
	//throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseReleased(MouseEvent e) {
	//throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e) {
	//throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseExited(MouseEvent e) {
	//throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseDragged(MouseEvent me) {
	//throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseMoved(MouseEvent me) {
    }

    /*private static void createAndShowGUI() {
    JFrame f = new JFrame("Animated Graphics");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setSize(200, 200);
    ShowMap mp = new ShowMap();
    f.add(mp);

    f.setVisible(true);
    }

    public static void main(String args[]) {

    for (int i = 0; i < 80; i++) {
    new Thread(new GpsSim2(i)).start();
    }

    Runnable doCreateAndShowGUI = new Runnable() {

    public void run() {

    createAndShowGUI();
    }
    };
    SwingUtilities.invokeLater(doCreateAndShowGUI);
    try {
    Thread.sleep(2);
    } catch (Exception e) {
    }
    }
     * 
     */
}

