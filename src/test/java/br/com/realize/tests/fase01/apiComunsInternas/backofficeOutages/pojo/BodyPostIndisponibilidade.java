package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.pojo;

import java.util.Collections;
import java.util.List;

public class BodyPostIndisponibilidade {

    private String duration;
    private String explanation;
    private String outageTime;
    private String partial;
    private List unavailableEndpoints;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getOutageTime() {
        return outageTime;
    }

    public void setOutageTime(String outageTime) {
        this.outageTime = outageTime;
    }

    public String getPartial() {
        return partial;
    }

    public void setPartial(String partial) {
        this.partial = partial;
    }

    public List getUnavailableEndpoints() {
        return unavailableEndpoints;
    }

    public void setUnavailableEndpoints(String unavailableEndpoints) {
        this.unavailableEndpoints = Collections.singletonList(unavailableEndpoints);
    }
}
