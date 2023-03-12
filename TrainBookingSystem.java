import java.util.*;

class Train {
    int trainID;
    String journeyFrom;
    String journeyTo;
    int distanceBetween;
    int costOfTicket;
    HashMap<String, Integer> bookingSlot;
    HashMap<String, Integer> storeHashmap;

    Train(int id, String from, String to, int totalDistance) {
        this.trainID = id;
        this.journeyFrom = from;
        this.journeyTo = to;
        this.distanceBetween = totalDistance;
        this.bookingSlot = new HashMap<>();
        this.bookingSlot.put("S", 0);
        this.bookingSlot.put("B", 0);
        this.bookingSlot.put("A", 0);
        this.bookingSlot.put("H", 0);
        this.costOfTicket = totalDistance;
    }

    Train(Train t) {
        this.trainID = t.trainID;
        this.journeyFrom = t.journeyFrom;
        this.journeyTo = t.journeyTo;
        this.costOfTicket = t.costOfTicket;
        this.bookingSlot = new HashMap<>(t.storeHashmap);
        this.distanceBetween = t.distanceBetween;
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
                this.daywiseBook = new Train(it);
            }
        }
    }
}

public class TrainBookingSystem {
    static int counter = 100000001;
    public static Train trainCheck(String from, String to, Data data) {

        for (Train it : data.storeTrainData) {
            if ((from.equals(it.journeyFrom)) && (to.equals(it.journeyTo))) {
                return it;
            }
        }
        return null;
    }

    public static void bookSlot(int seatCount, String coach, String distance) {
        int cost = Integer.parseInt(distance);
        if (coach.equals("SL")) {
            System.out.println(counter + " " + (seatCount * cost));
        } else if (coach.equals("3A")) {
            System.out.println(counter + " " + (seatCount * cost * 2));
        } else if (coach.equals("2A")) {
            System.out.println(counter + " " + (seatCount * cost * 3));
        } else if (coach.equals("1A")) {
            System.out.println(counter + " " + (seatCount * cost * 4));
        }
        counter++;
    }

    public static void seatAvailable(Data data, String date, String coach, int seatCount) {
        for (DayWiseTrainbooking it : data.dayWiseBook) {
            if (it.date.equals(date)) {

                if (coach.equals("SL")) {
                    if (it.daywiseBook.bookingSlot.get("S") >= seatCount) {
                        int temp2 = it.daywiseBook.bookingSlot.get("S") - seatCount;
                        it.daywiseBook.bookingSlot.put("S", temp2);

                        bookSlot(seatCount, coach, Integer.toString(it.daywiseBook.distanceBetween));
                        return;
                    } else {
                        System.out.println("No seat Available");
                        return;
                    }
                } else if (coach.equals("3A")) {
                    if (it.daywiseBook.bookingSlot.containsKey("B")) {
                        if (it.daywiseBook.bookingSlot.get("B") >= seatCount) {
                            int temp2 = it.daywiseBook.bookingSlot.get("B") - seatCount;
                            it.daywiseBook.bookingSlot.put("B", temp2);
                            bookSlot(seatCount, coach, Integer.toString(it.daywiseBook.distanceBetween));
                            return;
                        } else {
                            System.out.println("No seat Available");
                            return;
                        }
                    }
                } else if (coach.equals("2A")) {
                    if (it.daywiseBook.bookingSlot.containsKey("A")) {
                        if (it.daywiseBook.bookingSlot.get("A") >= seatCount) {
                            int temp2 = it.daywiseBook.bookingSlot.get("A") - seatCount;
                            it.daywiseBook.bookingSlot.put("A", temp2);
                            bookSlot(seatCount, coach, Integer.toString(it.daywiseBook.distanceBetween));
                            return;
                        } else {
                            System.out.println("No seat Available");
                            return;
                        }
                    }
                } else if (coach.equals("1A")) {
                    if (it.daywiseBook.bookingSlot.containsKey("H")) {
                        if (it.daywiseBook.bookingSlot.get("H") >= seatCount) {
                            int temp2 = it.daywiseBook.bookingSlot.get("H") - seatCount;
                            it.daywiseBook.bookingSlot.put("H", temp2);
                            bookSlot(seatCount, coach, Integer.toString(it.daywiseBook.distanceBetween));
                            return;
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
                if (seat[0].charAt(0) == 'S') {
                    int value = trainData.bookingSlot.get("S") + Integer.parseInt((seat[1]));
                    trainData.bookingSlot.replace("S", value);
                } else if (seat[0].charAt(0) == 'B') {
                    int value = trainData.bookingSlot.get("B") + Integer.parseInt((seat[1]));
                    trainData.bookingSlot.put("B", value);
                } else if (seat[0].charAt(0) == 'A') {
                    int value = trainData.bookingSlot.get("A") + Integer.parseInt((seat[1]));
                    trainData.bookingSlot.put("A", value);
                } else if (seat[0].charAt(0) == 'H') {
                    int value = trainData.bookingSlot.get("H") + Integer.parseInt((seat[1]));
                    trainData.bookingSlot.put("H", value);
                }
            }

            trainData.storeHashmap = new HashMap<>(trainData.bookingSlot);
            // Printing the Train Data
            storeData.storeTrainData.add(trainData);
            trainCount--;
        }

        while (true) {
            String[] inputBookingRequest = sc.nextLine().split(" ");
            if (trainCheck(inputBookingRequest[0], inputBookingRequest[1], storeData) != null) {
                Train train1 = trainCheck(inputBookingRequest[0], inputBookingRequest[1], storeData);
                DayWiseTrainbooking daywise = new DayWiseTrainbooking(train1.trainID,
                        inputBookingRequest[2],
                        storeData);
                storeData.dayWiseBook.add(daywise);
                seatAvailable(storeData, inputBookingRequest[2], inputBookingRequest[3],
                        Integer.parseInt(inputBookingRequest[4]));
            } else {
                System.out.println("No Train Available");
            }
        }
    }
}