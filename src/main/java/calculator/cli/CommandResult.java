package calculator.cli;

public record CommandResult(Status status, String message) {
    public enum Status { SUCCESS, USER_ERROR, SYSTEM_ERROR }

    public static CommandResult ok(String msg) { return new CommandResult(Status.SUCCESS, msg); }
    public static CommandResult userError(String msg) { return new CommandResult(Status.USER_ERROR, msg); }
    public static CommandResult systemError(String msg) { return new CommandResult(Status.SYSTEM_ERROR, msg); }
}