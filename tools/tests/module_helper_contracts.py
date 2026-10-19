"""Shared native hook entry contracts; wrappers are checked at their delegate."""

HOOK_INSTALL_TARGETS = [
    ('public static CustomMethodUnhooker hookMethod(Method method, MethodHook callback)', 'null'),
    ('public static CustomMethodUnhooker findAndHookMethod(String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback)', 'null'),
    ('public static CustomMethodUnhooker findAndHookMethodWithId(Class<?> clazz, String methodName, String id, Object... parameterTypesAndCallback)', 'null'),
    ('public static CustomMethodUnhooker findAndHookMethodWithId(String className, ClassLoader classLoader, String methodName, String id, Object... parameterTypesAndCallback)', 'null'),
    ('public static boolean findAndHookMethodSilently(String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback)', 'false'),
    ('public static boolean findAndHookMethodSilently(Class<?> clazz, String methodName, Object... parameterTypesAndCallback)', 'false'),
    ('public static CustomMethodUnhooker findAndHookConstructor(String className, ClassLoader classLoader, Object... parameterTypesAndCallback)', 'null'),
    ('public static void hookAllConstructors(String className, ClassLoader classLoader, MethodHook callback)', 'void'),
    ('public static void hookAllConstructors(Class<?> hookClass, MethodHook callback)', 'void'),
    ('public static void hookAllMethods(String className, ClassLoader classLoader, String methodName, MethodHook callback)', 'void'),
    ('public static void hookAllMethods(Class<?> hookClass, String methodName, MethodHook callback)', 'void'),
    ('public static boolean hookAllMethodsSilently(String className, ClassLoader classLoader, String methodName, MethodHook callback)', 'false'),
    ('public static boolean hookAllMethodsSilently(Class<?> hookClass, String methodName, MethodHook callback)', 'false'),
]

HOOK_INSTALL_METHODS = [signature for signature, _ in HOOK_INSTALL_TARGETS]
