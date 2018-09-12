package core.utils.results;

public class OperationFailed implements Result {

    private String reason;

    public OperationFailed(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean succeed() {
        return false;
    }

    @Override
    public String reason() {
        return reason;
    }
}
