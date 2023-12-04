package com.alal.backend.domain.dto.response;

import com.alal.backend.domain.entity.project.Script;
import lombok.Builder;

import java.util.List;

@Builder
public class ReadSceneResponseList {
    private List<ReadSceneResponse> readSceneResponses;
    private Long sceneId;

    public static ReadSceneResponseList from(List<ReadSceneResponse> readSceneResponses, Script script) {
        return ReadSceneResponseList.builder()
                .readSceneResponses(readSceneResponses)
                .sceneId(script.getScriptId())
                .build();
    }
}
