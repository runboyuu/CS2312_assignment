public interface Command {
    void execute(String[] cmdParts) throws ExInsufficientCmdArgs, ExEquipmentCodeInUse, ExEquipmentCodeNotFound, ExMemberIdInUse,
            ExInvalidNewDay, ExInvalidDayFormat, ExMemberNotFound, ExNoAvailableBorrow, ExExistBorrowMember, ExDayMustLatsOne, ExNotANumber, ExDayOverlap;
}
