package Planes;

import exception.ExperimentalPlaneBuilderException;
import exception.PlaneBuilderInputDataException;
import models.DisclosureLevel;
import models.ExperimentalPlaneType;

public class ExperimentalPlane extends Plane{

    private ExperimentalPlaneType type;
    private DisclosureLevel classificationLevel;

    protected ExperimentalPlane(ExperimentalPlaneBuilder builder) {
        super(builder);
        this.type = builder.type;
        this.classificationLevel = builder.level;
    }

    public static class ExperimentalPlaneBuilder
            extends PlaneBuilder<ExperimentalPlane, ExperimentalPlaneBuilder>{
        private ExperimentalPlaneType type;
        private DisclosureLevel level;

        public ExperimentalPlaneBuilder type(ExperimentalPlaneType type){
            this.type = type;
            return this;
        }

        public ExperimentalPlaneBuilder disclosureLevel(DisclosureLevel level){
            this.level = level;
            return this;
        }

        @Override
        public ExperimentalPlane build() throws PlaneBuilderInputDataException {
            buildPlaneDataIntegrityCheck();
            return new ExperimentalPlane(this);
        }

        //more sophisticated data integrity check/validation might be needed
        //the method below only checks if the builder object fields have been initialized before build execution
        @Override
        protected void buildPlaneDataIntegrityCheck() throws PlaneBuilderInputDataException {
            super.buildPlaneDataIntegrityCheck();
            if(this.type == null || this.level == null){
                throw new ExperimentalPlaneBuilderException(
                        "data integrity violation: experimental plane init data has not been initialized");
            }
        }
    }

    public DisclosureLevel getClassificationLevel(){
        return classificationLevel;
    }

    public void setClassificationLevel(DisclosureLevel classificationLevel){
        this.classificationLevel = classificationLevel;
    }

    @Override
    public String toString() {
        return "ExperimentalPlane{" +
                "model='" + model + '\'' +
                '}';
    }
}
