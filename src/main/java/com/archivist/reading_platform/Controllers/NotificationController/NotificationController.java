package com.archivist.reading_platform.Controllers.NotificationController;


import com.archivist.reading_platform.DTO.ResponseDTO.NotificationResponseDto;
import com.archivist.reading_platform.Services.NotificationService.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notification_service;


    @GetMapping("/get/{sub_name}")
    public NotificationResponseDto getNotifications(@PathVariable("sub_name") String sub_name) {
        return notification_service.getNotifications(sub_name);
    }

}
