package ru.focus_start_java_test_task.kononov_e_s;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ErrorLogger {
    private static int errorsCount;
    private static int filePairCount;

    static int getErrorsCount() {
        return errorsCount;
    }

    static void beginErrorLog() throws FileNotFoundException {
        try (PrintWriter errorLogger = new PrintWriter("Error Log.txt")) {
            errorLogger.println("Формат ошибок:");
            errorLogger.println("\"Порядковый номер входного файла\", \"Код ошибки\", \"Строка файла, содержащая ошибку\"");
            errorLogger.println("Коды ошибок:");
            errorLogger.println("1 - нарушен порядок сортировки");
            errorLogger.println("2 - строка файла пустая или содержит пробельный символ (для входных данных типа строки)");
            errorLogger.println("3 - невозможно преобразовать строку файла в целое число (для входных данных типа целые числа)");
            errorLogger.println();
        }
    }

    static void increaseFilePairCount() {
        ++filePairCount;
    }

    static String getErrorString(String incorrectString, int fileIndex, int errorCode) {
        ++errorsCount;

        return (filePairCount + fileIndex) + ", " + errorCode + ", " + incorrectString;
    }

    static void completeErrorLog() throws IOException {
        try (PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            errorLogger.println();
            errorLogger.println("Общее количество ошибок во входных файлах: " + errorsCount);
        }
    }
}