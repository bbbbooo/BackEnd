package com.alal.backend.controller.user;

import com.alal.backend.config.security.token.CurrentUser;
import com.alal.backend.config.security.token.UserPrincipal;
import com.alal.backend.domain.dto.request.ReadImageRequest;
import com.alal.backend.domain.dto.request.UploadImageRequest;
import com.alal.backend.domain.dto.response.ReadImageResponse;
import com.alal.backend.domain.dto.response.UploadImageResponse;
import com.alal.backend.service.user.FlaskService;
import com.alal.backend.service.user.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @Async
    @PostMapping("upload")
    public ResponseEntity<UploadImageResponse> upload(@RequestBody UploadImageRequest uploadImageRequest
//                                                      ,@CurrentUser UserPrincipal userPrincipal
    ) {
        Long userId = 1L;
        return ResponseEntity.ok(imageService.uploadImage(uploadImageRequest, userId));
    }
}
