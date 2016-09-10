package com.unimelb.swen30006.metromadness.stations;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.unimelb.swen30006.metromadness.passengers.Passenger;
import com.unimelb.swen30006.metromadness.routers.PassengerRouter;
import com.unimelb.swen30006.metromadness.tracks.Line;
import com.unimelb.swen30006.metromadness.trains.Train;

public class Station {

	public static final int PLATFORMS = 2;

	public Point2D.Float position;
	public static final float RADIUS = 6;
	public static final int NUM_CIRCLE_STATMENTS = 100;
	public static final int MAX_LINES = 3;
	private String name;
	protected ArrayList<Line> lines;
	private ArrayList<Train> trains;
	public static final float DEPARTURE_TIME = 2;
	protected PassengerRouter router;
	protected float maxPassengers; // NEW
	protected Passenger[] passengers; // NEW

	public Station(float x, float y, PassengerRouter router, String name, float maxPassengers) {
		this.name = name;
		this.router = router;
		this.position = new Point2D.Float(x, y);
		this.lines = new ArrayList<Line>();
		this.trains = new ArrayList<Train>();
		this.maxPassengers = maxPassengers;
	}

	public void registerLine(Line l) {
		this.lines.add(l);
	}

	// public void render(ShapeRenderer renderer){
	// float radius = RADIUS;
	// for(int i=0; (i<this.lines.size() && i<MAX_LINES); i++){
	// Line l = this.lines.get(i);
	// renderer.setColor(l.lineColour);
	// renderer.circle(this.position.x, this.position.y, radius,
	// NUM_CIRCLE_STATMENTS);
	// radius = radius - 1;
	// }
	//
	// // Calculate the percentage
	// float t = this.trains.size()/(float)PLATFORMS;
	// Color c = Color.WHITE.cpy().lerp(Color.DARK_GRAY, t);
	// renderer.setColor(c);
	// renderer.circle(this.position.x, this.position.y, radius,
	// NUM_CIRCLE_STATMENTS);
	// }

	public void enter(Train t) throws Exception {
		if (getTrains().size() >= PLATFORMS) {
			throw new Exception();
		} else {
			this.getTrains().add(t);
		}
	}

	public void depart(Train t) throws Exception {
		if (this.getTrains().contains(t)) {
			this.getTrains().remove(t);
		} else {
			throw new Exception();
		}
	}

	public boolean canEnter(Line l, Train t) throws Exception {
		return getTrains().size() < PLATFORMS;
	}

	// Returns departure time in seconds
	public float getDepartureTime() {
		return DEPARTURE_TIME;
	}

	public boolean shouldLeave(Passenger p) {
		return this.router.shouldLeave(this, p);
	}

	@Override
	public String toString() {
		return "Station [position=" + position + ", name=" + getName() + ", trains=" + getTrains().size() + ", router=" + router
				+ "]";
	}

	public ArrayList<Line> getLines() {
		return lines;
	}

	public float getMaxPassengers() {
		return maxPassengers;
	}

	public void assignPassengers(Passenger[] passengers) {
		this.passengers = passengers;
	}

	public boolean canPassThrough(Line l, Train t) throws Exception {
		return getTrains().size() < PLATFORMS;
	}

	public void passThrough(Train t) {

	}

	public ArrayList<Train> getTrains() {
		return trains;
	}

	public String getName() {
		return name;
	}

}
