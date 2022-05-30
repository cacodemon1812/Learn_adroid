package jmaster.io.evnloyalty.service;

import java.util.ArrayList;
import java.util.List;

import jmaster.io.evnloyalty.model.Notification;

public class NotificationService {

    public List<Notification> find() {
        List<Notification> notifications = new ArrayList<>();

        notifications.add(new Notification(1L, "EVN và UBND tỉnh Ninh Bình họp tháo gỡ vướng mắc trong triển khai các dự án điện",
                "18:56 | 27/05/2022 "));

        notifications.add(new Notification(1L, "EVN và UBND tỉnh Ninh Bình họp tháo gỡ vướng mắc trong triển khai các dự án điện",
                "18:56 | 27/05/2022 "));

        notifications.add(new Notification(1L, "EVN và UBND tỉnh Ninh Bình họp tháo gỡ vướng mắc trong triển khai các dự án điện",
                "18:56 | 27/05/2022 "));

        notifications.add(new Notification(1L, "EVN và UBND tỉnh Ninh Bình họp tháo gỡ vướng mắc trong triển khai các dự án điện",
                "18:56 | 27/05/2022 "));

        notifications.add(new Notification(1L, "EVN và UBND tỉnh Ninh Bình họp tháo gỡ vướng mắc trong triển khai các dự án điện",
                "18:56 | 27/05/2022 "));

        return notifications;
    }

}
