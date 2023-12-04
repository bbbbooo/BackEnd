package com.alal.backend.service.group;

import com.alal.backend.domain.dto.request.UploadSceneRequest;
import com.alal.backend.domain.dto.response.ReadSceneResponse;
import com.alal.backend.domain.dto.response.ReadSceneResponseList;
import com.alal.backend.domain.dto.response.UploadSceneResponse;
import com.alal.backend.domain.entity.project.Scene;
import com.alal.backend.domain.entity.project.Script;
import com.alal.backend.repository.ScriptRepository;
import com.alal.backend.repository.group.SceneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final SceneRepository sceneRepository;
    private final ScriptRepository scriptRepository;

    private final GoogleService googleService;

    @Transactional(readOnly = true)
    public ReadSceneResponseList readAllScene(Long projectId) {
        Script script = scriptRepository.getReferenceById(projectId);
        List<Scene> scenes = sceneRepository.findAllById(Collections.singleton(script.getScriptId()));

        List<ReadSceneResponse> readSceneResponses = scenes.stream()
                .map(Scene::toReadSceneResponse)
                .collect(Collectors.toList());

        return ReadSceneResponseList.from(readSceneResponses, script);
    }

    @Transactional
    public UploadSceneResponse uploadScene(UploadSceneRequest uploadSceneRequest) {
        Script script = scriptRepository.getReferenceById(uploadSceneRequest.getScriptId());
        String thumbnailUrl = googleService.uploadImage(uploadSceneRequest);
        Scene scene = Scene.from(uploadSceneRequest, script, thumbnailUrl);

        sceneRepository.save(scene);

        return scene.toUploadResponse();
    }
}
