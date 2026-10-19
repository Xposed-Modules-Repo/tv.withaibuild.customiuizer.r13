-- Direct Hook API references in a module DEX; definitions belong to the framework.
-- Do not infer API 101 compatibility solely from a package-name match.
SELECT d.source_path, d.entry_name, m.callee, COUNT(*) AS direct_calls
FROM method_calls m
JOIN dex_files d ON d.file_id = m.file_id
WHERE m.callee LIKE 'Lio/github/libxposed/api/%'
GROUP BY d.source_path, d.entry_name, m.callee
ORDER BY d.source_path, d.entry_name, m.callee;
