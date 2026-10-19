-- Decode the two dispatchers to confirm event IDs 6 and 36 before changing a filter.
SELECT DISTINCT file_id, method, callee
FROM method_calls
WHERE method_class = 'Lcom/android/server/TelephonyRegistry;'
  AND method_name IN ('notifyCallState', 'notifyCallStateForAllSubs')
  AND (callee LIKE '%->matchTelephonyCallbackEvent(%'
       OR callee LIKE '%->onCallStateChanged(%'
       OR callee LIKE '%->onLegacyCallStateChanged(%')
ORDER BY file_id, method, callee;
