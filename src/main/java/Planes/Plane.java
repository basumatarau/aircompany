package Planes;

import exception.PlaneBuilderInputDataException;

import java.util.Objects;

abstract public class Plane {
    String model;
    protected int maxSpeed;
    protected int maximumFlightDistance;
    protected int maximumLoadCapacity;

    protected Plane(PlaneBuilder builder) {
        this.model = builder.model;
        this.maxSpeed = builder.maxSpeed;
        this.maximumFlightDistance = builder.maxFlightDistance;
        this.maximumLoadCapacity = builder.maxLoadCapacity;
    }

    @SuppressWarnings("unchecked")
    public abstract static class PlaneBuilder <T extends Plane, B extends PlaneBuilder<T, B>>{
        private String model;
        private int maxSpeed;
        private int maxFlightDistance;
        private int maxLoadCapacity;

        public PlaneBuilder(){}

        public B model(final String model){
            this.model = model;
            return ((B) this);
        }
        public B maxSpeed(int maxSpeed){
            this.maxSpeed = maxSpeed;
            return ((B) this);
        }

        public B maxFlightDistance(int maxFlightCapacity){
            this.maxFlightDistance = maxFlightCapacity;
            return ((B) this);
        }

        public B maxLoadCapacity(int maxLoadCapacity){
            this.maxLoadCapacity = maxLoadCapacity;
            return ((B) this);
        }

        public abstract T build() throws PlaneBuilderInputDataException;

        //simple builder input data check before build method execution
        // - some advanced input data validation might be needed
        protected void buildPlaneDataIntegrityCheck() throws PlaneBuilderInputDataException {
            if(this.model == null ||
                    this.maxSpeed == 0 ||
                    this.maxFlightDistance == 0 ||
                    this.maxLoadCapacity == 0){
                throw new PlaneBuilderInputDataException("plane builder input data integrity violation");
            }
        }
    }

    public String getModel() {
        return model;
    }

    public int getMaximumSpeed() {
        return maxSpeed;
    }

    public int getMaximumFlightDistance() {
        return maximumFlightDistance;
    }

    public int getMinimumLoadCapacity() {
        return maximumLoadCapacity;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", maximumFlightDistance=" + maximumFlightDistance +
                ", maximumLoadCapacity=" + maximumLoadCapacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        Plane plane = (Plane) o;
        return maxSpeed == plane.maxSpeed &&
                maximumFlightDistance == plane.maximumFlightDistance &&
                maximumLoadCapacity == plane.maximumLoadCapacity &&
                Objects.equals(model, plane.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, maxSpeed, maximumFlightDistance, maximumLoadCapacity);
    }
}
