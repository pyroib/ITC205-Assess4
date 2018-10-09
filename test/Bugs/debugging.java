package Bugs;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import hotel.HotelHelper;
import hotel.checkout.CheckoutCTL;
import hotel.credit.CreditCard;
import hotel.credit.CreditCardType;
import hotel.entities.Booking;
import hotel.entities.Guest;
import hotel.entities.Hotel;
import hotel.entities.Room;
import hotel.entities.RoomType;
import hotel.entities.ServiceCharge;
import hotel.entities.ServiceType;
import hotel.service.RecordServiceCTL;

class debugging {
	
	CreditCard creditCard;
	CreditCardType cardType;
	int cardNumber; 
	int cardCcv; 
	
	
	String name;
	String address;
	int phoneNumber;
	
	int stayLength;
	int numberOfOccupants;
	
    String ArrivalDay;
    String ArrivalMonth;
    String ArrivalYear;
    
    
    int roomId;
    RoomType roomType;
    
    ServiceType roomServiceType;
    Double serviceCost;
    
    
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    
    
    @BeforeEach
    void setUp() throws Exception {

        cardType = CreditCardType.VISA;
        cardNumber = 1;
        cardCcv = 1;

		numberOfOccupants = 1;
        name = "Daffodil";
        address = "6 Tulip Cres";
        phoneNumber = 12345678;
        stayLength = 2;
        
        ArrivalDay = "10";
        ArrivalMonth = "10";
        ArrivalYear = "2018";
        
        roomId = 101;
        roomType = RoomType.SINGLE;
        
        creditCard = new CreditCard(cardType, cardNumber, cardCcv);
        
        roomServiceType = ServiceType.ROOM_SERVICE;
        serviceCost = 100.00;
        
    }
    
	@Test
	void bug1() throws ParseException {
		//arrange
		Guest guest = new Guest(name, address, phoneNumber);
		Room room = new Room(roomId, roomType);
		Date date = format.parse(ArrivalDay+"-"+ArrivalMonth+"-"+ArrivalYear);
		Booking booking = new Booking(guest, room, date, stayLength, numberOfOccupants, creditCard);
		
		//act
		booking.addServiceCharge(roomServiceType, serviceCost);
		
        //assert
		List<ServiceCharge> charges = booking.getCharges();
		for (ServiceCharge sc : charges) {
			assertTrue(sc.getCost() != 0);
		}
		
		
	}

}
