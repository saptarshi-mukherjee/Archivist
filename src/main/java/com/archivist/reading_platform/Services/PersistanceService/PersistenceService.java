package com.archivist.reading_platform.Services.PersistanceService;

import com.archivist.reading_platform.Models.Notification;
import com.archivist.reading_platform.Models.Reader;

import java.util.List;

public interface PersistenceService {
    public void saveNotification(Notification notification, List<Reader> subscribers);
}
