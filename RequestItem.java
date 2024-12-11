public class RequestItem {
    private Member member;
    private Equipment equipment;
    private EquipmentSet equipmentSet;
    private Day startDay;
    private Day endDay;
    public RequestItem(Member member,Equipment equipment){
        this.member=member;
        this.equipment=equipment;

    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public EquipmentSet getEquipmentSet() {
        return equipmentSet;
    }

    public void setEquipmentSet(EquipmentSet equipmentSet) {
        this.equipmentSet = equipmentSet;
    }

    public Day getStartDay() {
        return startDay;
    }

    public void setStartDay(Day startDay) {
        this.startDay = startDay;
    }

    public Day getEndDay() {
        return endDay;
    }

    public void setEndDay(Day endDay) {
        this.endDay = endDay;
    }

}
