package kr.co.yapp._22nd.coffice.ui.place;

import lombok.Data;

@Data
public class CrowdednessResponse {
    private final String weekDayType;
    private final String dayTimeType;
    private final String crowdednessLevel;
}
