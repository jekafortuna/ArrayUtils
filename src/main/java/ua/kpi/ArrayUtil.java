package ua.kpi;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Evgeniy on 17.11.2016.
 * This class contains  static method utils for arrays
 */
public class ArrayUtil {

    /**
     * Method finds equal elements in both arrays
     * @param array1 first array
     * @param array2 second array
     * @return list of equal elements of arrays
     */
    public static List<Integer> findEqualsValues(int array1[], int array2[]){
        Arrays.sort(array1);
        Arrays.sort(array2);
        int index1 = 0;
        int index2 = 0;
        List<Integer> equalValues = new ArrayList<Integer>();
        while ((index1 < array1.length) && (index2 < array2.length)){
            if (array1[index1] == array2[index2]){
                equalValues.add(array1[index1]);
                int temp = array1[index1];
                while ((index1 < array1.length) && (temp == array1[index1])){
                    index1++;
                }
            } else if (array1[index1] < array2[index2]){
                index1++;
            } else {
                index2++;
            }
        }
        return equalValues;
    }

    /**
     * Method finds different elements in both arrays
     * @param array1 first array
     * @param array2 second array
     * @return list of different elements of arrays
     */
    public static List<Integer> findDifferentValues(int[] array1, int[] array2) {
        Arrays.sort(array1);
        Arrays.sort(array2);
        List<Integer> result = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (!(index1 >= array1.length && index2 >= array2.length)) {
            while (index1 < array1.length - 1 && array1[index1] == array1[index1 + 1]) {
                index1++;
            }
            while (index2 < array2.length - 1 && array2[index2] == array2[index2 + 1]) {
                index2++;
            }
            if (index1 < array1.length && index2 < array2.length) {
                if (array1[index1] < array2[index2]) {
                    result.add(array1[index1]);
                    index1++;
                } else if (array1[index1] > array2[index2]) {
                    result.add(array2[index2]);
                    index2++;
                } else {
                    index1++;
                    index2++;
                }
            } else {
                if (index1 < array1.length) {
                    result.add(array1[index1]);
                    index1++;
                } else {
                    result.add(array2[index2]);
                    index2++;
                }
            }
        }
        return result;
    }

    /**
     * Method finds different elements in both arrays. Using HashMap
     * @param array1 first array
     * @param array2 second array
     * @return list of different elements of arrays using Hash
     */
    public static List<Integer> findDifferentValuesUsingHash(int[] array1, int[] array2) {
        Map<Integer, Integer> array1hash = new HashMap<>(array1.length);
        List<Integer> result = new ArrayList<>();

        for (int el : array1) {
            array1hash.put(el, 0);
        }

        for (int el : array2) {
            if (!result.contains(el)) {
                if (!array1hash.containsKey(el)) {
                    result.add(el);
                } else {
                    array1hash.put(el, 1);
                }
            }
        }

        array1hash.keySet().forEach(el -> {
            if (array1hash.get(el)==0) {
                result.add(el);
            }
        });
        return result;
    }

    /**
     * Method finds different elements in both arrays. Using collections
     *
     * Very slow method. Requires more time than others methods
     *
     * @param array1 first array
     * @param array2 second array
     * list of different elements of arrays
     */
    public static List<Integer> findDifferentValuesUsingHashSet(int[] array1, int[] array2) {
        Set<Integer> set1 = new HashSet<Integer>(Arrays.stream(array1).boxed().collect(Collectors.toList()));
        Set<Integer> set2 = new HashSet<Integer>(Arrays.stream(array2).boxed().collect(Collectors.toList()));
        List<Integer> result = new ArrayList<>(set1);
        result.addAll(set2);
        set1.retainAll(set2);
        result.removeAll(set1);
        return result;
    }
}
