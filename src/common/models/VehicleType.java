package common.models;

/**
 * Enum для хранения Типа исходного класса
 */
public enum VehicleType {
    HELICOPTER,
    SHIP,
    CHOPPER;

    static VehicleType[] namesVehicleType = VehicleType.values();
}