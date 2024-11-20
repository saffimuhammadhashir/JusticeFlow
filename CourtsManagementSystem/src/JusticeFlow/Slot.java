package JusticeFlow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.List;
public class Slot {
    private int slotID;
    public String dayName;
    private Time startTime;
    private Time endTime;
    private int slotNumber;
    private Integer caseID;
    private Integer judgeID;
    private Integer witnessID;
    private Integer CourtID;

    // Constructor
    public Slot(int slotID, String dayName, Time startTime, Time endTime, int slotNumber,
            Integer caseID, Integer judgeID, Integer witnessID, Integer CourtID) {
        this.slotID = slotID;
        this.dayName = dayName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotNumber = slotNumber;
        this.caseID = caseID;
        this.judgeID = judgeID;
        this.witnessID = witnessID;
        this.CourtID = CourtID;
    }

    public Slot(Slot other) {
        this.slotID = other.slotID;
        this.dayName = other.dayName;
        this.startTime = other.startTime != null ? new Time(other.startTime.getTime()) : null;
        this.endTime = other.endTime != null ? new Time(other.endTime.getTime()) : null;
        this.slotNumber = other.slotNumber;
        this.caseID = other.caseID;
        this.judgeID = other.judgeID;
        this.witnessID = other.witnessID;
        this.CourtID = other.CourtID;
    }

    // Getters and Setters
    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Integer getCaseID() {
        return caseID;
    }

    public void setCaseID(Integer caseID) {
        this.caseID = caseID;
    }

    public Integer getJudgeID() {
        return judgeID;
    }

    public void setJudgeID(Integer judgeID) {
        this.judgeID = judgeID;
    }

    public Integer getWitnessID() {
        return witnessID;
    }

    public Integer getCourtId() {
        return this.CourtID;
    }

    public void setCourtId(Integer courtid) {
        this.CourtID = courtid;
    }

    public void setWitnessID(Integer witnessID) {
        this.witnessID = witnessID;
    }

    // toString() method
    @Override
    public String toString() {
        return "Slot{" +
                "slotID=" + slotID +
                ", dayName='" + dayName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", slotNumber=" + slotNumber +
                ", caseID=" + caseID +
                ", judgeID=" + judgeID +
                ", witnessID=" + witnessID +
                ", CourtID=" + CourtID +
                '}';
    }

    public static void manageslot(List<Slot> AllSlot, DatabaseHandler dbHandler) {

        int count = 0;
        for (Slot s : AllSlot) {
            if (s == null) {
                continue;
            }
            if (s.getCaseID() == null && s.getJudgeID() == null && s.getWitnessID() == null) {
                count++;
            }
        }
        if (count < 15) {
            count = 50 - count;
            while (count > 0) {
                Slot newslot = createNextSlot(AllSlot);
                AllSlot.add(newslot);
                count--;
            }
        }
        dbHandler.updateOrInsertSlots(AllSlot);

        return;
    }

    private static Slot createNextSlot(List<Slot> allSlots) {

        Time startOfDay = Time.valueOf("09:00:00");
        Time endOfDay = Time.valueOf("19:00:00");

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dayName = currentDate.format(formatter);

        if (allSlots.isEmpty()) {
            return new Slot(1, dayName, startOfDay, Time.valueOf("09:30:00"), 0, null, null, null, null);
        }

        Slot lastSlot = allSlots.get(allSlots.size() - 1);
        LocalDate lastDate = LocalDate.parse(lastSlot.getDayName());
        LocalTime lastEndTime = lastSlot.getEndTime().toLocalTime();

        LocalTime nextStartTime = lastEndTime.isBefore(endOfDay.toLocalTime()) ? lastEndTime : LocalTime.of(9, 0);
        LocalDate nextDate = lastEndTime.isBefore(endOfDay.toLocalTime()) ? lastDate : lastDate.plusDays(1);

        LocalTime nextEndTime = nextStartTime.plusMinutes(30);
        if (nextEndTime.isAfter(endOfDay.toLocalTime())) {

            nextStartTime = LocalTime.of(9, 0);
            nextEndTime = nextStartTime.plusMinutes(30);
            nextDate = nextDate.plusDays(1);
        }

        if (nextEndTime.isAfter(endOfDay.toLocalTime())) {
            return null;
        }

        int maxSlotID = 0;
        for (Slot slot : allSlots) {
            if (slot != null && slot.getSlotID() > maxSlotID) {
                maxSlotID = slot.getSlotID();
            }
        }
        int newSlotID = maxSlotID + 1;

        String nextDayName = nextDate.format(formatter);

        Time nextStartTimeSQL = Time.valueOf(nextStartTime);
        Time nextEndTimeSQL = Time.valueOf(nextEndTime);

        return new Slot(newSlotID, nextDayName, nextStartTimeSQL, nextEndTimeSQL, 0, null, null, null, null);
    }



    
    public static Slot getLastSlotAtFarthestTime(List<Slot> allSlots) {
        if (allSlots == null || allSlots.isEmpty()) {
            return null;
        }

        Slot lastSlot = allSlots.get(0);
        for (Slot slot : allSlots) {
            if (slot.getEndTime().after(lastSlot.getEndTime())) {
                lastSlot = slot;
            }
        }
        return lastSlot;
    }


    public static void PossibleSchedule(List<Slot> AllSlot, Judge judge, Witness witness, Court court,
            List<Slot> possibleSlots) {

        for (Slot s : AllSlot) {
            if (s == null) {
                break;
            }
            if (s.getCaseID() == null && s.getJudgeID() == null && s.getWitnessID() == null
                    && s.getCourtId() == null) {
                Slot new_s = new Slot(s);
                new_s.setJudgeID(judge.getJudgeID());
                if (witness != null) {
                    new_s.setWitnessID(witness.getWitnessID());
                }
                new_s.setCourtId(court.getCourtID());
                possibleSlots.add(new_s);
                System.out.println(s.toString());
            }
            else if (!s.getCourtId().equals(null) && !s.getCourtId().equals(court.getCourtID())) {
                Slot new_s = new Slot(s);
                new_s.setJudgeID(judge.getJudgeID());
                if (witness != null) {
                    new_s.setWitnessID(witness.getWitnessID());
                }
                new_s.setCourtId(court.getCourtID());
                possibleSlots.add(new_s);
                System.out.println(s.toString());
            }
        }
        if (!possibleSlots.isEmpty()) {
            return;
        }

    }

    public static void removeSlotsWithSameIDOneByOne(Slot targetSlot, List<Slot> slotList) {
        if (targetSlot == null || slotList == null) {
            return; // Handle null inputs gracefully
        }

        // Get current time and add 12 hours to it
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime cutoffTime = currentTime.plusHours(12);
        System.out.println("Cutoff Time: " + cutoffTime); // For debugging purposes

        Iterator<Slot> iterator = slotList.iterator(); // Initialize iterator
        
        // First, remove all slots whose start time is before the current time + 12 hours
        while (iterator.hasNext()) {
            Slot currentSlot = iterator.next();

            // Convert currentSlot's startTime (java.sql.Time) to LocalDateTime
            LocalTime startTime = currentSlot.getStartTime().toLocalTime();
            
            // Parse the date from dayName (assuming dayName is in the format "yyyy-MM-dd")
            LocalDateTime slotStartTime = parseSlotStartTime(currentSlot.getDayName(), startTime);

            // Remove slots where the start time is before current time and before cutoff time
            if (slotStartTime.isBefore(currentTime) && slotStartTime.isBefore(cutoffTime)) {
                iterator.remove();
                System.out.println("Removed slot with ID (cutoff time condition): " + currentSlot.getSlotID());
            }
        }

        // Now, remove slots with the same slot ID as the target slot
        iterator = slotList.iterator(); // Reinitialize iterator to remove slots by targetSlot ID
        while (iterator.hasNext()) {
            Slot currentSlot = iterator.next();

            if (currentSlot.getSlotID() == targetSlot.getSlotID()) {
                iterator.remove();
                System.out.println("Removed slot with ID (matching target): " + currentSlot.getSlotID());
            }
        }
    }

    private static LocalDateTime parseSlotStartTime(String dayName, LocalTime startTime) {
        // Format the startTime into a full string like "09:00:00"
        String formattedStartTime = String.format("%02d:%02d:%02d", 
                                                  startTime.getHour(), 
                                                  startTime.getMinute(), 
                                                  startTime.getSecond());

        // Combine the dayName and formattedStartTime into a full datetime string
        String fullDateTimeString = dayName + "T" + formattedStartTime;

        // Define a DateTimeFormatter to parse the full date-time string
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        // Convert the dayName + startTime to LocalDateTime
        return LocalDateTime.parse(fullDateTimeString, formatter);
    }


}
