package calculator.cli;

import calculator.Calculator;

public class CliContext {

    private final Calculator calculator;
    private boolean running = true;

    public CliContext(Calculator calculator) {
        this.calculator = calculator;
    }

    public Calculator calculator(){
        return calculator;
    }

    public boolean isRunning(){
        return running;
    }

    public void stop(){
        this.running = false;
    }
}
