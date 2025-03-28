# Запуск автотестов для мобильного приложения "Мобильный хоспис"

## Предварительные условия
Перед запуском автотестов необходимо:

1. **Клонировать репозиторий с проектом**  

2. **Открыть проект в Android Studio**

3. **Настроить эмулятор:**  
   - **Устройство**: Pixel 4  
   - **Android API**: 29  

## Запуск тестов

1. Открыть терминал в корневой папке проекта.
2. Выполнить команду:
   ```sh
   adb shell am instrument -w -m -e debug false ru.iteco.fmhandroid.test/androidx.test.runner.AndroidJUnitRunner
   ```
3. Дождаться завершения тестов.

## Формирование Allure-отчета
После завершения тестов выполнить команду:
   ```sh
   allure generate allure-results --clean -o allure-report
