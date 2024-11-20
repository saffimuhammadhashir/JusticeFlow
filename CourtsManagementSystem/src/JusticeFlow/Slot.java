package JusticeFlow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;

public class Slot {
    private int slotID;
    private String dayName;
    private Time startTime;
    private Time endTime;
    private int slotNumber;
    private Integer caseID;
    private Integer judgeID;
    private Integer witnessID;

    // Constructor
    public Slot() {

    }

    public Slot(int slotID, String dayName, Time startTime, Time endTime, int slotNumber,
            Integer caseID, Integer judgeID, Integer witnessID) {
        this.slotID = slotID;
        this.dayName = dayName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotNumber = slotNumber;
        this.caseID = caseID;
        this.judgeID = judgeID;
        this.witnessID = witnessID;
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
                "}\n\n------------------------------------------\n";
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
            count = 30 - count;
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

        // Get the current date in "yyyy-MM-dd" format
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dayName = currentDate.format(formatter);

        // If no slots exist, create the first slot of the day
        if (allSlots.isEmpty()) {
            return new Slot(1, dayName, startOfDay, Time.valueOf("09:30:00"), 0, null, null, null);
        }

        // Get the date and end time of the last slot
        Slot lastSlot = allSlots.get(allSlots.size() - 1);
        LocalDate lastDate = LocalDate.parse(lastSlot.getDayName());
        LocalTime lastEndTime = lastSlot.getEndTime().toLocalTime();

        // Check if the last slot ends before the end of the working day
        LocalTime nextStartTime = lastEndTime.isBefore(endOfDay.toLocalTime()) ? lastEndTime : LocalTime.of(9, 0);
        LocalDate nextDate = lastEndTime.isBefore(endOfDay.toLocalTime()) ? lastDate : lastDate.plusDays(1);

        // Calculate the next end time by adding 30 minutes to the next start time
        LocalTime nextEndTime = nextStartTime.plusMinutes(30);
        if (nextEndTime.isAfter(endOfDay.toLocalTime())) {
            // If the next slot end time exceeds the working hours, move to the next day
            nextStartTime = LocalTime.of(9, 0); // 9 AM start for the next day
            nextEndTime = nextStartTime.plusMinutes(30);
            nextDate = nextDate.plusDays(1);
        }

        // If the next available slot end time is beyond the working hours, return null
        if (nextEndTime.isAfter(endOfDay.toLocalTime())) {
            return null;
        }

        // Find the highest slotID and assign the next ID
        int maxSlotID = 0;
        for (Slot slot : allSlots) {
            if (slot != null && slot.getSlotID() > maxSlotID) {
                maxSlotID = slot.getSlotID();
            }
        }
        int newSlotID = maxSlotID + 1;

        // Create the new slot with the calculated date and times
        String nextDayName = nextDate.format(formatter);

        // Convert LocalTime to Time for slot creation
        Time nextStartTimeSQL = Time.valueOf(nextStartTime);
        Time nextEndTimeSQL = Time.valueOf(nextEndTime);

        return new Slot(newSlotID, nextDayName, nextStartTimeSQL, nextEndTimeSQL, 0, null, null, null);
    }

    public static void PossibleSchedule(List<Slot> AllSlot, Judge judge, Witness witness, List<Slot> possibleSlots) {
        while (true) {
            Slot newslot = createNextSlot(AllSlot);
            AllSlot.add(newslot);

            for (Slot s : AllSlot) {
                if (s == null) {
                    break;
                }
                if (s.getCaseID() == null && s.getJudgeID() == null && s.getWitnessID() == null) {
                    Slot new_s = s;
                    new_s.setJudgeID(judge.getJudgeID());
                    new_s.setWitnessID(witness.getWitnessID());
                    possibleSlots.add(new_s);
                }
            }
            if (!possibleSlots.isEmpty()) {
                return;
            }

        }
    }
}
