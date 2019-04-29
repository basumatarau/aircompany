import Planes.ExperimentalPlane;
import exception.PlaneBuilderInputDataException;
import models.DisclosureLevel;
import models.ExperimentalPlaneType;
import models.MilitaryPlaneType;
import org.testng.Assert;
import org.testng.annotations.Test;
import Planes.MilitaryPlane;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static List<Plane> planes;

    static {
        try {
            planes = Arrays.asList(
                    new PassengerPlane.PassengerPlaneBuilder()
                            .passengerCapacity(164)
                            .model("Boeing-737")
                            .maxSpeed(900)
                            .maxFlightDistance(12000)
                            .maxLoadCapacity(60500)
                            .build(),
                    new PassengerPlane.PassengerPlaneBuilder()
                            .passengerCapacity(192)
                            .model("Boeing-737-800")
                            .maxSpeed(940)
                            .maxFlightDistance(12300)
                            .maxLoadCapacity(63870)
                            .build(),
                    new PassengerPlane.PassengerPlaneBuilder()
                            .passengerCapacity(242)
                            .model("Boeing-747")
                            .maxSpeed(980)
                            .maxFlightDistance(16100)
                            .maxLoadCapacity(70500)
                            .build(),
                    new PassengerPlane.PassengerPlaneBuilder()
                            .passengerCapacity(188)
                            .model("Airbus A320")
                            .maxSpeed(930)
                            .maxFlightDistance(11800)
                            .maxLoadCapacity(65500)
                            .build(),
                    new PassengerPlane.PassengerPlaneBuilder()
                            .passengerCapacity(222)
                            .model("Airbus A330")
                            .maxSpeed(990)
                            .maxFlightDistance(14800)
                            .maxLoadCapacity(80500)
                            .build(),
                    new PassengerPlane.PassengerPlaneBuilder()
                            .passengerCapacity(64)
                            .model("Embraer 190")
                            .maxSpeed(870)
                            .maxFlightDistance(8100)
                            .maxLoadCapacity(30800)
                            .build(),
                    new PassengerPlane.PassengerPlaneBuilder()
                            .passengerCapacity(196)
                            .model("Bombardier CS300")
                            .maxSpeed(920)
                            .maxFlightDistance(11000)
                            .maxLoadCapacity(60700)
                            .build(),
                    new PassengerPlane.PassengerPlaneBuilder()
                            .passengerCapacity(140)
                            .model("Sukhoi Superjet 100")
                            .maxSpeed(870)
                            .maxFlightDistance(11500)
                            .maxLoadCapacity(50500)
                            .build(),
                    new MilitaryPlane.MilitaryPlaneBuilder()
                            .type(MilitaryPlaneType.BOMBER)
                            .model("B-1B Lancer")
                            .maxSpeed(1050)
                            .maxFlightDistance(21000)
                            .maxLoadCapacity(80000)
                            .build(),
                    new MilitaryPlane.MilitaryPlaneBuilder()
                            .type(MilitaryPlaneType.BOMBER)
                            .model("B-2 Spirit")
                            .maxSpeed(1030)
                            .maxFlightDistance(22000)
                            .maxLoadCapacity(70000)
                            .build(),
                    new MilitaryPlane.MilitaryPlaneBuilder()
                            .type(MilitaryPlaneType.BOMBER)
                            .model("B-52 Stratofortress")
                            .maxSpeed(1000)
                            .maxFlightDistance(20000)
                            .maxLoadCapacity(80000)
                            .build(),
                    new MilitaryPlane.MilitaryPlaneBuilder()
                            .type(MilitaryPlaneType.FIGHTER)
                            .model("F-15")
                            .maxSpeed(1500)
                            .maxFlightDistance(12000)
                            .maxLoadCapacity(10000)
                            .build(),
                    new MilitaryPlane.MilitaryPlaneBuilder()
                            .type(MilitaryPlaneType.FIGHTER)
                            .model("F-22")
                            .maxSpeed(1550)
                            .maxFlightDistance(13000)
                            .maxLoadCapacity(11000)
                            .build(),
                    new MilitaryPlane.MilitaryPlaneBuilder()
                            .type(MilitaryPlaneType.TRANSPORTER)
                            .model("C-130 Hercules")
                            .maxSpeed(650)
                            .maxFlightDistance(5000)
                            .maxLoadCapacity(110000)
                            .build(),
                    new ExperimentalPlane.ExperimentalPlaneBuilder()
                            .disclosureLevel(DisclosureLevel.SECRET)
                            .type(ExperimentalPlaneType.HIGH_ALTITUDE)
                            .model("Bell X-14")
                            .maxSpeed(277)
                            .maxFlightDistance(482)
                            .maxLoadCapacity(500).build(),
                    new ExperimentalPlane.ExperimentalPlaneBuilder()
                            .disclosureLevel(DisclosureLevel.TOP_SECRET)
                            .type(ExperimentalPlaneType.VTOL)
                            .model("Ryan X-13 Vertijet")
                            .maxSpeed(560)
                            .maxFlightDistance(307)
                            .maxLoadCapacity(500).build()
            );
        } catch (PlaneBuilderInputDataException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static PassengerPlane planeWithMaxPassengerCapacity;

    static {
        try {
            planeWithMaxPassengerCapacity = new PassengerPlane.PassengerPlaneBuilder()
                    .passengerCapacity(242)
                    .model("Boeing-747")
                    .maxSpeed(980)
                    .maxFlightDistance(16100)
                    .maxLoadCapacity(70500)
                    .build();
        } catch (PlaneBuilderInputDataException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTransportMilitaryPlanes() {
        Assert.assertFalse(
                new Airport(planes).getTransportMilitaryPlanes()
                        .stream()
                        .anyMatch(tmp -> !tmp.getType().equals(MilitaryPlaneType.TRANSPORTER))
        );
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        Airport airport = new Airport(planes);
        Assert.assertEquals(
                airport.getPassengerPlaneWithMaxPassengersCapacity(), planeWithMaxPassengerCapacity);
    }

    @Test
    public void testSortByMaximumLoadCapacity() {
        Airport airport = new Airport(planes);
        airport.sortByMaximumLoadCapacity();

        List<? extends Plane> sortedPlanes = airport.getPlanes();
        for (int i = 1; i < sortedPlanes.size(); i++) {
            Assert.assertTrue(sortedPlanes.get(i - 1).getMinimumLoadCapacity()
                    > sortedPlanes.get(i).getMinimumLoadCapacity()
            );
        }
    }

    @Test
    public void testHasAtLeastOneBomberInMilitaryPlanes() {
        Assert.assertTrue(
                new Airport(planes)
                        .getBomberMilitaryPlanes()
                        .stream()
                        .anyMatch(p -> p.getType().equals(MilitaryPlaneType.BOMBER))
        );
    }

    @Test
    public void testAllExperimentalPlanesAreClassified() {
        Assert.assertFalse(
                new Airport(planes)
                        .getExperimentalPlanes()
                        .stream()
                        .anyMatch(p -> p.getDisclosureLevel().equals(DisclosureLevel.UNCLASSIFIED))
        );
    }
}
