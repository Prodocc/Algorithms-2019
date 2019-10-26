package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;

import static lesson1.Sorts.quickSort;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     * Трудоемкость O(n * log(n))
     * Ресурсоемкость O(n)
     */
    static public void sortTimes(String inputName, String outputName) {
        List<String> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(inputName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.matches("(\\d{2}:\\d{2}:\\d{2} [AM|PM]+)")) {
                    throw new IllegalArgumentException();
                }
                list.add(line);
            }
            int[] arr = new int[list.size()];
            String number;
            int hours, minutes, seconds;
            for (int i = 0; i < list.size(); i++) {
                String[] tmpArr;
                number = list.get(i).split(" ")[0].trim();
                tmpArr = number.split(":");
                hours = Integer.parseInt(tmpArr[0]);
                minutes = Integer.parseInt(tmpArr[1]);
                seconds = Integer.parseInt(tmpArr[2]);
                if (list.get(i).contains("PM")) {
                    if (hours != 12) {
                        hours += 12;
                    }
                } else if (list.get(i).contains("AM")) {
                    if (hours == 12) {
                        hours = 0;
                    }
                }
                arr[i] = hours * 3600 + minutes * 60 + seconds;
            }

            quickSort(arr);

            try (FileWriter out = new FileWriter(outputName)) {
                for (int i = 0; i < arr.length; i++) {
                    hours = arr[i] / 3600;
                    minutes = arr[i] / 60 % 60;
                    seconds = arr[i] % 3600 % 60;
                    if (hours > 12) {
                        out.write(String.format("%02d:%02d:%02d", hours - 12, minutes, seconds) + " PM" + "\n");
                    } else if (hours < 12 && hours > 0) {
                        out.write(String.format("%02d:%02d:%02d", hours, minutes, seconds) + " AM" + "\n");
                    } else if (hours == 0) {
                        out.write(String.format("%02d:%02d:%02d", hours + 12, minutes, seconds) + " AM" + "\n");
                    } else if (hours == 12) {
                        out.write(String.format("%02d:%02d:%02d", hours, minutes, seconds) + " PM" + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     * Трудоемкость O(n)
     * Ресурсоемкость O(n)
     */
    static public void sortTemperatures(String inputName, String outputName) {
        List<Double> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(inputName))) {
            while (scanner.hasNext()) {
                double number = Double.parseDouble(scanner.next());
                list.add(number);
            }

            int[] arr = new int[list.size()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int)((1000 + list.get(i)) * 10);
            }

            arr = countingSort(arr, 77300);

            double[] resultArray = new double[list.size()];
            for (int i = 0; i < resultArray.length; i++) {
                resultArray[i] = (double)(arr[i] - 10000) / 10;
            }

            try (FileWriter out = new FileWriter(outputName)) {
                for (int i = 0; i < list.size(); i++) {
                    out.write(resultArray[i] + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int[] countingSort(int[] elements, int limit) {
        int[] count = new int[limit + 1];
        for (int element : elements) {
            count[element]++;
        }
        for (int j = 1; j <= limit; j++) {
            count[j] += count[j - 1];
        }
        int[] out = new int[elements.length];
        for (int j = elements.length - 1; j >= 0; j--) {
            out[count[elements[j]] - 1] = elements[j];
            count[elements[j]]--;
        }
        return out;
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     * Трудоемкость O(n + m), m - длина последовательности минимального числа
     * Ресурсоемкость O(n)
     */
    static public void sortSequence(String inputName, String outputName) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(inputName))) {
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                list.add(number);
                if (map.containsKey(number)) {
                    map.put(number, map.get(number) + 1);
                } else {
                    map.put(number, 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        int maxValue = 0;
        int minKey = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxValue || entry.getValue() == maxValue && entry.getKey() < minKey) {
                maxValue = entry.getValue();
                minKey = entry.getKey();
            }
        }

        try (FileWriter out = new FileWriter(outputName)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != minKey) {
                    out.write(list.get(i) + "\n");
                }
            }
            for (int i = 0; i < maxValue; i++) {
                out.write(minKey + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     * Трудоемкость O(n)
     * Ресурсоемкость O(1)
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        int fIndex = 0;
        int sIndex = first.length;
        int rIndex = 0;
        while (rIndex < sIndex) {
            if (fIndex == first.length) {
                System.arraycopy(second, sIndex, second, rIndex, second.length - sIndex);
                break;
            }
            if (sIndex == second.length) {
                System.arraycopy(first, fIndex, second, rIndex, first.length - fIndex);
                break;
            }
            if (first[fIndex].compareTo(second[sIndex]) < 0) {
                second[rIndex] = first[fIndex];
                fIndex++;
            } else {
                second[rIndex] = second[sIndex];
                sIndex++;
            }
            rIndex++;
        }
    }
}
