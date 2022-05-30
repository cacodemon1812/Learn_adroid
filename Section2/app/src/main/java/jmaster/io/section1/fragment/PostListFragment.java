public void showNotification(String title,
        String message) {
        Intent intent
        = new Intent(this, MainActivity.class);
        String channel_id = "notification_channel";

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent  = PendingIntent.getActivity(  this, 0, intent,
        PendingIntent.FLAG_ONE_SHOT);

        // Create a Builder object using NotificationCompat
        // class. This will allow control over all the flags
        NotificationCompat.Builder builder
        = new NotificationCompat
        .Builder(getApplicationContext(),
        channel_id)
        .setSmallIcon(R.drawable.gfg)
        .setAutoCancel(true)
        .setVibrate(new long[]{1000, 1000, 1000,
        1000, 1000})
        .setOnlyAlertOnce(true)
        .setContentIntent(pendingIntent);

        // A customized design for the notification can be
        // set only for Android versions 4.1 and above. Thus
        // condition for the same is checked here.
        if (Build.VERSION.SDK_INT
        >= Build.VERSION_CODES.JELLY_BEAN) {
        builder = builder.setContent(
        getCustomDesign(title, message));
        } // If Android Version is lower than Jelly Beans,
        // customized layout cannot be used and thus the
        // layout is set as follows
        else {
        builder = builder.setContentTitle(title)
        .setContentText(message)
        .setSmallIcon(R.drawable.gfg);
        }
        // Create an object of NotificationManager class to
        // notify the
        // user of events that happen in the background.
        NotificationManager notificationManager
        = (NotificationManager) getSystemService(
        Context.NOTIFICATION_SERVICE);
        // Check if the Android Version is greater than Oreo
        if (Build.VERSION.SDK_INT
        >= Build.VERSION_CODES.O) {
        NotificationChannel notificationChannel
        = new NotificationChannel(
        channel_id, "web_app",
        NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(
        notificationChannel);
        }

        notificationManager.notify(0, builder.build());
        }
        }