-- services.jar from one API 33 ROM. Definition views avoid external references.
SELECT 'method' AS kind, file_id, method_descriptor AS descriptor
FROM class_methods
WHERE (class_descriptor = 'Lcom/android/server/TelephonyRegistry$Record;'
       AND method_name = 'matchTelephonyCallbackEvent')
   OR (class_descriptor = 'Lcom/android/server/TelephonyRegistry;'
       AND method_name IN ('notifyCallState', 'notifyCallStateForAllSubs', 'notifyCallStateForPhoneId'))
   OR (class_descriptor = 'Lcom/android/server/audio/AudioService;'
       AND method_name = 'requestAudioFocus')
UNION ALL
SELECT 'field' AS kind, file_id, field_descriptor AS descriptor
FROM class_fields
WHERE class_descriptor = 'Lcom/android/server/TelephonyRegistry$Record;'
  AND field_name IN ('callback', 'callingPackage', 'eventList', 'events')
ORDER BY file_id, kind, descriptor;
