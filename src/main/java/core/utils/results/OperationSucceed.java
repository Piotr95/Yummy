package core.utils.results;

public class OperationSucceed implements Result {
    @Override
    public boolean succeed() {
        return true;
    }

    @Override
    public String reason() {
        return "";
    }
}
