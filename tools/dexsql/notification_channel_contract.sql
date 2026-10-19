-- SystemUI + Settings + boot framework from the SAME ROM.
-- Also inspect Settings' exported CHANNEL_NOTIFICATION_SETTINGS intent filter
-- with get_manifest_components, then decode ChannelPanelActivity.launchFullSettings.
SELECT file_id, method_descriptor
FROM class_methods
WHERE (class_descriptor = 'Lcom/android/systemui/statusbar/notification/row/MiuiNotificationMenuRow;'
       AND method_name IN ('createMenuViews', 'onClickInfoItem'))
   OR (class_descriptor = 'Lcom/android/systemui/statusbar/notification/NotificationUtil;'
       AND method_name = 'isHybrid')
   OR (class_descriptor = 'Lcom/android/systemui/statusbar/notification/ExpandedNotification;'
       AND method_name IN ('getAppUid', 'getPackageName'))
   OR (class_descriptor = 'Lcom/android/settings/notification/app/ChannelPanelActivity;'
       AND method_name IN ('onCreate', 'launchFullSettings'))
   OR (class_descriptor = 'Landroid/os/UserHandle;'
       AND method_name = 'getUserHandleForUid')
ORDER BY file_id, method_descriptor;
