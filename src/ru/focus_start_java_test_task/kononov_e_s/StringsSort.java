package ru.focus_start_java_test_task.kononov_e_s;

import java.io.*;

class StringsSort {
    static void stringsSingleFileAscendingSort(String outputFilePath, String inputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String currentString = reader.readLine();
            String previousString = "0";

            while (currentString != null) {
                if (!isStringEmptyOrContainsWhitespaces(currentString)) {
                    writer.println(currentString);
                    previousString = currentString;
                    currentString = reader.readLine();
                    break;
                }

                errorLogger.println(ErrorLogger.getErrorString(currentString, 1, 2));
                currentString = reader.readLine();
            }

            while (currentString != null) {
                if (isStringEmptyOrContainsWhitespaces(currentString)) {
                    errorLogger.println(ErrorLogger.getErrorString(currentString, 1, 2));
                } else if (currentString.compareTo(previousString) < 0) {
                    errorLogger.println(ErrorLogger.getErrorString(currentString, 1, 1));
                } else {
                    writer.println(currentString);
                    previousString = currentString;
                }

                currentString = reader.readLine();
            }
        }
    }

    static void stringsFirstTwoFilesAscendingSort(String outputFilePath, String inputFile1Path, String inputFile2Path) throws IOException {
        try (BufferedReader file1Reader = new BufferedReader(new FileReader(inputFile1Path));
             BufferedReader file2Reader = new BufferedReader(new FileReader(inputFile2Path));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String file1CurrentString = file1Reader.readLine();
            String file2CurrentString = file2Reader.readLine();
            boolean isFile1CurrentStringCorrect = false;
            boolean isFile2CurrentStringCorrect = false;
            String file1String = "0";
            String file2String = "0";
            String file1PreviousString = "0";
            String file2PreviousString = "0";

            while (file1CurrentString != null) {
                if (!isStringEmptyOrContainsWhitespaces(file1CurrentString)) {
                    isFile1CurrentStringCorrect = true;
                    file1String = file1CurrentString;
                    file1PreviousString = file1CurrentString;
                    break;
                }

                errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 2));
                file1CurrentString = file1Reader.readLine();
            }

            while (file2CurrentString != null) {
                if (!isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                    isFile2CurrentStringCorrect = true;
                    file2String = file2CurrentString;
                    file2PreviousString = file2CurrentString;
                    break;
                }

                errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                file2CurrentString = file2Reader.readLine();
            }

            while (file1CurrentString != null) {
                while (!isFile1CurrentStringCorrect && file1CurrentString != null) {
                    if (isStringEmptyOrContainsWhitespaces(file1CurrentString)) {
                        errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 2));
                        file1CurrentString = file1Reader.readLine();
                        continue;
                    }

                    if (file1CurrentString.compareTo(file1PreviousString) < 0) {
                        errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 1));
                        file1CurrentString = file1Reader.readLine();
                        continue;
                    }

                    isFile1CurrentStringCorrect = true;
                    file1String = file1CurrentString;
                    file1PreviousString = file1CurrentString;
                }

                if (!isFile1CurrentStringCorrect) {
                    break;
                }

                while (!isFile2CurrentStringCorrect && file2CurrentString != null) {
                    if (isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                        file2CurrentString = file2Reader.readLine();
                        continue;
                    }

                    if (file2CurrentString.compareTo(file2PreviousString) < 0) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                        file2CurrentString = file2Reader.readLine();
                        continue;
                    }

                    isFile2CurrentStringCorrect = true;
                    file2String = file2CurrentString;
                    file2PreviousString = file2CurrentString;
                }

                if (!isFile2CurrentStringCorrect) {
                    writer.println(file1String);
                    file1CurrentString = file1Reader.readLine();

                    while (file1CurrentString != null) {
                        if (isStringEmptyOrContainsWhitespaces(file1CurrentString)) {
                            errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 2));
                        } else if (file1CurrentString.compareTo(file1PreviousString) < 0) {
                            errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 1));
                        } else {
                            writer.println(file1CurrentString);
                            file1PreviousString = file1CurrentString;
                        }

                        file1CurrentString = file1Reader.readLine();
                    }

                    break;
                }

                if (file1String.compareTo(file2String) <= 0) {
                    writer.println(file1String);
                    file1CurrentString = file1Reader.readLine();
                    isFile1CurrentStringCorrect = false;
                } else {
                    writer.println(file2String);
                    file2CurrentString = file2Reader.readLine();
                    isFile2CurrentStringCorrect = false;
                }
            }

            if (isFile2CurrentStringCorrect) {
                writer.println(file2String);
                file2CurrentString = file2Reader.readLine();
            }

            while (file2CurrentString != null) {
                if (isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                } else if (file2CurrentString.compareTo(file2PreviousString) < 0) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                } else {
                    writer.println(file2CurrentString);
                    file2PreviousString = file2CurrentString;
                }

                file2CurrentString = file2Reader.readLine();
            }
        }
    }

    static void stringsRemainingFilesAscendingSort(String outputFilePath, String inputFile1Path, String inputFile2Path) throws IOException {
        try (BufferedReader file1Reader = new BufferedReader(new FileReader(inputFile1Path));
             BufferedReader file2Reader = new BufferedReader(new FileReader(inputFile2Path));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String file1CurrentString = file1Reader.readLine();
            String file2CurrentString = file2Reader.readLine();
            boolean isFile2CurrentStringCorrect = false;
            String file2String = "0";
            String file2PreviousString = "0";

            while (file2CurrentString != null) {
                if (!isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                    isFile2CurrentStringCorrect = true;
                    file2String = file2CurrentString;
                    file2PreviousString = file2CurrentString;
                    break;
                }

                errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                file2CurrentString = file2Reader.readLine();
            }

            while (file1CurrentString != null) {
                while (!isFile2CurrentStringCorrect && file2CurrentString != null) {
                    if (isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                        file2CurrentString = file2Reader.readLine();
                        continue;
                    }

                    if (file2CurrentString.compareTo(file2PreviousString) < 0) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                        file2CurrentString = file2Reader.readLine();
                        continue;
                    }

                    isFile2CurrentStringCorrect = true;
                    file2String = file2CurrentString;
                    file2PreviousString = file2CurrentString;
                }

                if (!isFile2CurrentStringCorrect) {
                    writer.println(file1CurrentString);

                    while ((file1CurrentString = file1Reader.readLine()) != null) {
                        writer.println(file1CurrentString);
                    }

                    break;
                }

                if (file1CurrentString.compareTo(file2String) <= 0) {
                    writer.println(file1CurrentString);
                    file1CurrentString = file1Reader.readLine();
                } else {
                    writer.println(file2String);
                    file2CurrentString = file2Reader.readLine();
                    isFile2CurrentStringCorrect = false;
                }
            }

            if (isFile2CurrentStringCorrect) {
                writer.println(file2String);
                file2CurrentString = file2Reader.readLine();
            }

            while (file2CurrentString != null) {
                if (isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                } else if (file2CurrentString.compareTo(file2PreviousString) < 0) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                } else {
                    writer.println(file2CurrentString);
                    file2PreviousString = file2CurrentString;
                }

                file2CurrentString = file2Reader.readLine();
            }
        }
    }

    static void stringsSingleFileDescendingSort(String outputFilePath, String inputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String currentString = reader.readLine();
            String previousString = "0";

            while (currentString != null) {
                if (!isStringEmptyOrContainsWhitespaces(currentString)) {
                    writer.println(currentString);
                    previousString = currentString;
                    currentString = reader.readLine();
                    break;
                }

                errorLogger.println(ErrorLogger.getErrorString(currentString, 1, 2));
                currentString = reader.readLine();
            }

            while (currentString != null) {
                if (isStringEmptyOrContainsWhitespaces(currentString)) {
                    errorLogger.println(ErrorLogger.getErrorString(currentString, 1, 2));
                } else if (currentString.compareTo(previousString) > 0) {
                    errorLogger.println(ErrorLogger.getErrorString(currentString, 1, 1));
                } else {
                    writer.println(currentString);
                    previousString = currentString;
                }

                currentString = reader.readLine();
            }
        }
    }

    static void stringsFirstTwoFilesDescendingSort(String outputFilePath, String inputFile1Path, String inputFile2Path) throws IOException {
        try (BufferedReader file1Reader = new BufferedReader(new FileReader(inputFile1Path));
             BufferedReader file2Reader = new BufferedReader(new FileReader(inputFile2Path));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String file1CurrentString = file1Reader.readLine();
            String file2CurrentString = file2Reader.readLine();
            boolean isFile1CurrentStringCorrect = false;
            boolean isFile2CurrentStringCorrect = false;
            String file1String = "0";
            String file2String = "0";
            String file1PreviousString = "0";
            String file2PreviousString = "0";

            while (file1CurrentString != null) {
                if (!isStringEmptyOrContainsWhitespaces(file1CurrentString)) {
                    isFile1CurrentStringCorrect = true;
                    file1String = file1CurrentString;
                    file1PreviousString = file1CurrentString;
                    break;
                }

                errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 2));
                file1CurrentString = file1Reader.readLine();
            }

            while (file2CurrentString != null) {
                if (!isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                    isFile2CurrentStringCorrect = true;
                    file2String = file2CurrentString;
                    file2PreviousString = file2CurrentString;
                    break;
                }

                errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                file2CurrentString = file2Reader.readLine();
            }

            while (file1CurrentString != null) {
                while (!isFile1CurrentStringCorrect && file1CurrentString != null) {
                    if (isStringEmptyOrContainsWhitespaces(file1CurrentString)) {
                        errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 2));
                        file1CurrentString = file1Reader.readLine();
                        continue;
                    }

                    if (file1CurrentString.compareTo(file1PreviousString) > 0) {
                        errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 1));
                        file1CurrentString = file1Reader.readLine();
                        continue;
                    }

                    isFile1CurrentStringCorrect = true;
                    file1String = file1CurrentString;
                    file1PreviousString = file1CurrentString;
                }

                if (!isFile1CurrentStringCorrect) {
                    break;
                }

                while (!isFile2CurrentStringCorrect && file2CurrentString != null) {
                    if (isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                        file2CurrentString = file2Reader.readLine();
                        continue;
                    }

                    if (file2CurrentString.compareTo(file2PreviousString) > 0) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                        file2CurrentString = file2Reader.readLine();
                        continue;
                    }

                    isFile2CurrentStringCorrect = true;
                    file2String = file2CurrentString;
                    file2PreviousString = file2CurrentString;
                }

                if (!isFile2CurrentStringCorrect) {
                    writer.println(file1String);
                    file1CurrentString = file1Reader.readLine();

                    while (file1CurrentString != null) {
                        if (isStringEmptyOrContainsWhitespaces(file1CurrentString)) {
                            errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 2));
                        } else if (file1CurrentString.compareTo(file1PreviousString) > 0) {
                            errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 1));
                        } else {
                            writer.println(file1CurrentString);
                            file1PreviousString = file1CurrentString;
                        }

                        file1CurrentString = file1Reader.readLine();
                    }

                    break;
                }

                if (file1String.compareTo(file2String) >= 0) {
                    writer.println(file1String);
                    file1CurrentString = file1Reader.readLine();
                    isFile1CurrentStringCorrect = false;
                } else {
                    writer.println(file2String);
                    file2CurrentString = file2Reader.readLine();
                    isFile2CurrentStringCorrect = false;
                }
            }

            if (isFile2CurrentStringCorrect) {
                writer.println(file2String);
                file2CurrentString = file2Reader.readLine();
            }

            while (file2CurrentString != null) {
                if (isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                } else if (file2CurrentString.compareTo(file2PreviousString) > 0) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                } else {
                    writer.println(file2CurrentString);
                    file2PreviousString = file2CurrentString;
                }

                file2CurrentString = file2Reader.readLine();
            }
        }
    }

    static void stringsRemainingFilesDescendingSort(String outputFilePath, String inputFile1Path, String inputFile2Path) throws IOException {
        try (BufferedReader file1Reader = new BufferedReader(new FileReader(inputFile1Path));
             BufferedReader file2Reader = new BufferedReader(new FileReader(inputFile2Path));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String file1CurrentString = file1Reader.readLine();
            String file2CurrentString = file2Reader.readLine();
            boolean isFile2CurrentStringCorrect = false;
            String file2String = "0";
            String file2PreviousString = "0";

            while (file2CurrentString != null) {
                if (!isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                    isFile2CurrentStringCorrect = true;
                    file2String = file2CurrentString;
                    file2PreviousString = file2CurrentString;
                    break;
                }

                errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                file2CurrentString = file2Reader.readLine();
            }

            while (file1CurrentString != null) {
                while (!isFile2CurrentStringCorrect && file2CurrentString != null) {
                    if (isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                        file2CurrentString = file2Reader.readLine();
                        continue;
                    }

                    if (file2CurrentString.compareTo(file2PreviousString) > 0) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                        file2CurrentString = file2Reader.readLine();
                        continue;
                    }

                    isFile2CurrentStringCorrect = true;
                    file2String = file2CurrentString;
                    file2PreviousString = file2CurrentString;
                }

                if (!isFile2CurrentStringCorrect) {
                    writer.println(file1CurrentString);

                    while ((file1CurrentString = file1Reader.readLine()) != null) {
                        writer.println(file1CurrentString);
                    }

                    break;
                }

                if (file1CurrentString.compareTo(file2String) >= 0) {
                    writer.println(file1CurrentString);
                    file1CurrentString = file1Reader.readLine();
                } else {
                    writer.println(file2String);
                    file2CurrentString = file2Reader.readLine();
                    isFile2CurrentStringCorrect = false;
                }
            }

            if (isFile2CurrentStringCorrect) {
                writer.println(file2String);
                file2CurrentString = file2Reader.readLine();
            }

            while (file2CurrentString != null) {
                if (isStringEmptyOrContainsWhitespaces(file2CurrentString)) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 2));
                } else if (file2CurrentString.compareTo(file2PreviousString) > 0) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                } else {
                    writer.println(file2CurrentString);
                    file2PreviousString = file2CurrentString;
                }

                file2CurrentString = file2Reader.readLine();
            }
        }
    }

    static boolean isStringEmptyOrContainsWhitespaces(String string) {
        if (string.isEmpty()) {
            return true;
        }

        for (int i = 0; i < string.length(); ++i) {
            if (Character.isWhitespace(string.charAt(i))) {
                return true;
            }
        }

        return false;
    }
}