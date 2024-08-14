package com.greenmark.app.model;

import org.springframework.stereotype.Component;

@Component
public class ModelConfig {

    ModelConfigValues config;

    ModelLogic minute01;
    ModelLogic minute05;
    ModelLogic minute15;
    ModelLogic minute60;
    ModelLogic minuteDD;
    ModelLogic minuteAll;

    public ModelConfig(ModelConfigValues modelConfigValues) {
        this.config = modelConfigValues;
    }

    public ModelLogic getMinute01() {
        if (this.minute01 == null) {
            this.minute01 = buildModeLogic01();
        }
        return this.minute01;
    }

    public ModelLogic getMinute05() {
        if (this.minute05 == null) {
            this.minute05 = buildModeLogic05();
        }
        return this.minute05;
    }

    public ModelLogic getMinute15() {
        if (this.minute15 == null) {
            this.minute15 = buildModeLogic15();
        }
        return this.minute15;
    }

    public ModelLogic getMinute60() {
        if (this.minute60 == null) {
            this.minute60 = buildModeLogic60();
        }
        return this.minute60;
    }


    public ModelLogic getMinuteDD() {
        if (this.minuteDD == null) {
            this.minuteDD = buildModeLogicDD();
        }
        return this.minuteDD;
    }

    public ModelLogic getMinuteAll() {
        if (this.minuteDD == null) {
            this.minuteDD = buildModeLogicAll();
        }
        return this.minuteDD;
    }

    /////////////////////////////////////////////////////////////////////////////////
    private ModelLogic buildModeLogic01() {
        return new ModelLogic(
                config.getModel_minute01_demote(),
                config.getModel_minute01_promote()
        );
    }

    private ModelLogic buildModeLogic05() {
        return new ModelLogic(
                config.getModel_minute05_demote(),
                config.getModel_minute05_promote()
        );
    }

    private ModelLogic buildModeLogic15() {
        return new ModelLogic(
                config.getModel_minute15_demote(),
                config.getModel_minute15_promote()
        );
    }

    private ModelLogic buildModeLogic60() {
        return new ModelLogic(
                config.getModel_minute60_demote(),
                config.getModel_minute60_promote()
        );
    }

    private ModelLogic buildModeLogicDD() {
        return new ModelLogic(
                config.getModel_daily_demote(),
                config.getModel_daily_promote()
        );
    }

    private ModelLogic buildModeLogicAll() {
        return new ModelLogic(
                config.getModel_all_demote(),
                config.getModel_all_promote()
        );
    }
}
