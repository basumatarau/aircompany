package Planes;

import exception.PassengerPlaneBuilderInputDataException;
import exception.PlaneBuilderInputDataException;

import java.util.Objects;

public class PassengerPlane extends Plane{

    private int passengersCapacity;

    protected PassengerPlane(PassengerPlaneBuilder builder) {
        super(builder);
        this.passengersCapacity = builder.passengersCapacity;
    }

    public static class PassengerPlaneBuilder
            extends PlaneBuilder<PassengerPlane, PassengerPlaneBuilder>{
        private int passengersCapacity;

        public PassengerPlaneBuilder passengerCapacity(int passengersCapacity){
            this.passengersCapacity = passengersCapacity;
            return this;
        }

        @Override
        public PassengerPlane build() throws PlaneBuilderInputDataException {
            buildPlaneDataIntegrityCheck();
            return new PassengerPlane(this);
        }

        //more sophisticated data integrity check/validation might be needed
        //the method below only checks if the builder object fields have been initialized before build execution
        @Override
        protected void buildPlaneDataIntegrityCheck() throws PlaneBuilderInputDataException {
            super.buildPlaneDataIntegrityCheck();
            if(passengersCapacity == 0){
                throw new PassengerPlaneBuilderInputDataException(
                        "data integrity violation: passengers capacity has not been initialized for passenger plane");
            }
        }
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    @Override
    public String toString() {
        return super.toString().replace("}",
                ", passengersCapacity=" + passengersCapacity +
                '}');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerPlane)) return false;
        if (!super.equals(o)) return false;
        PassengerPlane plane = (PassengerPlane) o;
        return passengersCapacity == plane.passengersCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengersCapacity);
    }
}
