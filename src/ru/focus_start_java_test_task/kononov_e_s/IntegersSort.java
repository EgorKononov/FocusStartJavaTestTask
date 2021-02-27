package ru.focus_start_java_test_task.kononov_e_s;

import java.io.*;

class IntegersSort {
    static void integersSingleFileAscendingSort(String outputFilePath, String inputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String currentString = reader.readLine();
            int previousInt = Integer.MIN_VALUE;

            while (currentString != null) {
                try {
                    int currentInt = Integer.parseInt(currentString);

                    if (currentInt >= previousInt) {
                        writer.println(currentInt);
                        previousInt = currentInt;
                    } else {
                        errorLogger.println(ErrorLogger.getErrorString(currentString, 1, 1));
                    }
                } catch (NumberFormatException e) {
                    errorLogger.println(ErrorLogger.getErrorString(currentString, 1, 3));
                }

                currentString = reader.readLine();
            }
        }
    }

    static void integersFirstTwoFilesAscendingSort(String outputFilePath, String inputFile1Path, String inputFile2Path) throws IOException {
        try (BufferedReader file1Reader = new BufferedReader(new FileReader(inputFile1Path));
             BufferedReader file2Reader = new BufferedReader(new FileReader(inputFile2Path));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String file1CurrentString = file1Reader.readLine();
            String file2CurrentString = file2Reader.readLine();
            boolean isFile1CurrentStringCorrect = false;
            boolean isFile2CurrentStringCorrect = false;
            int currentFile1Int = 0;
            int currentFile2Int = 0;
            int previousFile1Int = Integer.MIN_VALUE;
            int previousFile2Int = Integer.MIN_VALUE;

            while (file1CurrentString != null) {
                while (!isFile1CurrentStringCorrect && file1CurrentString != null) {
                    try {
                        currentFile1Int = Integer.parseInt(file1CurrentString);

                        if (currentFile1Int >= previousFile1Int) {
                            isFile1CurrentStringCorrect = true;
                            previousFile1Int = currentFile1Int;
                        } else {
                            errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 1));
                            file1CurrentString = file1Reader.readLine();
                        }
                    } catch (NumberFormatException e) {
                        errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 3));
                        file1CurrentString = file1Reader.readLine();
                    }
                }

                if (!isFile1CurrentStringCorrect) {
                    break;
                }

                while (!isFile2CurrentStringCorrect && file2CurrentString != null) {
                    try {
                        currentFile2Int = Integer.parseInt(file2CurrentString);

                        if (currentFile2Int >= previousFile2Int) {
                            isFile2CurrentStringCorrect = true;
                            previousFile2Int = currentFile2Int;
                        } else {
                            errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                            file2CurrentString = file2Reader.readLine();
                        }
                    } catch (NumberFormatException e) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 3));
                        file2CurrentString = file2Reader.readLine();
                    }
                }

                if (!isFile2CurrentStringCorrect) {
                    writer.println(currentFile1Int);
                    file1CurrentString = file1Reader.readLine();

                    while (file1CurrentString != null) {
                        try {
                            currentFile1Int = Integer.parseInt(file1CurrentString);

                            if (currentFile1Int >= previousFile1Int) {
                                writer.println(currentFile1Int);
                                previousFile1Int = currentFile1Int;
                            } else {
                                errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 1));
                            }
                        } catch (NumberFormatException e) {
                            errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 3));
                        }

                        file1CurrentString = file1Reader.readLine();
                    }

                    break;
                }

                if (currentFile1Int <= currentFile2Int) {
                    writer.println(currentFile1Int);
                    file1CurrentString = file1Reader.readLine();
                    isFile1CurrentStringCorrect = false;
                } else {
                    writer.println(currentFile2Int);
                    file2CurrentString = file2Reader.readLine();
                    isFile2CurrentStringCorrect = false;
                }
            }

            if (isFile2CurrentStringCorrect) {
                writer.println(currentFile2Int);
                file2CurrentString = file2Reader.readLine();
            }

            while (file2CurrentString != null) {
                try {
                    currentFile2Int = Integer.parseInt(file2CurrentString);

                    if (currentFile2Int >= previousFile2Int) {
                        writer.println(currentFile2Int);
                        previousFile2Int = currentFile2Int;
                    } else {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                    }
                } catch (NumberFormatException e) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 3));
                }

                file2CurrentString = file2Reader.readLine();
            }
        }
    }

    static void integersRemainingFilesAscendingSort(String outputFilePath, String inputFile1Path, String inputFile2Path) throws IOException {
        try (BufferedReader file1Reader = new BufferedReader(new FileReader(inputFile1Path));
             BufferedReader file2Reader = new BufferedReader(new FileReader(inputFile2Path));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String file1CurrentString = file1Reader.readLine();
            String file2CurrentString = file2Reader.readLine();
            boolean isFile2CurrentStringCorrect = false;
            int currentFile2Int = 0;
            int previousFile2Int = Integer.MIN_VALUE;

            while (file1CurrentString != null) {
                int currentFile1Int = Integer.parseInt(file1CurrentString);

                while (!isFile2CurrentStringCorrect && file2CurrentString != null) {
                    try {
                        currentFile2Int = Integer.parseInt(file2CurrentString);

                        if (currentFile2Int >= previousFile2Int) {
                            isFile2CurrentStringCorrect = true;
                            previousFile2Int = currentFile2Int;
                        } else {
                            errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                            file2CurrentString = file2Reader.readLine();
                        }
                    } catch (NumberFormatException e) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 3));
                        file2CurrentString = file2Reader.readLine();
                    }
                }

                if (!isFile2CurrentStringCorrect) {
                    writer.println(currentFile1Int);

                    while ((file1CurrentString = file1Reader.readLine()) != null) {
                        writer.println(file1CurrentString);
                    }

                    break;
                }

                if (currentFile1Int <= currentFile2Int) {
                    writer.println(currentFile1Int);
                    file1CurrentString = file1Reader.readLine();
                } else {
                    writer.println(currentFile2Int);
                    file2CurrentString = file2Reader.readLine();
                    isFile2CurrentStringCorrect = false;
                }
            }

            if (isFile2CurrentStringCorrect) {
                writer.println(currentFile2Int);
                file2CurrentString = file2Reader.readLine();
            }

            while (file2CurrentString != null) {
                try {
                    currentFile2Int = Integer.parseInt(file2CurrentString);

                    if (currentFile2Int >= previousFile2Int) {
                        writer.println(currentFile2Int);
                        previousFile2Int = currentFile2Int;
                    } else {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                    }
                } catch (NumberFormatException e) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 3));
                }

                file2CurrentString = file2Reader.readLine();
            }
        }
    }

    static void integersSingleFileDescendingSort(String outputFilePath, String inputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String currentString = reader.readLine();
            int previousInt = Integer.MAX_VALUE;

            while (currentString != null) {
                try {
                    int currentInt = Integer.parseInt(currentString);

                    if (currentInt <= previousInt) {
                        writer.println(currentInt);
                        previousInt = currentInt;
                    } else {
                        errorLogger.println(ErrorLogger.getErrorString(currentString, 1, 1));
                    }
                } catch (NumberFormatException e) {
                    errorLogger.println(ErrorLogger.getErrorString(currentString, 1, 3));
                }

                currentString = reader.readLine();
            }
        }
    }

    static void integersFirstTwoFilesDescendingSort(String outputFilePath, String inputFile1Path, String inputFile2Path) throws IOException {
        try (BufferedReader file1Reader = new BufferedReader(new FileReader(inputFile1Path));
             BufferedReader file2Reader = new BufferedReader(new FileReader(inputFile2Path));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String file1CurrentString = file1Reader.readLine();
            String file2CurrentString = file2Reader.readLine();
            boolean isFile1CurrentStringCorrect = false;
            boolean isFile2CurrentStringCorrect = false;
            int currentFile1Int = 0;
            int currentFile2Int = 0;
            int previousFile1Int = Integer.MAX_VALUE;
            int previousFile2Int = Integer.MAX_VALUE;

            while (file1CurrentString != null) {
                while (!isFile1CurrentStringCorrect && file1CurrentString != null) {
                    try {
                        currentFile1Int = Integer.parseInt(file1CurrentString);

                        if (currentFile1Int <= previousFile1Int) {
                            isFile1CurrentStringCorrect = true;
                            previousFile1Int = currentFile1Int;
                        } else {
                            errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 1));
                            file1CurrentString = file1Reader.readLine();
                        }
                    } catch (NumberFormatException e) {
                        errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 3));
                        file1CurrentString = file1Reader.readLine();
                    }
                }

                if (!isFile1CurrentStringCorrect) {
                    break;
                }

                while (!isFile2CurrentStringCorrect && file2CurrentString != null) {
                    try {
                        currentFile2Int = Integer.parseInt(file2CurrentString);

                        if (currentFile2Int <= previousFile2Int) {
                            isFile2CurrentStringCorrect = true;
                            previousFile2Int = currentFile2Int;
                        } else {
                            errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                            file2CurrentString = file2Reader.readLine();
                        }
                    } catch (NumberFormatException e) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 3));
                        file2CurrentString = file2Reader.readLine();
                    }
                }

                if (!isFile2CurrentStringCorrect) {
                    writer.println(currentFile1Int);
                    file1CurrentString = file1Reader.readLine();

                    while (file1CurrentString != null) {
                        try {
                            currentFile1Int = Integer.parseInt(file1CurrentString);

                            if (currentFile1Int <= previousFile1Int) {
                                writer.println(currentFile1Int);
                                previousFile1Int = currentFile1Int;
                            } else {
                                errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 1));
                            }
                        } catch (NumberFormatException e) {
                            errorLogger.println(ErrorLogger.getErrorString(file1CurrentString, 1, 3));
                        }

                        file1CurrentString = file1Reader.readLine();
                    }

                    break;
                }

                if (currentFile1Int >= currentFile2Int) {
                    writer.println(currentFile1Int);
                    file1CurrentString = file1Reader.readLine();
                    isFile1CurrentStringCorrect = false;
                } else {
                    writer.println(currentFile2Int);
                    file2CurrentString = file2Reader.readLine();
                    isFile2CurrentStringCorrect = false;
                }
            }

            if (isFile2CurrentStringCorrect) {
                writer.println(currentFile2Int);
                file2CurrentString = file2Reader.readLine();
            }

            while (file2CurrentString != null) {
                try {
                    currentFile2Int = Integer.parseInt(file2CurrentString);

                    if (currentFile2Int <= previousFile2Int) {
                        writer.println(currentFile2Int);
                        previousFile2Int = currentFile2Int;
                    } else {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                    }
                } catch (NumberFormatException e) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 3));
                }

                file2CurrentString = file2Reader.readLine();
            }
        }
    }

    static void integersRemainingFilesDescendingSort(String outputFilePath, String inputFile1Path, String inputFile2Path) throws IOException {
        try (BufferedReader file1Reader = new BufferedReader(new FileReader(inputFile1Path));
             BufferedReader file2Reader = new BufferedReader(new FileReader(inputFile2Path));
             PrintWriter writer = new PrintWriter(outputFilePath);
             PrintWriter errorLogger = new PrintWriter(new FileWriter("Error Log.txt", true))) {
            String file1CurrentString = file1Reader.readLine();
            String file2CurrentString = file2Reader.readLine();
            boolean isFile2CurrentStringCorrect = false;
            int currentFile2Int = 0;
            int previousFile2Int = Integer.MAX_VALUE;

            while (file1CurrentString != null) {
                int currentFile1Int = Integer.parseInt(file1CurrentString);

                while (!isFile2CurrentStringCorrect && file2CurrentString != null) {
                    try {
                        currentFile2Int = Integer.parseInt(file2CurrentString);

                        if (currentFile2Int <= previousFile2Int) {
                            isFile2CurrentStringCorrect = true;
                            previousFile2Int = currentFile2Int;
                        } else {
                            errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 1));
                            file2CurrentString = file2Reader.readLine();
                        }
                    } catch (NumberFormatException e) {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 2, 3));
                        file2CurrentString = file2Reader.readLine();
                    }
                }

                if (!isFile2CurrentStringCorrect) {
                    writer.println(currentFile1Int);

                    while ((file1CurrentString = file1Reader.readLine()) != null) {
                        writer.println(file1CurrentString);
                    }

                    break;
                }

                if (currentFile1Int >= currentFile2Int) {
                    writer.println(currentFile1Int);
                    file1CurrentString = file1Reader.readLine();
                } else {
                    writer.println(currentFile2Int);
                    file2CurrentString = file2Reader.readLine();
                    isFile2CurrentStringCorrect = false;
                }
            }

            if (isFile2CurrentStringCorrect) {
                writer.println(currentFile2Int);
                file2CurrentString = file2Reader.readLine();
            }

            while (file2CurrentString != null) {
                try {
                    currentFile2Int = Integer.parseInt(file2CurrentString);

                    if (currentFile2Int <= previousFile2Int) {
                        writer.println(currentFile2Int);
                        previousFile2Int = currentFile2Int;
                    } else {
                        errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 1, 1));
                    }
                } catch (NumberFormatException e) {
                    errorLogger.println(ErrorLogger.getErrorString(file2CurrentString, 1, 3));
                }

                file2CurrentString = file2Reader.readLine();
            }
        }
    }
}