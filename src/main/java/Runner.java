import Planes.MilitaryPlane;
import exception.PlaneBuilderInputDataException;
import models.MilitaryPlaneType;
import Planes.PassengerPlane;
import Planes.Plane;

import java.util.Arrays;
import java.util.List;

public class Runner {

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
                            .build()
                );
        } catch (PlaneBuilderInputDataException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void main(String[] args) {
        Airport airport = new Airport(planes);
        Airport militaryAirport = new Airport(airport.getMilitaryPlanes());
        Airport passengerAirport = new Airport(airport.getPassengerPlanes());

        System.out.println("Military airport sorted by max distance: " + militaryAirport
                .sortByMaximumDistance()
                .toString());

        System.out.println("Passenger airport sorted by max speed: " + passengerAirport
                .sortByMaximumSpeed()
                .toString());

        System.out.println("Plane with max passenger capacity: " + passengerAirport.getPassengerPlaneWithMaxPassengersCapacity());
    }
}
