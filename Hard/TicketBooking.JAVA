class TicketBookingSystem {
    private int availableSeats = 10;

    public synchronized void bookSeat(String customerType) {
        if (availableSeats > 0) {
            System.out.println(customerType + " booking in progress...");
            try {
                Thread.sleep(100); // Simulate time taken to book the seat
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            availableSeats--;
            System.out.println(customerType + " successfully booked a seat. Remaining seats: " + availableSeats);
        } else {
            System.out.println("No seats available for " + customerType + "!");
        }
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}

class RegularBookingThread extends Thread {
    private TicketBookingSystem bookingSystem;

    public RegularBookingThread(TicketBookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
        setPriority(Thread.NORM_PRIORITY);  // Default priority for regular customers
    }

    @Override
    public void run() {
        while (bookingSystem.getAvailableSeats() > 0) {
            bookingSystem.bookSeat("Regular Customer");
        }
    }
}

class VIPBookingThread extends Thread {
    private TicketBookingSystem bookingSystem;

    public VIPBookingThread(TicketBookingSystem bookingSystem) {
        this.bookingSystem = bookingSystem;
        setPriority(Thread.MAX_PRIORITY);  // High priority for VIP customers
    }

    @Override
    public void run() {
        while (bookingSystem.getAvailableSeats() > 0) {
            bookingSystem.bookSeat("VIP Customer");
        }
    }
}

public class TicketBookingSystemApp {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();

        // Create VIP and regular booking threads
        VIPBookingThread vipBooking = new VIPBookingThread(bookingSystem);
        RegularBookingThread regularBooking = new RegularBookingThread(bookingSystem);

        // Start the threads
        vipBooking.start();
        regularBooking.start();
    }
}
