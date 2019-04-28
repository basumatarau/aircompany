import Planes.ExperimentalPlane;
import models.MilitaryPlaneType;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<? extends Plane> getPlanes() {
        return planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {
        return planes.stream()
                .filter(p -> p instanceof PassengerPlane)
                .map(PassengerPlane.class::cast)
                .collect(toList());
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return planes.stream()
                .filter(p -> p instanceof MilitaryPlane)
                .map(MilitaryPlane.class::cast)
                .collect(toList());
    }

    //the method returns null if there is no passenger planes at hand (exception should be thrown... probably)
    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return planes
                .stream()
                .filter(p -> p instanceof PassengerPlane)
                .map(PassengerPlane.class::cast)
                .max(Comparator.comparingInt(PassengerPlane::getPassengersCapacity))
                .orElse(null);
    }

    public List<MilitaryPlane> getTransportMilitaryPlanes() {
        return planes.stream()
                .filter(p -> p instanceof MilitaryPlane)
                .map(MilitaryPlane.class::cast)
                .filter(p -> p.getType().equals(MilitaryPlaneType.TRANSPORTER))
                .collect(toList());
    }

    public List<MilitaryPlane> getBomberMilitaryPlanes() {
        return planes.stream()
                .filter(p -> p instanceof MilitaryPlane)
                .map(MilitaryPlane.class::cast)
                .filter(p -> p.getType().equals(MilitaryPlaneType.BOMBER))
                .collect(toList());

    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return planes.stream()
                .filter(p -> p instanceof ExperimentalPlane)
                .map(ExperimentalPlane.class::cast)
                .collect(toList());
    }

    public Airport sortByMaximumDistance() {
        Collections.sort(planes, Comparator.comparingInt(Plane::getMaximumFlightDistance));
        return this;
    }

    public Airport sortByMaximumSpeed() {
        Collections.sort(planes, Comparator.comparingInt(Plane::getMaximumSpeed));
        return this;
    }

    public Airport sortByMaximumLoadCapacity() {
        Collections.sort(planes, Comparator.comparingInt(Plane::getMinimumLoadCapacity));
        return this;
    }

    private void print(Collection<? extends Plane> collection) {
        planes.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "Planes=" + planes.toString() +
                '}';
    }
}
