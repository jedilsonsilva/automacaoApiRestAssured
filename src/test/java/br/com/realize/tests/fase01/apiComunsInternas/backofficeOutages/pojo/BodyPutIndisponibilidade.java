package br.com.realize.tests.fase01.apiComunsInternas.backofficeOutages.pojo;

import java.util.Collections;
import java.util.List;

public class BodyPutIndisponibilidade {

    private String duration;
    private String explanation;
    private String id;
    private String outageTime;
    private String partial;
    private List<String> unavailableEndpoints;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getUnavailableEndpoints() {
        return unavailableEndpoints;
    }

    public void setUnavailableEndpoints(String unavailableEndpoints) {
        this.unavailableEndpoints = Collections.singletonList(unavailableEndpoints);
    }
}
