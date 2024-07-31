package com.greenmark.common.dto.parameters.pdbupdater;

import java.io.Serializable;
import java.util.List;

/**
 * @formatter:off
 * <p>Copyright: Copyright (c) 2023</p>
 * <p>Company: Greenman Financial Services, Inc.</p>
 * <p>Title: CreateUpdateJobParams</p>
 * <p>Description: Used by the PDB Updater GUI to create test jobs.</p>

 * @author Monte Seibel
 * @version 7.5
 * @formatter:on
 **/

public class CreateUpdateJobParams implements Serializable {
    public static final String CLASSNAME = "CreateUpdateJobParams";
    private static final long serialVersionUID = 1L;

    private String jobName;
    private String jobDescription;

    private List<Integer> selectedTimeframes;
    private List<Integer> selectedSecurityIds;

    // ------------------------------------------------ SETTERS/GETTERS ---------------------------------------------------
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public List<Integer> getSelectedTimeframes() {
        return selectedTimeframes;
    }

    public void setSelectedTimeframes(List<Integer> selectedTimeframes) {
        this.selectedTimeframes = selectedTimeframes;
    }

    public List<Integer> getSelectedSecurityIds() {
        return selectedSecurityIds;
    }

    public void setSelectedSecurityIds(List<Integer> selectedSecurityIds) {
        this.selectedSecurityIds = selectedSecurityIds;
    }
}
