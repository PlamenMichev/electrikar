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
                    0, request.getImage().toByteArray());
            var carDao = SqlCarDao.getInstance();
            var newCar = carDao.createCar(car.getReg_number(), car.getColor(), car.getMake(), car.getModel(), car.getType(), car.getPrice(), car.getImage());

            CreateCarResponse response = CreateCarResponse.newBuilder().setRegNumber(newCar.getReg_number()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public void updateCar(UpdateCarRequest request,
        StreamObserver<UpdateCarResponse> responseObserver)
    {
        try {
            var car = new Car(request.getRegNumber(),
                    CarColor.valueOf((int)request.getColor()),
                    CarMake.valueOf((int)request.getMake()),
                    CarModel.valueOf((int)request.getModel()),
                    CarType.valueOf((int)request.getType()),
                    0, request.getImage().toByteArray());
            var carDao = SqlCarDao.getInstance();
            carDao.updateCar(car);

            var response = UpdateCarResponse.newBuilder().setRegNumber(car.getReg_number()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    @Override
    public void getAllCars(Empty request, StreamObserver<CarsList> responseObserver) {
        try {
            var carDao = SqlCarDao.getInstance();
            var cars = carDao.getAll();

            var carsList = CarsList.newBuilder();
            for (var car : cars) {
                var base64Encoded = Base64.getEncoder().encodeToString(car.getImage());
                var carProto = CreateCarResponse.newBuilder()
                        .setRegNumber(car.getReg_number())
                        .setColor(car.getColor().ordinal())
                        .setMake(car.getMake().ordinal())
                        .setModel(car.getModel().ordinal())
                        .setType(car.getType().ordinal())
                        .setImage(base64Encoded)
                        .build();
                carsList.addCars(carProto);
            }

            responseObserver.onNext(carsList.build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

@Override
public void getCar(GetCarRequest request, StreamObserver<GetCarResponse> responseObserver)
{
    try
    {
        var carDao = SqlCarDao.getInstance();
        var car = carDao.getCarByReg(request.getRegNumber());

        if (car != null)
        {
            var base64Encoded = Base64.getEncoder()
                .encodeToString(car.getImage());
            var carProto = GetCarResponse.newBuilder()
                .setRegNumber(car.getReg_number())
                .setColor(car.getColor().ordinal())
                .setMake(car.getMake().ordinal())
                .setModel(car.getModel().ordinal())
                .setType(car.getType().ordinal()).setImage(base64Encoded)
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

