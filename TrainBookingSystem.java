import java.util.*;

class Train {
    int trainID;
    String journeyFrom;
    String journeyTo;
    int distanceBetween;
    int costOfTicket;
    HashMap<String, Integer> bookingSlot;

    Train(int id, String from, String to, int totalDistance) {
        this.trainID = id;
        this.journeyFrom = from;
        this.journeyTo = to;
        this.distanceBetween = totalDistance;
        this.bookingSlot = new HashMap<String, Integer>();
        this.costOfTicket = totalDistance;
    }
}

class Data {
    List<Train> storeTrainData;
    List<DayWiseTrainbooking> dayWiseBook;

    Data() {
        this.storeTrainData = new ArrayList<Train>();
        this.dayWiseBook = new ArrayList<DayWiseTrainbooking>();
    }

    void print() {
        for (Train it : storeTrainData) {
            System.out.println(it.trainID);
        }
    }
}

class DayWiseTrainbooking {
    String date;
    Train daywiseBook;

    DayWiseTrainbooking(int id, String date, Data data) {
        this.date = date;
        for (Train it : data.storeTrainData) {
            if (id == it.trainID) {
                System.out.println("match");
                this.daywiseBook = it;
            }
        }
    }
}

public class TrainBookingSystem {

    public static Train trainCheck(String from, String to, Data data) {

        for (Train it : data.storeTrainData) {
            System.out.println("In");
            if ((from == it.journeyFrom) && (to == it.journeyTo)) {
                return it;
            }
        }
        return null;
    }

    public static void seatAvailable(Data data, String coach, int seatCount) {
        for (DayWiseTrainbooking it : data.dayWiseBook) {
            if (coach.equals("SL")) {
                System.out.println("hello");
                if (it.daywiseBook.bookingSlot.containsKey("S1") && it.daywiseBook.bookingSlot.get("S1") >= seatCount) {
                    int temp2 = it.daywiseBook.bookingSlot.get("S1") - seatCount;
                    it.daywiseBook.bookingSlot.put("S1", temp2);
                } else if (it.daywiseBook.bookingSlot.containsKey("S2")
                        && it.daywiseBook.bookingSlot.get("S2") >= seatCount) {
                    int temp2 = it.daywiseBook.bookingSlot.get("S2") - seatCount;
                    it.daywiseBook.bookingSlot.put("S2", temp2);
                } else {
                    System.out.println("No seat Available");
                    return;
                }
            } else if (coach == "3A") {
                if (it.daywiseBook.bookingSlot.containsKey("B1")) {
                    if (it.daywiseBook.bookingSlot.get("B1") >= seatCount) {
                        int temp2 = it.daywiseBook.bookingSlot.get("B1") - seatCount;
                        it.daywiseBook.bookingSlot.put("B1", temp2);
                    } else {
                        System.out.println("No seat Available");
                        return;
                    }
                }
            } else if (coach == "2A") {
                if (it.daywiseBook.bookingSlot.containsKey("A1")) {
                    if (it.daywiseBook.bookingSlot.get("A1") >= seatCount) {
                        int temp2 = it.daywiseBook.bookingSlot.get("A1") - seatCount;
                        it.daywiseBook.bookingSlot.put("A1", temp2);
                    } else {
                        System.out.println("No seat Available");
                        return;
                    }
                }
            } else if (coach == "1A") {
                if (it.daywiseBook.bookingSlot.containsKey("H1")) {
                    if (it.daywiseBook.bookingSlot.get("H1") >= seatCount) {
                        int temp2 = it.daywiseBook.bookingSlot.get("H1") - seatCount;
                        it.daywiseBook.bookingSlot.put("H1", temp2);
                    } else {
                        System.out.println("No seat Available");
                        return;
                    }
                }
            } else {
                System.out.println("No seat Available");
                return;
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Data storeData = new Data();

        int trainCount = Integer.parseInt(sc.nextLine());
        while (trainCount > 0) {
            String[] temp = sc.nextLine().split(" ");
            String from[] = temp[1].split("-");
            String to[] = temp[2].split("-");
            int totalDistance = Integer.parseInt(to[1]) - Integer.parseInt(from[1]);
            Train trainData = new Train(Integer.parseInt(temp[0]), from[0], to[0], totalDistance);

            String[] temp1 = sc.nextLine().split(" ");
            for (int i = 1; i < temp1.length; i++) {
                String seat[] = temp1[i].split("-");
                trainData.bookingSlot.put(seat[0], Integer.parseInt(seat[1]));
            }

            // Printing the Train Data
            // storeData.storeTrainData.add(trainData);
            trainCount--;
        }

        storeData.print();

        while (true) {
            String[] inputBookingRequest = sc.nextLine().split(" ");
            if (trainCheck(inputBookingRequest[0], inputBookingRequest[1], storeData) == null) {
                Train train1 = trainCheck(inputBookingRequest[0], inputBookingRequest[1], storeData);
                DayWiseTrainbooking daywise = new DayWiseTrainbooking(17726,
                        inputBookingRequest[2],
                        storeData);
                storeData.dayWiseBook.add(daywise);
                seatAvailable(storeData, inputBookingRequest[3], Integer.parseInt(inputBookingRequest[4]));
            } else {
                System.out.println("Train is not Available");
            }
        }
    }
}