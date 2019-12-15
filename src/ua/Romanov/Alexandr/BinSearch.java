package ua.Romanov.Alexandr;

import java.util.Scanner;

public class BinSearch {
    // Слияние двух подмассивов arr[].
    // Первый подмассив arr[l..m]
    // Второй подмассив arr[m+1..r]
    static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two помассивы to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Создание временных массивов */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Копирование данных во временные массивы*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        /* Слияние временных массивов */

        // Первоначальные индексы первого и второго подмассивов
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Копирование оставшихся элементов L[], если таковые были */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Копирование оставшихся элементов R[], если таковые были */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Основная функция, которая сортирует arr[l..r] с использованием
    // слияния()
    static void sort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Сортировать первую и вторую половинки
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Слияние отсортированных половинок
            merge(arr, l, m, r);
        }
    }

    public static int binarySearch(int arr[], int elementToSearch) {

        int firstIndex = 0;
        int lastIndex = arr.length - 1;

        // условие прекращения (элемент не представлен)
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            // если средний элемент - целевой элемент, вернуть его индекс
            if (arr[middleIndex] == elementToSearch) {
                return middleIndex;
            }

            // если средний элемент меньше
            // направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (arr[middleIndex] < elementToSearch)
                firstIndex = middleIndex + 1;

                // если средний элемент больше
                // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (arr[middleIndex] > elementToSearch)
                lastIndex = middleIndex - 1;

        }
        return -1;
    }

    /* выод массива в консоль */
    static void printArray(int arr[]) {
        //System.out.print("Исходный массив: ");
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static void print(int elementToSearch, int index) {
        if (index == -1) {
            System.out.println(elementToSearch + " not found.");
        } else {
            System.out.println(elementToSearch + " found at index: " + index);
        }
    }

    public static void main(String[] args) {

        int[] arr = {4, 90, -7, 58, 78, 2, 34, 56, -56};
        System.out.print("Исходный массив: ");
        printArray(arr);
        System.out.println("чтобы искать число, необходимо сначала отсортировать массив по порядку");
        sort(arr, 0, arr.length - 1);
        System.out.print("Отсортированный массив: ");
        printArray(arr);
        System.out.print("Введите число: ");
        for (; ; ) {
            Scanner scanner = new Scanner(System.in);
            int elementToSearch = scanner.nextInt();
            int index = binarySearch(arr, elementToSearch);
            print(elementToSearch, index);
        }


    }
}
