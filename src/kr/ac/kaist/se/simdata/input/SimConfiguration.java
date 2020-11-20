package kr.ac.kaist.se.simdata.input;

public class SimConfiguration {

    protected int simTotalTime = 0;
    protected boolean simMapeMode = false;
    protected boolean simHasScenario = false;

    public SimConfiguration(){

    }

    public int getSimTotalTime() {
        return simTotalTime;
    }

    public void setSimTotalTime(int simTotalTime) {
        this.simTotalTime = simTotalTime;
    }

    public boolean isSimMapeMode() {
        return simMapeMode;
    }

    public void setSimMapeMode(boolean simMapeMode) {
        this.simMapeMode = simMapeMode;
    }

    public boolean isSimHasScenario() {
        return simHasScenario;
    }

    public void setSimHasScenario(boolean simHasScenario) {
        this.simHasScenario = simHasScenario;
    }
}
