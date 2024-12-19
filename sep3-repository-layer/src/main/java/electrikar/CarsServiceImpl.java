package electrikar;

import electrikar.database.dao.SqlCarDao;
import electrikar.enums.CarColor;
import electrikar.enums.CarMake;
import electrikar.enums.CarModel;
import electrikar.enums.CarType;
import io.grpc.stub.StreamObserver;
import org.electrikar.grpc.*;

import java.sql.SQLException;
import java.util.Base64;

public class CarsServiceImpl extends CarsServiceGrpc.CarsServiceImplBase {

    /**
     * Create a new car
     * @param request
     * @param responseObserver
     */
    @Override
    public void create(
            CreateCarRequest request, StreamObserver<CreateCarResponse> responseObserver) {
        System.out.println("Received request: " + request.getRegNumber());
        System.out.println("Received request: " + request.getColor());
        System.out.println("Received request: " + request.getMake());
        System.out.println("Received request: " + request.getModel());
        System.out.println("Received request: " + request.getType());
        System.out.println("Received request: " + request.getImage());

        try {
            var car = new Car(request.getRegNumber(),
                    CarColor.valueOf((int)request.getColor()),
                    CarMake.valueOf((int)request.getMake()),
                    CarModel.valueOf((int)request.getModel()),
                    CarType.valueOf((int)request.getType()),
                    0, request.getImage(), false);
            var carDao = SqlCarDao.getInstance();
            var newCar = carDao.createCar(car.getReg_number(), car.getColor(), car.getMake(), car.getModel(), car.getType(), car.getPrice(), car.getImage(), car.checkRent());

            CreateCarResponse response = CreateCarResponse.newBuilder().setRegNumber(newCar.getReg_number()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update a car
     * @param request
     * @param responseObserver
     */
    @Override public void updateCar(UpdateCarRequest request,
        StreamObserver<UpdateCarResponse> responseObserver)
    {
        try {
            var car = new Car(request.getRegNumber(),
                    CarColor.valueOf((int)request.getColor()),
                    CarMake.valueOf((int)request.getMake()),
                    CarModel.valueOf((int)request.getModel()),
                    CarType.valueOf((int)request.getType()),
                    0, request.getImage(), false);
            var carDao = SqlCarDao.getInstance();
            carDao.updateCar(request.getOldRegNumber(), car);

            var response = UpdateCarResponse.newBuilder().setRegNumber(car.getReg_number()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a car
     * @param request
     * @param responseObserver
     */
    @Override public void deleteCar(DeleteCarRequest request,
        StreamObserver<DeleteCarResponse> responseObserver)
    {
        try {
            var carDao = SqlCarDao.getInstance();
            carDao.deleteCarByReg(request.getRegNumber());

            var response = DeleteCarResponse.newBuilder().setRegNumber(request.getRegNumber()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();}
    }

    /**
     * Get all cars
     * @param request
     * @param responseObserver
     */
    @Override
    public void getAllCars(Empty request, StreamObserver<CarsList> responseObserver) {
        try {
            var carDao = SqlCarDao.getInstance();
            var cars = carDao.getAll();

            var carsList = CarsList.newBuilder();
            for (var car : cars) {
                var carProto = CreateCarResponse.newBuilder()
                        .setRegNumber(car.getReg_number())
                        .setColor(car.getColor().ordinal())
                        .setMake(car.getMake().ordinal())
                        .setModel(car.getModel().ordinal())
                        .setType(car.getType().ordinal())
                        .setImage(car.getImage())
                        .build();
                carsList.addCars(carProto);
            }

            responseObserver.onNext(carsList.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a car by its registration number
     * @param request
     * @param responseObserver
     */
@Override
public void getCar(GetCarRequest request, StreamObserver<GetCarResponse> responseObserver)
{
    try
    {
        var carDao = SqlCarDao.getInstance();
        var car = carDao.getCarByReg(request.getRegNumber());

        if (car != null)
        {
            var carProto = GetCarResponse.newBuilder()
                .setRegNumber(car.getReg_number())
                .setColor(car.getColor().ordinal())
                .setMake(car.getMake().ordinal())
                .setModel(car.getModel().ordinal())
                .setType(car.getType().ordinal()).setImage(car.getImage())
                .build();
            responseObserver.onNext(carProto);
        }
        else
        {
            responseObserver.onNext(GetCarResponse.newBuilder().build());
        }
        responseObserver.onCompleted();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
}


}

