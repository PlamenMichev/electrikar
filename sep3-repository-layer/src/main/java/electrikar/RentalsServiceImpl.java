package electrikar;

import electrikar.database.dao.SqlRentalDao;
import io.grpc.stub.StreamObserver;
import org.electrikar.grpc.*;

import java.sql.Timestamp;

public class RentalsServiceImpl
    extends RentalsServiceGrpc.RentalsServiceImplBase
{
  @Override
  public void create(CreateRentalRequest request, StreamObserver<CreateRentalResponse> responseObserver)
  {
    try
    {
      var rental = new Rental(request.getId(), request.getCarRegNumber(), (int)request.getUserId(), new Timestamp(request.getStartDate()), new Timestamp(request.getEndDate()));
      var rentalDao = SqlRentalDao.getInstance();
      var newRental = rentalDao.createRental(rental.getCarRegNumber(), rental.getUserId(), rental.getStartDate(), rental.getEndDate());

      var response = CreateRentalResponse.newBuilder().setId(newRental.getId()).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }


  @Override
  public void updateRental(UpdateRentalRequest request, StreamObserver<UpdateRentalResponse> responseObserver)
  {
    try
    {
      var rental = new Rental(request.getId(), request.getCarRegNumber(), (int)request.getUserId(), new Timestamp(request.getStartDate()), new Timestamp(request.getEndDate()));
      var rentalDao = SqlRentalDao.getInstance();
      rentalDao.updateRental(rental);

      var response = UpdateRentalResponse.newBuilder().setId(rental.getId()).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteRental(DeleteRentalRequest request, StreamObserver<DeleteRentalResponse> responseObserver)
  {
    try
    {
      var rentalDao = SqlRentalDao.getInstance();
      rentalDao.deleteRentalById(request.getId());

      var response = DeleteRentalResponse.newBuilder().setId(request.getId()).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void getAllRentals(Empty request, StreamObserver<RentalsList> responseObserver)
  {
    try
    {
      var rentalDao = SqlRentalDao.getInstance();
      var rentals = rentalDao.getAllRentals();
      var response = RentalsList.newBuilder();
      for (var rental : rentals)
      {
        var rentalProto = CreateRentalResponse.newBuilder()
            .setId(rental.getId())
            .setCarRegNumber(rental.getCarRegNumber())
            .setUserId(rental.getUserId())
            .setStartDate(rental.getStartDate().getTime())
            .setEndDate(rental.getEndDate().getTime())
            .build();

        response.addRentals(rentalProto);
      }
      responseObserver.onNext(response.build());
      responseObserver.onCompleted();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }



}