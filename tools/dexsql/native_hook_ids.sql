-- Final R8 DEX. Combine with hook_api_calls.sql and the SAME build's mapping.
-- These two IDs belong to existing A13 bootstrap sites, not A14 plugin targets.
SELECT DISTINCT file_id, method, value
FROM method_strings
WHERE value IN ('application.attach', 'systemui.application.create')
ORDER BY file_id, method, value;
