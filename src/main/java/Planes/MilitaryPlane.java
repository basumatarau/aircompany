package Planes;

import exception.MilitaryPlaneBuilderInputDataException;
import exception.PlaneBuilderInputDataException;
import models.MilitaryPlaneType;

import java.util.Objects;

public class MilitaryPlane extends Plane{

    private MilitaryPlaneType type;

    protected MilitaryPlane(MilitaryPlaneBuilder builder) {
        super(builder);
        this.type = builder.type;
    }

    public static class MilitaryPlaneBuilder
            extends PlaneBuilder<MilitaryPlane, MilitaryPlaneBuilder> {

        private MilitaryPlaneType type;

        public MilitaryPlaneBuilder type(MilitaryPlaneType type){
            this.type = type;
            return this;
        }

        @Override
        public MilitaryPlane build() throws PlaneBuilderInputDataException {
            buildPlaneDataIntegrityCheck();
            return new MilitaryPlane(this);
        }

        //more sophisticated data integrity check/validation might be needed
        //the method below only checks if the builder object fields have been initialized before build execution
        @Override
        protected void buildPlaneDataIntegrityCheck() throws PlaneBuilderInputDataException {
            super.buildPlaneDataIntegrityCheck();
            if(this.type == null){
                throw new MilitaryPlaneBuilderInputDataException(
                        "data integrity violation: military plane model has not been initialized");
            }
        }
    }

    public MilitaryPlaneType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "MilitaryPlane{" +
                "type=" + type +
                ", model='" + model + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", maximumFlightDistance=" + maximumFlightDistance +
                ", maximumLoadCapacity=" + maximumLoadCapacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MilitaryPlane)) return false;
        if (!super.equals(o)) return false;
        MilitaryPlane that = (MilitaryPlane) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
