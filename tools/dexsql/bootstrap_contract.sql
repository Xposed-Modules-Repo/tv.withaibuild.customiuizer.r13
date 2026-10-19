-- A13 uses Application.attach and SystemUIApplication.onCreate, not A14 plugin bootstrap.
-- Open the required boot framework too; absent framework input is UNKNOWN.
SELECT d.source_path, d.entry_name, m.method_descriptor
FROM class_methods m
JOIN dex_files d ON d.file_id = m.file_id
WHERE (m.class_descriptor = 'Landroid/app/Application;' AND m.method_name IN ('attach', 'onCreate'))
   OR (m.class_descriptor = 'Lcom/android/systemui/SystemUIApplication;' AND m.method_name = 'onCreate')
ORDER BY m.file_id, m.method_idx;
