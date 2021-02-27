package ru.focus_start_java_test_task.kononov_e_s;

import java.io.*;
import java.util.Objects;

public class Main {
    private static boolean isSortCompleteWithError = false;
    private static boolean isErrorLogCompleted = true;
    private static final File TEMP_DIRECTORY = new File("Temp");

    private static void checkInputFiles(String[] args) throws IOException {
        for (int i = 3; i < args.length; ++i) {
            File inputFile = new File(args[i]);

            if (!inputFile.exists()) {
                throw new IOException(args[i] + System.lineSeparator() +
                        "Неверно заданы аргументы. Указанный входной файл не существует.");
            }
        }
    }

    private static void checkOutputFile(String outputFilePath) throws IOException {
        File outputFileAbstractPathname = new File(outputFilePath);

        boolean isOutputFileExists = outputFileAbstractPathname.exists();

        if (isOutputFileExists) {
            throw new IOException(outputFilePath + System.lineSeparator() +
                    "По указанному пути уже существует файл." + System.lineSeparator() +
                    "Укажите путь к несуществующему файлу в качестве пути к выходному файлу.");
        }
    }

    private static void createTempDirectory() throws IOException {
        boolean isTempDirectoryExists = TEMP_DIRECTORY.exists();

        if (isTempDirectoryExists) {
            throw new IOException("В корневой папке программы существует папка с именем Temp." + System.lineSeparator() +
                    "В корневой папке программы могут находится только файлы программы и файлы с входными данными");
        }

        boolean isTempDirectoryCreated = TEMP_DIRECTORY.mkdir();

        if (!isTempDirectoryCreated) {
            throw new IOException("Не удалось создать временную папку");
        }
    }

    private static void renameTempOutputFile(String tempOutputFilePath, String outputFilePath) throws IOException {
        File outputTempFilePath = new File(tempOutputFilePath);
        boolean isTempOutputFileRenamed = outputTempFilePath.renameTo(new File(outputFilePath));

        if (!isTempOutputFileRenamed) {
            throw new IOException("Не удалось переименовать временный файл во выходной файл");
        }
    }

    private static void deleteTempFilesAndDirectory() throws IOException {
        for (File file : Objects.requireNonNull(TEMP_DIRECTORY.listFiles())) {
            if (file.isFile()) {
                boolean isTempFileDeleted = file.delete();

                if (!isTempFileDeleted) {
                    throw new IOException("Не удалось удалить временный файл");
                }
            }
        }

        boolean isTempDirectoryDeleted = TEMP_DIRECTORY.delete();

        if (!isTempDirectoryDeleted) {
            throw new IOException("Не удалось удалить временную папку");
        }
    }

    private static void printHelp() {
        System.out.println("Допустимые значения и порядок аргументов программы:");
        System.out.println("-help - вызов справки по аргументам программы. Необязательный аргумент");
        System.out.println("1. Режим сортировки: -a - сортировка по возрастанию, -d - сортировка по убыванию");
        System.out.println("Необязательный аргумент. По умолчанию выполняется сортировка по возрастанию");
        System.out.println("2. Тип входных данных: -s - строки, -i - целые числа. Обязательный аргумент");
        System.out.println("3. Путь к выходному файлу. Обязательный аргумент");
        System.out.println("4. Пути к входным файлам. Обязательный аргумент. Необходимо указать не менее одного файла");
    }

    private static void printIncorrectArgumentsError() {
        System.out.println("Неверно заданы аргументы программы");
        printHelp();
    }

    private static String getSortReport() {
        int errorsCount = ErrorLogger.getErrorsCount();

        if (!isSortCompleteWithError && errorsCount == 0) {
            return "Сортировка успешно выполнена";
        }

        return "Сортировка выполнена частично. Количество ошибок во входных файлах: " + errorsCount;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            printIncorrectArgumentsError();
        } else {
            if (args[0].equals("-s") || args[0].equals("-i")) {
                String[] newArgs = new String[args.length + 1];
                System.arraycopy(args, 0, newArgs, 1, args.length);
                newArgs[0] = "-a";
                args = newArgs;
            }

            if (args.length < 4) {
                printIncorrectArgumentsError();
            } else {
                try {
                    switch (args[0]) {
                        case "-a":
                            if (args[1].equals("-s")) {
                                checkOutputFile(args[2]);
                                checkInputFiles(args);
                                ErrorLogger.beginErrorLog();
                                isErrorLogCompleted = false;

                                if (args.length == 4) {
                                    StringsSort.stringsSingleFileAscendingSort(args[2], args[3]);
                                } else if (args.length == 5) {
                                    StringsSort.stringsFirstTwoFilesAscendingSort(args[2], args[3], args[4]);
                                } else {
                                    createTempDirectory();
                                    String tempOutputFilePath = "Temp\\1.txt";
                                    String previousTempOutputFilePath = "Temp\\1.txt";
                                    StringsSort.stringsFirstTwoFilesAscendingSort(tempOutputFilePath, args[3], args[4]);

                                    try {
                                        for (int i = 5; i < args.length; ++i) {
                                            previousTempOutputFilePath = "Temp\\" + (i - 4) + ".txt";
                                            tempOutputFilePath = "Temp\\" + (i - 3) + ".txt";
                                            ErrorLogger.increaseFilePairCount();
                                            StringsSort.stringsRemainingFilesAscendingSort(tempOutputFilePath, previousTempOutputFilePath, args[i]);
                                        }

                                        renameTempOutputFile(tempOutputFilePath, args[2]);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        isSortCompleteWithError = true;

                                        renameTempOutputFile(previousTempOutputFilePath, args[2]);
                                    }

                                    deleteTempFilesAndDirectory();
                                }

                                ErrorLogger.completeErrorLog();
                                isErrorLogCompleted = true;
                                System.out.println(getSortReport());
                            } else if (args[1].equals("-i")) {
                                checkOutputFile(args[2]);
                                checkInputFiles(args);
                                ErrorLogger.beginErrorLog();
                                isErrorLogCompleted = false;

                                if (args.length == 4) {
                                    IntegersSort.integersSingleFileAscendingSort(args[2], args[3]);
                                } else if (args.length == 5) {
                                    IntegersSort.integersFirstTwoFilesAscendingSort(args[2], args[3], args[4]);
                                } else {
                                    createTempDirectory();
                                    String tempOutputFilePath = "Temp\\1.txt";
                                    String previousTempOutputFilePath = "Temp\\1.txt";
                                    IntegersSort.integersFirstTwoFilesAscendingSort(tempOutputFilePath, args[3], args[4]);

                                    try {
                                        for (int i = 5; i < args.length; ++i) {
                                            previousTempOutputFilePath = "Temp\\" + (i - 4) + ".txt";
                                            tempOutputFilePath = "Temp\\" + (i - 3) + ".txt";
                                            ErrorLogger.increaseFilePairCount();
                                            IntegersSort.integersRemainingFilesAscendingSort(tempOutputFilePath, previousTempOutputFilePath, args[i]);
                                        }

                                        renameTempOutputFile(tempOutputFilePath, args[2]);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        isSortCompleteWithError = true;

                                        renameTempOutputFile(previousTempOutputFilePath, args[2]);
                                    }

                                    deleteTempFilesAndDirectory();
                                }

                                ErrorLogger.completeErrorLog();
                                isErrorLogCompleted = true;
                                System.out.println(getSortReport());
                            } else {
                                printIncorrectArgumentsError();
                            }
                            break;
                        case "-d":
                            if (args[1].equals("-s")) {
                                checkOutputFile(args[2]);
                                checkInputFiles(args);
                                ErrorLogger.beginErrorLog();
                                isErrorLogCompleted = false;

                                if (args.length == 4) {
                                    StringsSort.stringsSingleFileDescendingSort(args[2], args[3]);
                                } else if (args.length == 5) {
                                    StringsSort.stringsFirstTwoFilesDescendingSort(args[2], args[3], args[4]);
                                } else {
                                    createTempDirectory();
                                    String tempOutputFilePath = "Temp\\1.txt";
                                    String previousTempOutputFilePath = "Temp\\1.txt";
                                    StringsSort.stringsFirstTwoFilesDescendingSort(tempOutputFilePath, args[3], args[4]);

                                    try {
                                        for (int i = 5; i < args.length; ++i) {
                                            previousTempOutputFilePath = "Temp\\" + (i - 4) + ".txt";
                                            tempOutputFilePath = "Temp\\" + (i - 3) + ".txt";
                                            ErrorLogger.increaseFilePairCount();
                                            StringsSort.stringsRemainingFilesDescendingSort(tempOutputFilePath, previousTempOutputFilePath, args[i]);
                                        }

                                        renameTempOutputFile(tempOutputFilePath, args[2]);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        isSortCompleteWithError = true;

                                        renameTempOutputFile(previousTempOutputFilePath, args[2]);
                                    }

                                    deleteTempFilesAndDirectory();
                                }

                                ErrorLogger.completeErrorLog();
                                isErrorLogCompleted = true;
                                System.out.println(getSortReport());
                            } else if (args[1].equals("-i")) {
                                checkOutputFile(args[2]);
                                checkInputFiles(args);
                                ErrorLogger.beginErrorLog();
                                isErrorLogCompleted = false;

                                if (args.length == 4) {
                                    IntegersSort.integersSingleFileDescendingSort(args[2], args[3]);
                                } else if (args.length == 5) {
                                    IntegersSort.integersFirstTwoFilesDescendingSort(args[2], args[3], args[4]);
                                } else {
                                    createTempDirectory();
                                    String tempOutputFilePath = "Temp\\1.txt";
                                    String previousTempOutputFilePath = "Temp\\1.txt";
                                    IntegersSort.integersFirstTwoFilesDescendingSort(tempOutputFilePath, args[3], args[4]);

                                    try {
                                        for (int i = 5; i < args.length; ++i) {
                                            previousTempOutputFilePath = "Temp\\" + (i - 4) + ".txt";
                                            tempOutputFilePath = "Temp\\" + (i - 3) + ".txt";
                                            ErrorLogger.increaseFilePairCount();
                                            IntegersSort.integersRemainingFilesDescendingSort(tempOutputFilePath, previousTempOutputFilePath, args[i]);
                                        }

                                        renameTempOutputFile(tempOutputFilePath, args[2]);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        isSortCompleteWithError = true;

                                        renameTempOutputFile(previousTempOutputFilePath, args[2]);
                                    }

                                    deleteTempFilesAndDirectory();
                                }

                                ErrorLogger.completeErrorLog();
                                isErrorLogCompleted = true;
                                System.out.println(getSortReport());
                            } else {
                                printIncorrectArgumentsError();
                            }
                            break;
                        case "-help":
                            printHelp();
                            break;
                        default:
                            printIncorrectArgumentsError();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();

                    if (!isErrorLogCompleted) {
                        try {
                            ErrorLogger.completeErrorLog();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}